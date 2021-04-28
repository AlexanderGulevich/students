
package basisFx.dataSource;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.DataStoreCallBack;
import lombok.Singular;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UnitOfWork {


    public HashMap <String,ArrayList<ActiveRecord>>      newDomainObjects=new HashMap<>();
    public HashMap <String,ArrayList<ActiveRecord>>      dirtyDomainObjects=new HashMap<>();
    public HashMap <String,ArrayList<ActiveRecord>>      deletedDomainObject=new HashMap<>();

    public void registerNew(ActiveRecord record){
        listNullCheck(newDomainObjects,record.entityName);
        ArrayList<ActiveRecord> records = newDomainObjects.get(record.entityName);
        records.add(record);
    }
    public void registercDirty(ActiveRecord record){
        listNullCheck(dirtyDomainObjects,record.entityName);
        ArrayList<ActiveRecord> records = dirtyDomainObjects.get(record.entityName);
        records.add(record);
    }
    public void registercDeleted(ActiveRecord record){
        listNullCheck(deletedDomainObject,record.entityName);
        ArrayList<ActiveRecord> records = deletedDomainObject.get(record.entityName);
        records.add(record);
    }

    public void clearNew(){
        this.newDomainObjects.clear();
    }
    public void cleardDeleted(){
        this.deletedDomainObject.clear();
    }
    public void clearDirty(){
        this.dirtyDomainObjects.clear();

    }

    public boolean isExistNew(String s,ActiveRecord record){
        return newDomainObjects.get(s).contains(record);
    }
    public boolean isExistDeleted(String s,ActiveRecord record){
        return deletedDomainObject.get(s).contains(record);
    }
    public boolean isExistDirty(String s,ActiveRecord record){
        return dirtyDomainObjects.get(s).contains(record);
    }

    private void listNullCheck(HashMap<String,ArrayList<ActiveRecord>>  map,String activeRecordName) {
        ArrayList<ActiveRecord> activeRecords = map.get(activeRecordName);
        if(activeRecords==null){
            map.put(activeRecordName,new ArrayList<>());
        }
    }

    public boolean commit() {
        if (commitNew() && commitDirty() && commitDeleted()){
            cleardDeleted();
            clearDirty();
            clearNew();
            System.out.println("UnitOfWork commit");

            return  true;
        }
        System.out.println("UnitOfWork none commit");
        return false;

    }
    public boolean commitNew(){
        Set<ActiveRecord> recordsSet=iterateHMapAndGetAllDomainObjects(newDomainObjects);
        if (recordsSet.size()==0 )return true;
        if(recordsSet.size()>0 && isReadyToTransaction(recordsSet)) {
            for (Iterator<ActiveRecord> iterator = recordsSet.iterator(); iterator.hasNext(); ) {
                ActiveRecord next = iterator.next();
                next.insert();
            }
            return true;
        }
            return false;
    }
    public boolean commitDirty(){
        Set<ActiveRecord> recordsSet=iterateHMapAndGetAllDomainObjects(dirtyDomainObjects);
        if (recordsSet.size()==0)return true;
        if(recordsSet.size()>0 && isReadyToTransaction(recordsSet)){
            for (Iterator<ActiveRecord> iterator = recordsSet.iterator(); iterator.hasNext();) {
                ActiveRecord next = iterator.next();
                    next.update();
            }
            return true;
        }
        return false;

    }

    private boolean isReadyToTransaction(Set<ActiveRecord> recordsSet) {
        boolean isReady=false;
        for (Iterator<ActiveRecord> iterator = recordsSet.iterator(); iterator.hasNext();) {
            ActiveRecord next = iterator.next();
            if (next.isReadyToTransaction()) {
                isReady=true;
            }else {
                return false;
            }
        }
        return isReady;
    }

    public boolean commitDeleted(){
        Set<ActiveRecord> recordsSetDeleted=iterateHMapAndGetAllDomainObjects(deletedDomainObject);
        if (recordsSetDeleted.size()==0)return true;
        for (Iterator<ActiveRecord> iterator = recordsSetDeleted.iterator(); iterator.hasNext();) {
            ActiveRecord next = iterator.next();
                next.delete();
        }

        return true;
    }

    private  Set<ActiveRecord>  iterateHMapAndGetAllDomainObjects(HashMap <String,ArrayList<ActiveRecord>>map) {
        Set<ActiveRecord> recordsSet = Collections.newSetFromMap(new ConcurrentHashMap<ActiveRecord, Boolean>() {});

        for (String s : map.keySet()) {
            ArrayList<ActiveRecord> activeRecords = map.get(s);
            recordsSet.addAll(activeRecords);
        }

        return recordsSet;
    }

}
