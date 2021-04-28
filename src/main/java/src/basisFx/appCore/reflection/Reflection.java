package basisFx.appCore.reflection;

import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import javafx.beans.property.SimpleObjectProperty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Reflection {


    public static ResultSet executeQuery(String expression)   {
        try (
                Statement stmt = Db.connection.createStatement()) {
            ResultSet resultSet;
            try {
                resultSet = stmt.executeQuery(expression);

                return resultSet;
            } catch (SQLException e) {
                Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
            e.printStackTrace();
        }
        return null;
    }


    public static ActiveRecord getDomainInstanceFromStaticMethod(Class propertyGenericClass) {

        ActiveRecord record =null;
        try {
            Method getINSTANCE = propertyGenericClass.getDeclaredMethod("getINSTANCE");
            record = (ActiveRecord) getINSTANCE.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
            e.printStackTrace();
        }
        return record;

    }
    public static ActiveRecord createNewInstance(ActiveRecord record){
        Class<? extends ActiveRecord> aClass = record.getClass();
        ActiveRecord activeRecord=null;
        try {
            activeRecord = aClass.newInstance();
        } catch (InstantiationException e) {
            Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Registry.windowFabric.infoWindow("Что-то пошло не так при загрузке данных из БД");
            e.printStackTrace();
        }
        return  activeRecord;


    }

    public static boolean isReadyToTransaction(ActiveRecord activeRecord){
        boolean isReady=false;
        Field[] declaredFields = ReflectionInspectDomain.getDeclaredFields(activeRecord);
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (ReflectionInspectDomain.isaStaticField(declaredField)) continue;

            DataStore dataStore = declaredField.getAnnotation(DataStore.class);
            if (dataStore != null) {
                if (dataStore.AS_OUTER_ID())  continue;
                SimpleObjectProperty property= ReflectionInspectDomain.getPropertyFromClass(declaredField,activeRecord);
                Object obj = property.get();
                if (dataStore.NOT_CHECK_FOR_TRANSACTIONS() && obj==null)  continue;
            }

            SimpleObjectProperty property= ReflectionInspectDomain.getPropertyFromClass(declaredField,activeRecord);
            Object obj = property.get();
            if (obj!= null ) {
                isReady=true;
            }else {
                isReady=false;
                break;
            }
        }
        return isReady;
    }

    public static Method getMethod(String propertyName, Class activeRecordClass){
        String name = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Method method = null;
        try {
            method = activeRecordClass.getDeclaredMethod("get" + name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method setMethod(ActiveRecord record,String propertyName, Class propertyGenericClass){
        Method setMethod = null;
        try {
            setMethod = record.getClass().getDeclaredMethod("set" + propertyName, propertyGenericClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return setMethod;
    }



    public static Boolean invokeBooleanGetter(ActiveRecord activeRecord, Method method) {
        BoolComboBox value=null;
        try {
            value = (BoolComboBox) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value.getBoolean();
    }
    public static ActiveRecord invokeDomainGetter(ActiveRecord activeRecord, Method method) {
        ActiveRecord value=null;
        try {
            value = (ActiveRecord) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static String invokeStringGetter(ActiveRecord activeRecord, Method method) {
        String value=null;
        try {
            value = (String) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static Integer invokeIntegerGetter(ActiveRecord activeRecord, Method method) {
        Integer value=null;
        try {
            value = (Integer) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static Double invokeDoubleGetter(ActiveRecord activeRecord, Method method) {
        Double value=null;
        try {
            value = (Double) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static LocalDate invokeLocalDateGetter(ActiveRecord activeRecord, Method method) {
        LocalDate value=null;
        try {
            value = (LocalDate) method.invoke(activeRecord);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }


}
