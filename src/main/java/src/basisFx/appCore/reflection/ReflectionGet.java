package basisFx.appCore.reflection;


import basisFx.appCore.utils.DomainPropertiesMetaInfo;
import basisFx.appCore.utils.Range;
import basisFx.dataSource.Db;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import javafx.collections.ObservableList;

import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReflectionGet {

    public static void setPropertyValueWithSimpleType(ResultSet rs ,
                                                      DomainPropertiesMetaInfo propertiesMetaInfo,
                                                      ActiveRecord activeRecord) {

        String genericShortTypeName = propertiesMetaInfo.getGenericShortTypeName();
        String propertyName = propertiesMetaInfo.getPropertyName();
        Class propertyGenericClass = propertiesMetaInfo.getGenericClass();

        Method setMethod = Reflection.setMethod(activeRecord,propertyName,propertyGenericClass);

            try {
                if (genericShortTypeName.equals("String")) {
                    setMethod.invoke(activeRecord, rs.getString(propertyName));
                }
                if (genericShortTypeName.equals("Double")) {
                    setMethod.invoke(activeRecord, rs.getDouble(propertyName));
                }
                if (genericShortTypeName.equals("Integer")) {
                    setMethod.invoke(activeRecord, rs.getInt(propertyName));
                }
                if (genericShortTypeName.equals("LocalDate")) {
                    setMethod.invoke(activeRecord, rs.getDate(propertyName).toLocalDate());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public static void setPropertyValueWithDomainType(ResultSet rs ,
                                                      DomainPropertiesMetaInfo propertiesMetaInfo,
                                                      ActiveRecord activeRecord) {

        String genericShortTypeName = propertiesMetaInfo.getGenericShortTypeName();
        String propertyName = propertiesMetaInfo.getPropertyName();
        Class propertyGenericClass = propertiesMetaInfo.getGenericClass();

        try {
            Method find = propertyGenericClass.getSuperclass().getDeclaredMethod("find", int.class);
            ActiveRecord instanceForPropertyObject = Reflection.getDomainInstanceFromStaticMethod(propertyGenericClass);
            ActiveRecord InerRecord=null;
            try {
                InerRecord = (ActiveRecord)find.invoke(instanceForPropertyObject,rs.getInt(propertyName+"Id"));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Method setMethod = activeRecord.getClass().getDeclaredMethod("set" + propertyName, propertyGenericClass);
            try {
                setMethod.invoke(activeRecord,InerRecord);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void setPropertyValueBollComboBox(ResultSet rs ,
                                                      DomainPropertiesMetaInfo propertiesMetaInfo,
                                                      ActiveRecord activeRecord) {
     String propertyName = propertiesMetaInfo.getPropertyName();
     Class propertyGenericClass = propertiesMetaInfo.getGenericClass();
     try {
         Method setMethod = activeRecord.getClass().getDeclaredMethod("set" + propertyName, propertyGenericClass);
         setMethod.invoke(activeRecord, new BoolComboBox(rs.getBoolean(propertyName)));
     } catch (NoSuchMethodException e) {
         e.printStackTrace();
     } catch (IllegalAccessException e) {
         e.printStackTrace();
     } catch (InvocationTargetException e) {
         e.printStackTrace();
     } catch (SQLException e) {
         e.printStackTrace();
     }

 }

    public static void setIdToDomain(ActiveRecord activeRecord, ResultSet rs){
        Method methodIdSetter = null;
        try {
            methodIdSetter = activeRecord.getClass().getSuperclass().getDeclaredMethod("setId" , int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            try {
                methodIdSetter.invoke(activeRecord,rs.getInt("id"));
            } catch (SQLException e) {
                System.out.println("отсутствует id");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static ActiveRecord findDomain(int id, ActiveRecord activeRecord, ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList, String expression) {
        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                ReflectionGet.setIdToDomain(activeRecord,rs);

                for (DomainPropertiesMetaInfo propertiesMetaInfo : domainPropertiesMetaInfoList) {

                    if(propertiesMetaInfo.getGenericClass().getSuperclass()  == ActiveRecord.class){
                        if(propertiesMetaInfo.getGenericClass()==BoolComboBox.class){
                            ReflectionGet.setPropertyValueBollComboBox(rs,propertiesMetaInfo,activeRecord);
                        }else{
                            ReflectionGet.setPropertyValueWithDomainType(rs,propertiesMetaInfo,activeRecord);
                        }
                    }else{
                        ReflectionGet.setPropertyValueWithSimpleType(rs,propertiesMetaInfo,activeRecord);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeRecord;
    }

    public static ObservableList<ActiveRecord> getAllDomainsList(
            ActiveRecord record,
            ObservableList<ActiveRecord> list,
            ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList,
            ResultSet rs) {
        try {
            while (rs.next()) {
                ActiveRecord activeRecord = Reflection.createNewInstance(record);
                ReflectionGet.setIdToDomain(activeRecord,rs);

                for (DomainPropertiesMetaInfo propertiesMetaInfo : domainPropertiesMetaInfoList) {

                    if(propertiesMetaInfo.getGenericClass().getSuperclass()  == ActiveRecord.class){
                        if(propertiesMetaInfo.getGenericClass()==BoolComboBox.class){
                            ReflectionGet.setPropertyValueBollComboBox(rs,propertiesMetaInfo,activeRecord);
                        }else{
                            ReflectionGet.setPropertyValueWithDomainType(rs,propertiesMetaInfo,activeRecord);
                        }
                    }else{
                        ReflectionGet.setPropertyValueWithSimpleType(rs,propertiesMetaInfo,activeRecord);
                    }
                }
                list.add(activeRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public static String createFindAllByOuterIdExpression(ActiveRecord record, ArrayList<DomainPropertiesMetaInfo> inf, int id) {
        return  "Select * from " + record.entityName  + " "+ExpressionProcessor.getWhere(inf,id);
    }

    public static String createFindAllByOuterIdAndRangeExpression(ActiveRecord record, ArrayList<DomainPropertiesMetaInfo> inf, int id, Range range) {
        return  "Select * from " + record.entityName  + " "+ExpressionProcessor.getWhere(inf,id,range);
    }
    public static ResultSet findAllByOuterIdAndPeriod(ActiveRecord record,
                                                                   ArrayList<DomainPropertiesMetaInfo> inf,
                                                                   int id,
                                                                   LocalDate start,
                                                                   LocalDate end
    ) {

        String expression = "Select * from " + record.entityName + ExpressionProcessor.getWhereForPeriod(inf,id);

        PreparedStatement pstmt=null;
        try {
            pstmt = Db.connection.prepareStatement(expression);

            pstmt.setDate(1, Date.valueOf(start));
            pstmt.setDate(2, Date.valueOf(end));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ResultSet findAllByPeriod(ActiveRecord record, ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList, LocalDate start, LocalDate end) {

        String expression = "Select * from " + record.entityName + ExpressionProcessor.getWhereForPeriod(domainPropertiesMetaInfoList);

        PreparedStatement pstmt=null;
        try {
            pstmt = Db.connection.prepareStatement(expression);

            pstmt.setDate(1, Date.valueOf(start));
            pstmt.setDate(2, Date.valueOf(end));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createFindAllByRangeExpression(ActiveRecord record, ArrayList<DomainPropertiesMetaInfo> inf, Range range) {
        return  "Select * from " + record.entityName  + " "+ExpressionProcessor.getWhere(inf,range);
    }
}
