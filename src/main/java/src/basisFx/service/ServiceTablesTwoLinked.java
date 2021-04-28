package basisFx.service;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

public class ServiceTablesTwoLinked extends ServiceTables {

    @Setter @Getter private RangeDirector rangeDirector;
    @Setter @Getter private TableWrapper primaryTableWrapper;
    @Setter @Getter private TableWrapper accessoryTableWrapper;
    public int idFromPrimeTable;

    @Override
    public void inform(Object node) {
        if (node == primaryTableWrapper) {
            if (!ActiveRecord.isNewDomane(primaryTableWrapper.clickedDomain)) {
                setClickedDomainFromPrimaryTable();
                refresh(accessoryTableWrapper);
            }
        }
        if (node == rangeDirector) {
            refresh(accessoryTableWrapper);
        }
    }

    private void setClickedDomainFromPrimaryTable() {
        idFromPrimeTable = primaryTableWrapper.clickedDomain.id.get();
    }

    @Override
    public void wasRemoved(AppNode node, ActiveRecord record) {
        TableWrapper tableWrapper = (TableWrapper) node;
        ServiceTablesTwoLinked mediator = (ServiceTablesTwoLinked) tableWrapper.getMediator();
        Boolean isNewDomane = ActiveRecord.isNewDomane(record);
        boolean readyToTransaction = record.isReadyToTransaction();
        if (readyToTransaction) {
            if (!isNewDomane) {
                tableWrapper.unitOfWork.registercDeleted(record);
                if (tableWrapper == mediator.getPrimaryTableWrapper()) {
                    removeAccessoryTableItems(mediator);
                }
                commit(tableWrapper);
            }
        }
    }
    private  void removeAccessoryTableItems(ServiceTablesTwoLinked mediator) {
        ObservableList<ActiveRecord> items = mediator.getAccessoryTableWrapper().getElement().getItems();
        if (items != null) {
            for (ActiveRecord record:items) {
                if (ActiveRecord.isNewDomane(record)) {
                    continue;
                }
                mediator.getAccessoryTableWrapper().unitOfWork.registercDeleted(record);
            }
            commit(mediator.getAccessoryTableWrapper());
        }
    }
    @Override
    public void wasChanged(AppNode node, ActiveRecord record) {
        if (node == accessoryTableWrapper) record.outerId = idFromPrimeTable;
        TableWrapper tableWrapper = (TableWrapper) node;
        Boolean isNewDomane = ActiveRecord.isNewDomane(record);
        boolean readyToTransaction = record.isReadyToTransaction();
        if (readyToTransaction) {
            if (isNewDomane){
                tableWrapper.unitOfWork.registerNew(record);
            }else {
                tableWrapper.unitOfWork.registercDirty(record);
            }
            commit(tableWrapper);
        }
    }
    @Override
    public void refresh(AppNode node) {
        if (node == primaryTableWrapper) {
            refreshPrimaryTable();
        }
        if (node == accessoryTableWrapper && rangeDirector ==null ) {
            refreshByOuterId(accessoryTableWrapper,idFromPrimeTable);
        }
        if (node == accessoryTableWrapper&& rangeDirector !=null) {
            refreshViaRangeAndOuterId(accessoryTableWrapper,idFromPrimeTable, rangeDirector.getSelectedRange());
        }
    }

    private void refreshPrimaryTable() {
        setItems(primaryTableWrapper,primaryTableWrapper.activeRecord.getAll());
    }

    @Override
    public void initElements() {
           primaryTableWrapper.setItems(primaryTableWrapper.activeRecord.getAll());
           accessoryTableWrapper.setItems(null);

    }

    @Override
    public TableWrapper getTableWrapper() {
        return null;
    }

}




