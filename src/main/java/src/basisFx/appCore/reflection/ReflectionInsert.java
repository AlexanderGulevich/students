package basisFx.appCore.reflection;

import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.settings.Settings;
import basisFx.appCore.utils.DomainPropertiesMetaInfo;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import javafx.application.Platform;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReflectionInsert {
    public static String createInsertExpression(ActiveRecord activeRecord, ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList) {
        String expression =" INSERT INTO "  + activeRecord.entityName + " (  ";
        for (DomainPropertiesMetaInfo info : domainPropertiesMetaInfoList) {
            if(info.getGenericClass().getSuperclass()  == ActiveRecord.class){
                if(info.getGenericClass()== BoolComboBox.class){
                    expression+=" "+info.getPropertyName()+",";
                }else{
                    expression+=" "+info.getPropertyName()+"Id"+",";
                }
            }else{
                expression+=" "+info.getPropertyName()+",";
            }
        }
        expression=expression.substring(0,expression.length()-1);
        expression+=" ) VALUES(" ;

        int length = domainPropertiesMetaInfoList.toArray().length;
        for (int i = 0; i < length; i++) {
            expression+="?,";
        }
        expression=expression.substring(0,expression.length()-1);
        expression=expression+")";


        return expression;

    }

    public static void executeInsertStatement(ActiveRecord activeRecord, String insertExpression, ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList) {
        PreparedStatement pstmt = null;
        try {
            pstmt = Db.connection.prepareStatement(insertExpression);
            int counter=1;
            for (DomainPropertiesMetaInfo info : domainPropertiesMetaInfoList) {

                DataStore dataStoreAnnotation = info.getDataStoreAnnotation();

                if (dataStoreAnnotation != null) {
                    if (dataStoreAnnotation.AS_OUTER_ID() && activeRecord.outerId!=null) {
                        Integer val = activeRecord.outerId;
                        pstmt.setInt(counter, val);
                        counter++;
                        continue;
                    }
                }

                String genericShortTypeName = info.getGenericShortTypeName();
                String propertyName = info.getPropertyName();
                Class propertyGenericClass = info.getGenericClass();
                Method method = Reflection.getMethod(propertyName, activeRecord.getClass());

                if(propertyGenericClass.getSuperclass()  == ActiveRecord.class){
                    if(info.getGenericClass()== BoolComboBox.class){
                        Boolean val = Reflection.invokeBooleanGetter(activeRecord, method);
                        pstmt.setBoolean(counter, val);
                    }else{
                        ActiveRecord val = Reflection.invokeDomainGetter(activeRecord, method);
                        pstmt.setInt(counter, val.getId());
                    }
                }else{
                    if (genericShortTypeName.equals("String")) {
                        String val = Reflection.invokeStringGetter(activeRecord, method);
                        pstmt.setString(counter, val);
                    }
                    if (genericShortTypeName.equals("Double")) {
                        Double val = Reflection.invokeDoubleGetter(activeRecord, method);
                        pstmt.setDouble(counter, val);
                    }
                    if (genericShortTypeName.equals("Integer")) {
                        Integer val = Reflection.invokeIntegerGetter(activeRecord, method);
                        pstmt.setInt(counter, val);
                    }
                    if (genericShortTypeName.equals("LocalDate")) {
                        LocalDate val = Reflection.invokeLocalDateGetter(activeRecord, method);
                        pstmt.setDate(counter, Date.valueOf(val));
                    }
                }
                counter++;
            }

             pstmt.executeUpdate();


        } catch (SQLException e) {

            Platform.runLater(() -> {

                Registry.windowFabric.infoWindow(Settings.COMMON_ERROR_MESSAGE);
                e.printStackTrace();
                    });

        }

    }
}