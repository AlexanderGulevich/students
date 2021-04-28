package basisFx.appCore.reflection;

import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.utils.DomainPropertiesMetaInfo;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectionInspectDomain {

    public static String retrievePropertyName(ActiveRecord record, Field field) {
        SimpleObjectProperty property= getPropertyFromClass(field,record);
        String propertyName = property.getName();
        propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        return propertyName;
    }

    public static String retrieveShortGenericTypeName(ActiveRecord record, Field field, ParameterizedType type) {
        Type parameterizedType = getParameterizedType(record,type, field);
        String typeName = parameterizedType.getTypeName();
        SimpleObjectProperty obj = null;
        String[] arr = typeName.split("\\.");
        return arr[arr.length - 1];
    }

    public static Class retrievGenericClass(ActiveRecord record, Field field, ParameterizedType type) {
        Type parameterizedType = getParameterizedType(record,type, field);
        String typeName = parameterizedType.getTypeName();
        Class<?> aClass=null;
        try {
            aClass = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    public static String retrieveFullGenericTypeName(ActiveRecord record, Field field, ParameterizedType type) {
        Type parameterizedType = getParameterizedType(record,type, field);
        String typeName = parameterizedType.getTypeName();
        return  typeName;
    }

    public static boolean isaStaticField(Field field) {
        return java.lang.reflect.Modifier.isStatic(field.getModifiers());
    }

    public static Type getParameterizedType(ActiveRecord record,ParameterizedType type, Field field) {
        ParameterizedType pt = type;
        Type rawType = pt.getRawType();
        Type ownerType = pt.getOwnerType();
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        Type argument = actualTypeArguments[0];
        SimpleObjectProperty property= getPropertyFromClass(field,record);
        return argument ;
    }

    public static Field[] getDeclaredFields(ActiveRecord record) {
        Class<? extends ActiveRecord> aClass = record.getClass();
        return aClass.getDeclaredFields();
    };

    public static  SimpleObjectProperty getPropertyFromClass(Field declaredField, ActiveRecord record) {
        try {
            return (SimpleObjectProperty) declaredField.get(record);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static ArrayList<DomainPropertiesMetaInfo> inspectDomainProperties(ActiveRecord record) {
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList =new ArrayList<>();
        Field[] declaredFields = getDeclaredFields(record);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (isaStaticField(field)) continue;
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {

                String shortGenericTypeName = retrieveShortGenericTypeName(record,field, (ParameterizedType) type);
                String fullGenericTypeName = retrieveFullGenericTypeName(record,field, (ParameterizedType) type);
                String propertyName = retrievePropertyName(record,field);
                Class genericClass = retrievGenericClass(record, field,(ParameterizedType) type);
                DataStore dataStoreAnnotation = field.getAnnotation(DataStore.class);

                DomainPropertiesMetaInfo info = new DomainPropertiesMetaInfo();
                info.setGenericFullTypeName(fullGenericTypeName);
                info.setGenericShortTypeName(shortGenericTypeName);
                info.setPropertyName(propertyName);
                info.setGenericClass(genericClass);
                info.setDataStoreAnnotation(dataStoreAnnotation);



                domainPropertiesMetaInfoList.add(info);
            }
        }
        return domainPropertiesMetaInfoList;
    }

}
