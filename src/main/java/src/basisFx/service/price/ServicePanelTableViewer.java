package basisFx.service.price;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.elements.ToogleHandler;
import basisFx.appCore.grid.ButSizeEnum;
import basisFx.appCore.grid.CtrlPosEnum;
import basisFx.appCore.panelSets.Panel;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.price.StoredCategory;
import basisFx.domain.price.PriceItem;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.stream.Collectors;


public class ServicePanelTableViewer extends ServicePanels {
    @FXML
    private JFXToggleButton imgToggle;
    @FXML
    private JFXToggleButton categoryToggle;
    @FXML
    private JFXToggleButton aliasToggle ;
    @FXML
    private JFXToggleButton viewedToggle;
    @FXML
    private JFXToggleButton noneBarcodeToggle;
    @FXML
    private JFXToggleButton allToggle;
    @FXML
    private AnchorPane tableAnchor;
    @FXML
    private Label commonLabelName;

    ToogleHandler toogleHandler_imgToggle;
    ToogleHandler toogleHandler_categoryToggle;
    ToogleHandler toogleHandler_aliasToggle;
    ToogleHandler toogleHandler_viewedToggle;
    ToogleHandler toogleHandler_allToggle;
    ToogleHandler toogleHandler_noneBarcodeToggle;

    SingleTableSet singleTableSet;

    public static boolean bindRecordsHasChanged=false;

    public ServicePanelTableViewer() {
        Registry.dataExchanger.put("priceTableViewer", this);
    }

    @Override
    public void init() {


        currentWindow=Registry.mainWindow;

         toogleHandler_imgToggle=new ToogleHandler(imgToggle,this);
         toogleHandler_categoryToggle=new ToogleHandler(categoryToggle,this);
         toogleHandler_aliasToggle=new ToogleHandler(aliasToggle,this);
         toogleHandler_viewedToggle=new ToogleHandler(viewedToggle,this);
         toogleHandler_allToggle=new ToogleHandler(allToggle,this);
         toogleHandler_noneBarcodeToggle=new ToogleHandler(noneBarcodeToggle,this);


        setTooglesDisabled();


        createTable();


    }

    private void createTable() {
        WindowBuilder popup=WindowBuilder.newBuilder()
              .setTitle("Изображения")
              .setMessage(null)
              .setFxmlFileName("PriceImgHandlerWindow")
              .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
              .setWidth(700d)
              .setHeight(600d)
              .setPreClosingCallBack( () ->  singleTableSet.setItems(PriceItem.getINSTANCE().getAll()) )
              .build();


         singleTableSet = SingleTableSet.builder()
                .aClass(PriceItem.class)
                .parentAnchor(tableAnchor)
                .cssClassesStringsList("table_font_size_12")
                .cssClassesStringsList("wrappedHeaderColumn_font_size_14")
                .isSortable(false).isEditable(true)
                .currentWindow(currentWindow)
                .bigTitle(null).littleTitle(" ")
                .coordinate(new Coordinate(-30d, 10d, 0d, 0d))
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_N_O_N).butSizeEnum(ButSizeEnum.BUT_SIZE_NON)
                .addButEvent(null).delButEvent(null)
                .column(ColumnFabric.stringCol("код", "barcode", 0.09d, false))
//                .column(ColumnFabric.stringCol("№","pure_order",0.04d,false))
                .column(ColumnFabric.stringCol("№", "orderNumber", 0.05d, false))
                .column(ColumnFabric.stringCol("Наименование", "name", 0.30d, true))
                .column(ColumnFabric.stringCol("Псевдоним", "alias", 0.26d, true))
                .column(ColumnFabric.boolCol("Вывод", "visibitity", 0.05d, true))
                .column(ColumnFabric.popupViaBtnColButYN("Картинка", "Показать", "--нет--",0.07d, popup,
                         activeRecord -> {
                             if (activeRecord != null) {
                                 if (((PriceItem) activeRecord).getImg() != null) {
                                     return true;
                                 }
                                 return false;
                             }
                             return false;
                         }
                 ))
                .column(ColumnFabric.comboBoxCol(StoredCategory.class, "Категория ", "storedCategory", 0.18d, true))
                .build();

        singleTableSet.configure();

        if (bindRecordsHasChanged) {
            singleTableSet.setItems(PriceItem.getINSTANCE().getAllFullData());
            bindRecordsHasChanged=false;
        }


        TableWrapper tableWrapper = singleTableSet.getMediatorSingleTable().getTableWrapper();
        Registry.dataExchanger.put("outer_table_wrapper",tableWrapper);

    }

    private void setTooglesDisabled() {
        imgToggle.setSelected(false);
        categoryToggle.setSelected(false);
        aliasToggle.setSelected(false);
        viewedToggle.setSelected(false);
        noneBarcodeToggle.setSelected(false);
        allToggle.setSelected(false);
    }


    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    @Override
    public void inform(Object node) {

        if (node==toogleHandler_imgToggle) {
            setTooglesDisabled();
            imgToggle.setSelected(true);
            //to view without img
            List<ActiveRecord> recordList = PriceItem.getINSTANCE().getAll().stream()
                    .filter(record -> ((PriceItem) record).getImg() == null)
                    .collect(Collectors.toList());
            singleTableSet.setItems(recordList);
        }
        if (node==toogleHandler_categoryToggle) {
            setTooglesDisabled();
            categoryToggle.setSelected(true);
            //to view without category
            List<ActiveRecord> recordList = PriceItem.getINSTANCE().getAll().stream()
                    .filter(record -> ((PriceItem) record).getStoredCategory() == null)
                    .collect(Collectors.toList());
            singleTableSet.setItems(recordList);

        }
        if (node==toogleHandler_aliasToggle) {
            setTooglesDisabled();
            aliasToggle.setSelected(true);
            //to view without alias
            List<ActiveRecord> recordList = PriceItem.getINSTANCE().getAll().stream()
                    .filter(record -> ((PriceItem) record).getAlias() == null)
                    .collect(Collectors.toList());
            singleTableSet.setItems(recordList);


        }
        if (node==toogleHandler_viewedToggle) {
            setTooglesDisabled();
            viewedToggle.setSelected(true);
            //to view without not viewed
            List<ActiveRecord> recordList = PriceItem.getINSTANCE().getAll().stream()
                    .filter(record -> (!((PriceItem) record).getVisibitity().getBoolean()))
                    .collect(Collectors.toList());
            singleTableSet.setItems(recordList);

        }
        if (node==toogleHandler_noneBarcodeToggle) {
            setTooglesDisabled();
            noneBarcodeToggle.setSelected(true);
            //to view without not viewed
            List<ActiveRecord> recordList = PriceItem.getINSTANCE().getAll().stream()
                    .filter(record -> ((PriceItem) record).getBarcode() == null)
                    .collect(Collectors.toList());
            singleTableSet.setItems(recordList);

        }
        if (node==toogleHandler_allToggle) {
            setTooglesDisabled();
            allToggle.setSelected(true);
            //insert all
            singleTableSet.setItems(PriceItem.getINSTANCE().getAll());

        }


    }

}