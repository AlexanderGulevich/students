package basisFx.service;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.RowAddToTable;
import basisFx.appCore.events.RowDeleteFromTable;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public  class WindowServiceAddDel extends WindowService {

    private TableWrapper table_wrapper;
    private TableWrapper outer_table_wrapper;
    private RowAddToTable rowAddToTable ;
    private RowDeleteFromTable rowDeleteFromTable ;

    @FXML private AnchorPane dynamicContentAnchorHolder;
    @FXML private AnchorPane titlePanel;
    @FXML private Button selectBut;
    @FXML private Button addBut;
    @FXML private Button delBut;
    @FXML private Label title;

    public WindowServiceAddDel() {
        Registry.crossWindowMediators.put("AddDellPopupWindow",this);
    }
    @Override
    public void init() {
        outer_table_wrapper = (TableWrapper)  Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
        table_wrapper = (TableWrapper) currentWindow.getNodeFromMap("tableWrapper");
        currentWindow.setNodeToMap(dynamicContentAnchorHolder,"dynamicContentAnchorHolder");
        currentWindow.setNodeToMap(titlePanel,"titlePanel");
        currentWindow.setNodeToMap(selectBut,"selectBut");
        currentWindow.setNodeToMap(title,"title");
        ServiceTables mediator = table_wrapper.getMediator();
        mediator.setRefreshCallBack(this::refreshTable);

        initCloseButtonEvent();
        initStageDragging();
        initTitle();
        bondingToContentAnchorWidth();
        initAddDelButtonsEvents();
        refreshTable();
    }
    private void refreshTable() {
        table_wrapper.getMediator().setItems(
                table_wrapper,
                table_wrapper.activeRecord.getAll()
        );
    }
    private void bondingToContentAnchorWidth() {
        dynamicContentAnchor = currentWindow.getCurrentDynamicContent().getDynamicContentAnchorHolder();
        Coordinate coordinate = new Coordinate(0d, 0d, 0d, 0d);
        coordinate.setChildNode(dynamicContentAnchor);
        coordinate.setParentAnchorPane(dynamicContentAnchorHolder);
        coordinate.bonding();
    }
    private void initAddDelButtonsEvents() {
        rowAddToTable=new RowAddToTable(table_wrapper);
        rowAddToTable.setEventToElement(addBut);
        rowAddToTable.setMediator(this);
        rowDeleteFromTable=new RowDeleteFromTable(table_wrapper);
        rowDeleteFromTable.setEventToElement(delBut);
        rowDeleteFromTable.setMediator(this);
    }
    private void initCloseButtonEvent() {
        ClosePopupAndSubWindow closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setMediator(this);
        closePopupAndSubWindow.setCallBackTyped(()->{
            if (callBackParametrized != null) { //if calback for bind table exist
                if(table_wrapper.getItems().toArray().length==0){
                    currentWindow.getStage().close();
                    close();
                }
                if (table_wrapper.clickedDomain != null) {
                    return true;
                }return false;
            }return true;
        } );
        closePopupAndSubWindow.setEventToElement(selectBut);

    }
    private void initStageDragging() {
        new StageDragging().setEventToElement(titlePanel);
    }
    private void initTitle() {
        title.setText(currentWindow.getWindowImpl().getTitleName());
    }
    @Override
    public void inform(Object node) {
        if (node == selectBut) {
            if (callBackParametrized != null) {
                callBackParametrized.call(table_wrapper.clickedDomain);
            }
        }
        if (node == selectBut) {
           informParentWindowAboutClosing();
        }
    }

}
