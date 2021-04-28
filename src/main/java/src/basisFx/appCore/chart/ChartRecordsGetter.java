package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;

public class ChartRecordsGetter {


    public static  ObservableList<ActiveRecord>  getRecords( Class aClass ){
        ObservableList<ActiveRecord> activeRecords=null;

        try {
            activeRecords = ((ActiveRecord) aClass.newInstance()).getAllWithoutID();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return activeRecords;
    }


}
