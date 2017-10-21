package com.billings.utils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import com.billings.wrappers.Encoder;

public class Utils {
	
	private static final Set<Class> PRIMITIVE_OBJECT_TYPES = new HashSet<Class>(){{
		add(Object.class); add(Character.class); add(String.class); add(Void.class); add(Boolean.class);
		add(Integer.class); add(Short.class); add(Long.class); add(Double.class); add(Float.class);
	}};
	
	
	public static List<Method> findGetterMethodsForAttributes(Class type, String... attributes) throws Exception {
		List<Method> methods = findMethodsForAttributes(type, "get", attributes);
		
		methods.addAll(findMethodsForAttributes(type, "is", attributes));
		
		return methods;
	}
	
	
	public static List<Method> findSetterMethodsForAttributes(Class type, String... attributes) throws Exception {
		return findMethodsForAttributes(type, "set", attributes);
	}
	
	
	private static List<Method> findMethodsForAttributes(Class type, String accessPrefix, String... attributes) throws Exception {
    	int attributeCount = attributes.length;
    	int prefixLength = accessPrefix.length();

    	List<Method> associatedMethods = new ArrayList<Method>(attributeCount);
    	
    	Method[] allMethodsInObject = type.getMethods();
    	
    	Map<String, Method> methodMap = new HashMap<String, Method>();
    	
    	for (Method method : allMethodsInObject) {
    		String methodName = method.getName();
    		
    		if (methodName.length() > prefixLength && methodName.startsWith(accessPrefix)) {
    			String trimmedName = methodName.substring(prefixLength);
    			String standardizedName = standardize(trimmedName);
    			
    			methodMap.put(standardizedName, method);
    		}
    	}
    	
    	for (int i=0; i<attributeCount; i++) {
    		String attribute = attributes[i];
    		String standardizedAttribute = standardize(attribute);
    		
    		Method associatedMethod = methodMap.get(standardizedAttribute);
    		
    		if (associatedMethod == null) {
    			String verbiage = String.format("No method found for attribute name %s in class %s", attribute, type);
    			throw new Exception(verbiage);
    		}
    		
    		associatedMethods.add(associatedMethod);
    	}
    	
    	return associatedMethods;
    }
	
	
	private static String standardize(String str) {
		str = str.replaceAll("[^A-Za-z0-9]", "");
		str = str.toLowerCase();
		
		return str;
	}
	
	
	public static Object cast(Object target, Class castType) {
		if (target == null || target.getClass() == castType) {
			return target;
		
		} else if (target.getClass().isPrimitive()) {
			return castInboundPrimitive(target, castType);
		
		} else {
			return castInboundObject(target, castType);
		}
	}
	
	
	private static Object castInboundPrimitive(Object target, Class castType) {
		Object objectifiedTarget = null;
		
		Class targetClass = target.getClass();
		
		if (targetClass == int.class) {
			objectifiedTarget = (Integer)target;
		} else if (targetClass == boolean.class) {
			objectifiedTarget = (Boolean)target;
		} else if (targetClass == char.class) {
			objectifiedTarget = (Character)target;
		} else if (targetClass == byte.class) {
			objectifiedTarget = (Byte)target;
		} else if (targetClass == short.class) {
			objectifiedTarget = (Short)target;
		} else if (targetClass == long.class) {
			objectifiedTarget = (Long)target;
		} else if (targetClass == float.class) {
			objectifiedTarget = (Float)target;
		} else if (targetClass == double.class) {
			objectifiedTarget = (Double)target;
		}
		
		return castInboundObject(objectifiedTarget, castType);
	}
	
	
	private static Object castInboundObject(Object target, Class castType) {
		String modifiedTarget = null;
		
		if (target.getClass() != Timestamp.class) {
			modifiedTarget = target.toString();
		} else {
			modifiedTarget = ((Timestamp)target).toString();
		}
		
		if (castType == String.class) { 
			return modifiedTarget;
		} else if (castType == Integer.class || castType == int.class) { 
			return Integer.parseInt(modifiedTarget);
		} else if (castType == Timestamp.class) { 
			return Timestamp.valueOf(modifiedTarget);
		} else if (castType == Double.class || castType == double.class) { 
			return Double.parseDouble(modifiedTarget);
		} else if (castType == Float.class || castType == float.class) { 
			return Float.parseFloat(modifiedTarget);
		} else if (castType == Long.class || castType == long.class) { 
			return Long.parseLong(modifiedTarget);
		} else if (castType == Boolean.class || castType == boolean.class) { 
			return Boolean.valueOf(modifiedTarget);
		} else if (castType == java.sql.Date.class) { 
			return java.sql.Date.valueOf(sanitizeDate(modifiedTarget));
		} else if (castType == java.util.Date.class) { 
			return java.util.Date.parse(modifiedTarget);
		} else if (castType == Character.class || castType == char.class) { 
			return modifiedTarget.toCharArray()[0];
		} else if (castType == LocalDate.class) {
			if (target.getClass() == Timestamp.class)
				return ((Timestamp)target).toLocalDateTime().toLocalDate();
			else if (target.getClass() == java.util.Date.class) 
				return convertDateToLocalDate((java.util.Date)target);
			else if (target.getClass() == java.sql.Date.class)
				return convertDateToLocalDate((java.sql.Date)target);
		} 
		
		return target;
	}


	private static String sanitizeDate(String modifiedTarget) {
		int trimTo = modifiedTarget.indexOf(" ");
		
		if (trimTo == -1)
			return modifiedTarget;
		
		return modifiedTarget.substring(0, trimTo);
	}
	
	
	public static LocalDate convertDateToLocalDate(Date date) {
		LocalDate convertedFormat = null;
		
		if (date != null) {
			Instant instant = Instant.ofEpochMilli(date.getTime());
			LocalDateTime dateTime = LocalDateTime.ofInstant(instant,  ZoneId.systemDefault());
			convertedFormat = dateTime.toLocalDate();
		}
		
		return convertedFormat;
	}
	
	
	public static boolean isPrimitiveObject(Object o) {
		return isPrimitiveObject(o.getClass());
	}
	
	
	public static boolean isPrimitiveObject(Class c) {
		return PRIMITIVE_OBJECT_TYPES.contains(c);
	}
	
	
}
