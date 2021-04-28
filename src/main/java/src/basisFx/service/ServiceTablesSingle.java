package basisFx.service;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.windows.WindowInfoDispatcher;
import basisFx.dataSource.UnitOfWork;
import basisFx.appCore.activeRecord.ActiveRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ServiceTablesSingle extends ServiceTables {

    @Setter private TableWrapper tableWrapper;
    @Setter private int outrId;
    @Setter
    public List<DataStoreCallBack> dataStoreCallBack ;
    @Setter @Getter
    private RangeDirector rangeDirector;
    ActiveRecord record;

    @Override
    public void inform(Object node) {
        if (node == rangeDirector) {
            refreshViaRange(tableWrapper,rangeDirector.getSelectedRange());
        }
    }

    @Override
    public void wasRemoved(AppNode node, ActiveRecord record) {
        UnitOfWork unitOfWork = ((TableWrapper) node).unitOfWork;
        boolean readyToTransaction = record.isReadyToTransaction();
        if (readyToTransaction) {
            Boolean isNewDomane = ActiveRecord.isNewDomane(record);
            if (!isNewDomane){
                unitOfWork.registercDeleted(record);
                    unitOfWork.commit();
            }
        }
    }
    @Override
    public void wasChanged(AppNode node, ActiveRecord record) {
        this.record=record;
        UnitOfWork unitOfWork = ((TableWrapper) node).unitOfWork;
//        boolean readyToTransaction = record.isReadyToTransaction();
//        if (readyToTransaction) {
//            Boolean isNewDomane = ActiveRecord.isNewDomane(record);
//
//            if (!isNewDomane){
//                unitOfWork.registercDirty(record);
//            }else {
//                unitOfWork.registerNew(record);
//            }
//                unitOfWork.commit();
//                ((TableWrapper) node).getMediator().refresh(node);
//        }

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
        if (refreshCallBack != null) {
            refreshCallBack.call();
        }else {
            TableWrapper tableWrapper = (TableWrapper) node;

            setItems(tableWrapper,tableWrapper.activeRecord.getAll());
        }


    }

    @Override
    public void initElements() {
        tableWrapper.setItems(tableWrapper.activeRecord.getAll());
    }

    @Override
    public TableWrapper getTableWrapper() {
        return tableWrapper;
    }

}
