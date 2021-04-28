package basisFx.appCore.activeRecord;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.utils.DomainPropertiesMetaInfo;
import basisFx.appCore.reflection.*;
import basisFx.appCore.utils.Range;
import basisFx.dataSource.Db;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ActiveRecord {
    public String entityName;
    public ObjectProperty<Integer> id =new SimpleObjectProperty<>(this, "id", null);
    public Integer outerId=null;
    public abstract String toString();  //Method for Combobox
    public ActiveRecord( ) {
        String name = this.getClass().getName();
        String[] arr = name.split("\\.");
        name= arr[arr.length - 1];
        this.entityName =name;
    }
    public boolean isReadyToTransaction(){
        return Reflection.isReadyToTransaction(this);
    }

    public Integer getId() {
        return id.get();
    }
    public void setId(int value) {
        this.id.set(value);
    }
    public  ObservableList <ActiveRecord>  getAllByDate(LocalDate date){return null;};
    public  ObservableList<ActiveRecord>  createNewActiveRecordList() {
        return FXCollections.<ActiveRecord>observableArrayList();
    }
    public static boolean isNewDomane(ActiveRecord record) {
        if (record != null) {
            if (record.getId() !=null) {
                return false;
            }else{
                return true;
            }
        }else{
            throw new  NullPointerException();
        }
    }
    public  void setOuterIdToRecord(Integer id) {
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        for (DomainPropertiesMetaInfo info : domainPropertiesMetaInfoList) {
            DataStore dataStoreAnnotation = info.getDataStoreAnnotation();
            if (dataStoreAnnotation != null) {
                if (dataStoreAnnotation.AS_OUTER_ID()) {
                    String propertyName = info.getPropertyName();
                    Method method = Reflection.setMethod(this,propertyName, info.getGenericClass());
                    try {
                        method.invoke(this,id);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
    public  ObservableList <ActiveRecord>  getAll() {
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        ResultSet rs = Reflection.executeQuery("Select * from " + this.entityName + " order by id ");
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public  ObservableList <ActiveRecord>  getAllWithoutID() {
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        ResultSet rs = Reflection.executeQuery("Select * from " + this.entityName );
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ObservableList<ActiveRecord> findAllByOuterId(int id){
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String exp = ReflectionGet.createFindAllByOuterIdExpression(this,domainPropertiesMetaInfoList, id);
        ResultSet rs = Reflection.executeQuery(exp);
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ObservableList<ActiveRecord> findAllByOuterIdAndRange(int id, Range   range){
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String exp = ReflectionGet.createFindAllByOuterIdAndRangeExpression(this,domainPropertiesMetaInfoList, id,range);
        ResultSet rs = Reflection.executeQuery(exp);
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ObservableList<ActiveRecord> findAllByOuterIdAndPeriod(int id, LocalDate start, LocalDate end){
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        ResultSet rs =  ReflectionGet.findAllByOuterIdAndPeriod(this,domainPropertiesMetaInfoList, id,start,end);
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ObservableList<ActiveRecord> findAllByRange(  Range   range){
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String exp = ReflectionGet.createFindAllByRangeExpression(this,domainPropertiesMetaInfoList,  range);
        ResultSet rs = Reflection.executeQuery(exp);
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ObservableList<ActiveRecord> findAllByPeriod( LocalDate start, LocalDate end){
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        ResultSet rs =  ReflectionGet.findAllByPeriod(this,domainPropertiesMetaInfoList,start,end);
        return ReflectionGet.getAllDomainsList(this,list, domainPropertiesMetaInfoList, rs);
    }
    public ActiveRecord find(int id) {
        ActiveRecord activeRecord = Reflection.createNewInstance(this);
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String expression="SELECT  * FROM " +this.entityName+" WHERE ID=?";
        return ReflectionGet.findDomain(id, activeRecord, domainPropertiesMetaInfoList, expression);
    }
    public void delete(){
            try {
                String expression="delete from " +entityName+" where id=? ";
                PreparedStatement pstmt =  Db.connection.prepareStatement(expression);
                pstmt.setInt(1, this.id.get());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void update() {
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String updateExpression = ReflectionUpdate.createUpdateExpression(this,domainPropertiesMetaInfoList);
        ReflectionUpdate.executePepareStatement(this,updateExpression,domainPropertiesMetaInfoList);
    }
    public void insert()  {
        ArrayList<DomainPropertiesMetaInfo> domainPropertiesMetaInfoList = ReflectionInspectDomain.inspectDomainProperties(this);
        String insertExpression = ReflectionInsert.createInsertExpression(this,domainPropertiesMetaInfoList);
        ReflectionInsert.executeInsertStatement(this,insertExpression,domainPropertiesMetaInfoList);
    }
    protected void executeUpdate(PreparedStatement pstmt){
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected PreparedStatement prepareStatement(String expression){
        try {
            return     Db.connection.prepareStatement(expression);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Integer getMaxIdFromTable(ActiveRecord record){
        ResultSet rs = Reflection.executeQuery(  "SELECT  max(id) as id FROM " +record.entityName);
        Integer id = null;
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                id = rs.getInt("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}


