package basisFx.service;

import basisFx.appCore.elements.LabelWrapper;
import basisFx.appCore.elements.RangeDirector;
import basisFx.appCore.elements.DatePickerHandler;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.events.ClosePopupAndSubWindow;
import basisFx.appCore.events.RowAddToTable;
import basisFx.appCore.events.RowDeleteFromTable;
import basisFx.appCore.events.StageDragging;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Range;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public  class WindowServiceByDateResearch extends WindowService {

    private RangeDirector rangeDirector;
    private DatePickerHandler datePickerHandlerSTART;
    private DatePickerHandler datePickerHandlerEND;
    private TableWrapper table_wrapper;
    private TableWrapper outer_table_wrapper;
    private LocalDate selectedDateStart ;
    private LocalDate selectedDateEnd ;
    private RowAddToTable rowAddToTable ;
    private RowDeleteFromTable rowDeleteFromTable ;
    private Label gridLabel;

    @FXML private AnchorPane dynamicContentAnchorHolder;
    @FXML private AnchorPane titlePanel;
    @FXML private Button addBut;
    @FXML private Button delBut;
    @FXML private Button okBut;
    @FXML private Button periodBut;
    @FXML private Label title;
    @FXML private DatePicker dateStart;
    @FXML private DatePicker dateEnd;
    @FXML private ComboBox combobox;

    public WindowServiceByDateResearch() {

        Registry.crossWindowMediators.put("ByDateResearchWindow",this);
    }
    @Override
    public void init() {
        outer_table_wrapper = (TableWrapper)  Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
        Registry.crossWindowMediators.put("ByDateResearchWindow",this);

        gridLabel =   LabelWrapper.newBuilder()
                .setCSSid(CSSid.LABEL_TEXT)
                .setText(((String) currentWindow.getNodeFromMap("gridLabel")))
                .setFont(FontsStore.ROBOTO_LIGHT)
                .setAlignment(Pos.BASELINE_LEFT)
                .setFontSize(25d)
                .build().getElement();


        table_wrapper = (TableWrapper) currentWindow.getNodeFromMap("tableWrapper");
        table_wrapper.getMediator().setRefreshCallBack(this::refreshTable);
        table_wrapper.setOuterTable(outer_table_wrapper);
        initCloseButtonEvent();
        initStageDragging();
        initTitle();
        bondingToContentAnchorWidth();
        initAddDelButtonsEvents();
        initComboboxHandler();
        initDatePickersHadler();
        initPeriodButEvent();
        refreshTable();
    }
    private void refreshTable() {
        Integer clickedDomainId = outer_table_wrapper.clickedDomain.getId();
        ObservableList<ActiveRecord> allByOuterId = table_wrapper.activeRecord.findAllByOuterId(clickedDomainId);
        allByOuterId.forEach(record -> record.outerId=clickedDomainId);
        table_wrapper.getMediator().setItems( table_wrapper, allByOuterId);
    }
    private void initDatePickersHadler() {
        datePickerHandlerSTART=new DatePickerHandler(dateStart,this);
        datePickerHandlerEND=new DatePickerHandler(dateEnd,this);
    }
    private void initComboboxHandler() {
        rangeDirector =new RangeDirector(
                combobox,
                this,
                Range.LAST10,
                Range.getAll()
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
    private void initPeriodButEvent() {
        periodBut.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1 ||  event.getClickCount() ==2 ) {
                if (selectedDateStart != null && selectedDateEnd!= null ) {
                    table_wrapper.getMediator()
                            .refreshViaPeriodAndOuterId(
                                    table_wrapper,
                                    outer_table_wrapper.clickedDomain.id.get(),
                                    selectedDateStart,
                                    selectedDateEnd
                            );
                }
            }
        });
    }
    private void initCloseButtonEvent() {
        ClosePopupAndSubWindow closePopupAndSubWindow = new ClosePopupAndSubWindow();
        closePopupAndSubWindow.setMediator(this);
        closePopupAndSubWindow.setEventToElement(okBut);
    }
    private void initStageDragging() {
        new StageDragging().setEventToElement(titlePanel);
    }
    private void initTitle() {
        title.setText(outer_table_wrapper.clickedDomain.toString());
        gridLabel.setText(currentWindow.getWindowImpl().getTitleName());
    }
    @Override
    public void inform(Object node) {
        if (node == okBut) {
            informParentWindowAboutClosing();
        }
        if (node == rowAddToTable) {
           rowAddToTable.getCurrentNewInstance().setOuterIdToRecord(outer_table_wrapper.clickedDomain.getId());
        }
        if (node == rangeDirector) {
            table_wrapper.getMediator()
                    .refreshViaRangeAndOuterId(
                            table_wrapper,
                            outer_table_wrapper.clickedDomain.id.get(),
                            rangeDirector.getSelectedRange()
                    );
        }
        if (node == datePickerHandlerSTART) {
            selectedDateStart=datePickerHandlerSTART.getSelectedDate();
        }
        if (node == datePickerHandlerEND) {
            selectedDateEnd=datePickerHandlerEND.getSelectedDate();
        }
    }

}
