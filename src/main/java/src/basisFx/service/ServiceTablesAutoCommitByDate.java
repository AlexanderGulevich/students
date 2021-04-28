package basisFx.service;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.interfaces.RecordWithDate;
import basisFx.appCore.utils.DateGetter;
import basisFx.appCore.windows.WindowInfoDispatcher;
import basisFx.dataSource.UnitOfWork;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

public class ServiceTablesAutoCommitByDate extends ServiceTables {
    private TableWrapper tableWrapper;
    @Setter
    public List<DataStoreCallBack> dataStoreCallBack ;
    @Setter
    private DateGetter dateGetter;

    @Override
    public void inform(Object node) {
        if (node==dateGetter){
            initElements();
        }
    }

    @Override
    public void wasRemoved(AppNode node, ActiveRecord record) {
        UnitOfWork unitOfWork = ((TableWrapper) node).unitOfWork;
        boolean newDomane = ActiveRecord.isNewDomane(record);
        if (!newDomane) {
            unitOfWork.registercDeleted(record);
            commit(tableWrapper);
        }
    }

    @Override
    public void wasChanged(AppNode node, ActiveRecord record) {
        if (checkingTableRecordCallBack != null) {
            checkingTableRecordCallBack.call(record);
        }
        UnitOfWork unitOfWork = ((TableWrapper) node).unitOfWork;
        LocalDate date = dateGetter.getDate();
        ((RecordWithDate) record).setDate(date);
        boolean ready = record.isReadyToTransaction();
        if (ready) {
            if (dataStoreCallBack != null) {
                boolean accept=true;
                for (DataStoreCallBack callBack : dataStoreCallBack) {
                    if (!callBack.check(record)) {
                        accept = false;
                    }
                }
                if (accept) {
                    write(record, unitOfWork, ready);
                }else {
                    WindowInfoDispatcher.run();
                }
            }
            else {
                write(record, unitOfWork, ready);
            }
        }


    }

    private void write(ActiveRecord record, UnitOfWork unitOfWork, boolean readyToTransaction) {
        if (readyToTransaction) {
            boolean newDomane = ActiveRecord.isNewDomane(record);
            if (newDomane) {
                unitOfWork.registerNew(record);
            }else{
                unitOfWork.registercDirty(record);
            }
            commit(tableWrapper);
        }
    }

    @Override
    public void refresh(AppNode node) {
        initElements();
    }

    @Override
    public void initElements() {
        LocalDate date = dateGetter.getDate();
        if (date != null) {
            ObservableList <ActiveRecord> list=tableWrapper.activeRecord.getAllByDate(date);
            if (list == null) {
                list=FXCollections.observableArrayList();
            }
            tableWrapper.setItems(list);
        }

    }

    @Override
    public TableWrapper getTableWrapper() {
        return tableWrapper;
    }

    public void setTableWrapper(TableWrapper tableWrapper) {
        this.tableWrapper = tableWrapper;
    }


}
