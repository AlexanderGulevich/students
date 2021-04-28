package basisFx.service.price;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.CheckBoxAdapter;
import basisFx.appCore.elements.ComboboxAdapter;
import basisFx.appCore.elements.ToogleHandler;
import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.events.SubWindowCreaterByBut;
import basisFx.appCore.grid.ButSizeEnum;
import basisFx.appCore.grid.CtrlPosEnum;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.poi.StringHandler;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.FXMLLoader;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.price.*;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.*;
import java.util.stream.Collectors;


public class ServicePanelPriceOutput extends ServicePanels {
    @FXML
    private JFXButton output;
    @FXML
    private JFXButton saveTamplate;
    @FXML
    private Button templateTableBut;
    @FXML
    private Button categoryTableBut;
    @FXML
    private Label commonLabelName;
    @FXML
    private TextField textField;
    @FXML
    private FlowPane flowpane;
    @FXML
    private JFXCheckBox selecyAll;
    @FXML
    private JFXCheckBox addImage;
    @FXML
    private JFXCheckBox standatrCategory;
    @FXML
    private ComboBox tamplateCombobox;
    @FXML
    private AnchorPane panelAnchor;
    @FXML
    private JFXToggleButton swichImgSize;

    DirectoryChosserEvent directoryChosserEvent;

    ToogleHandler toogleHandler_swichImgSize;

    StringHandler stringHandler=new StringHandler();

    ComboboxAdapter comboboxAdapter;

    ArrayList<StoredCategory> storedCategoryArrayList=new ArrayList<>();
    ArrayList<CheckBoxAdapter> checkBoxHandlersArrayList =new ArrayList<>();

    @Override
    public void init() {

        toogleHandler_swichImgSize=new ToogleHandler(swichImgSize,this);
        comboboxAdapter = new ComboboxAdapter(tamplateCombobox, this, OutputTemplate.getINSTANCE());
        currentWindow=Registry.mainWindow;

        directoryChosserEvent = new DirectoryChosserEvent();
        directoryChosserEvent.setEventToElement(output);
        directoryChosserEvent.setMediator(this);
        directoryChosserEvent.setCallBackTyped(() -> {
            Price price = (Price) Registry.dataExchanger.get("price");
            if (price == null) {
                Registry.windowFabric.infoWindow("Для того, чтобы сохранить обработанный прайс, его нужно сначала загрузить из остатков!");
                return false;
            }
            return true;
            }

        );

        addImage.setSelected(true);

        selecyAllAction();
        switchImgInit();
        checkboxCreater();
        createStoredCategoryPopup();
        createOutputTemplatePopup();
        saveTemplateAction();
        standatrCategorySwitch();

    }

    private void standatrCategorySwitch() {
        standatrCategory.setOnAction(event -> {
            if (standatrCategory.isSelected()) {
                noneSelectionForAllAction();
                standatrCategory.setSelected(true);
                selecyAll.setSelected(false);
            }else {
                standatrCategory.setSelected(false);
            }

        });
    }

    private void saveTemplateAction() {
        saveTamplate.setOnAction(event ->
                {
                    String textStart = textField.getText().trim();
                    String text=stringHandler.delAllSpace(textStart);
                    int length = text.length();
                    if(length ==0) Registry.windowFabric.infoWindow("Введите имя шаблона.");
                    if(length >0 && length <5) Registry.windowFabric.infoWindow("Введите, как минимум, 5 символов.");
                    if(length >4) {
                        OutputTemplate outputTemplate=new OutputTemplate();
                        outputTemplate.setName(textStart);
                        List<Integer> collect = storedCategoryArrayList.stream().mapToInt(value -> value.getId()).boxed().collect(Collectors.toList());
                        outputTemplate.setStoredCategory(new ArrayList<>(collect));
                        outputTemplate.insert();
                        textField.clear();
                    }
                    comboboxAdapter.refresh();
                }
                );
    }

    private void selecyAllAction() {
        selecyAll.setOnAction(event -> {
            if (selecyAll.isSelected()) {
                storedCategoryArrayList.clear();
                checkBoxHandlersArrayList.forEach(cbh ->{
                    JFXCheckBox jfxCheckBox = cbh.getJfxCheckBox();
                    StoredCategory storedCategory = (StoredCategory) cbh.getRecord();
                    storedCategoryArrayList.add(storedCategory);
                    jfxCheckBox.setSelected(true);
                    standatrCategory.setSelected(false);
                } );
            }else {
                checkBoxHandlersArrayList.forEach(cbh ->{
                    JFXCheckBox jfxCheckBox = cbh.getJfxCheckBox();
                    storedCategoryArrayList.clear();
                    jfxCheckBox.setSelected(false);
                } );
            }
        });
    }

    private void noneSelectionForAllAction() {
                storedCategoryArrayList.clear();
                checkBoxHandlersArrayList.forEach(cbh ->{
                    JFXCheckBox jfxCheckBox = cbh.getJfxCheckBox();
                    jfxCheckBox.setSelected(false);
                } );
    }

    private void switchImgInit() {
        swichImgSize.setText("Большие картинки");
        swichImgSize.setSelected(true);
        swichImgSize.setOnAction(event -> {
        });
    }

    private void checkboxCreater() {
        flowpane.getChildren().clear();
        checkBoxHandlersArrayList.clear();
        ObservableList<ActiveRecord> activeRecords = StoredCategory.getINSTANCE().getAll();
        activeRecords.stream().forEach(record -> {
            JFXCheckBox checkbox = FXMLLoader.loadCheckBox("checkbox");
            checkbox.setText(((StoredCategory) record).getName());
            CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(checkbox, this, record);
            checkBoxHandlersArrayList.add(checkBoxAdapter);
            flowpane.getChildren().add(checkbox);
        });
    }

    private void createStoredCategoryPopup() {
        WindowBuilder popup = WindowBuilder.newBuilder()
                .setPanelCreator(() -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        SingleTableSet.builder()
                                .aClass(StoredCategory.class)
                                .isSortable(false).isEditable(true).bigTitle(null)
                                .currentWindow(window)
                                .littleTitle("Управление категориями прайса")
                                .parentAnchor(dynamicContentAnchorHolder)
                                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP)
                                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                                .addButEvent(null).delButEvent(null)
                                .column( ColumnFabric.intCol( "№","rank",0.1d,true ) )
                                .column( ColumnFabric.stringCol( "Наименование ","name",0.9d,true ) )
                                .build().configure();
                    }
                })
                .setTitle("Категории")
                .setMessage(null)
                .setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d).setHeight(600d)
                .setPreClosingCallBack(()->{
                    checkboxCreater();
                    storedCategoryArrayList.forEach(storedCategory ->
                            checkBoxHandlersArrayList.forEach(checkBoxAdapter -> {
                                if (((StoredCategory) checkBoxAdapter.getRecord()).getName().equals(storedCategory.getName())) {
                                    checkBoxAdapter.getJfxCheckBox().setSelected(true);
                                }
                            }));
                })
                .build();

        SubWindowCreaterByBut subWindowCreaterByBut=new SubWindowCreaterByBut();
        subWindowCreaterByBut.setEventToElement(categoryTableBut);
        subWindowCreaterByBut.setWindowBuilder(popup);
    }

    private void createOutputTemplatePopup() {
        WindowBuilder popup = WindowBuilder.newBuilder()
                .setPanelCreator(() -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
//        ScenicView.show(window.getScene());
                        SingleTableSet.builder()
                                .aClass(OutputTemplate.class)
                                .isSortable(false).isEditable(true).bigTitle(null)
                                .currentWindow(window)
                                .littleTitle("Управление шаблонами вывода")
                                .parentAnchor(dynamicContentAnchorHolder)
                                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_DEL_BUT_BOTTON)
                                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                                .addButEvent(null).delButEvent(null)
                                .column( ColumnFabric.stringCol( "Наименование ","name",1d,true ) )
                                .build().configure();
                    }
                })
                .setTitle("Шаблон")
                .setMessage(null)
                .setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d).setHeight(600d)
//                .setPreClosingCallBack( () -> {
//                    comboboxAdapter.refresh();
//                            })
                .setCallBackParametrized( clickedDomain ->{
                    OutputTemplate record = (OutputTemplate) clickedDomain;
                    comboboxAdapter.refresh();
                    comboboxAdapter.choiceItemById(record);
                } )
                .build();

        SubWindowCreaterByBut subWindowCreaterByBut=new SubWindowCreaterByBut();
        subWindowCreaterByBut.setEventToElement(templateTableBut);
        subWindowCreaterByBut.setWindowBuilder(popup);
    }


    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelPriceOutput() {
        Registry.dataExchanger.put("priceWritter", this);
    }


    @Override
    public void inform(Object node) {
        if (node==output) {
            write();
        }
        if (node==tamplateCombobox) {
            tamplateChoice();
        }
        if ( node.getClass()== CheckBoxAdapter.class) {
            categoryChoice((CheckBoxAdapter) node);
        }


    }

    private void categoryChoice(CheckBoxAdapter node) {
        CheckBoxAdapter checkBoxAdapter = node;
        JFXCheckBox jfxCheckBox = checkBoxAdapter.getJfxCheckBox();
        StoredCategory record = (StoredCategory) checkBoxAdapter.getRecord();
        jfxCheckBoxSwitchBehaviour(jfxCheckBox, record);
    }

    private void tamplateChoice() {
        noneSelectionForAllAction();
        OutputTemplate outputTemplate = ((OutputTemplate) comboboxAdapter.getSelected());
        ArrayList<Integer> storedCategoryIdList = outputTemplate.getStoredCategory();
        for (Integer id : storedCategoryIdList) {
            CheckBoxAdapter checkBoxAdapter = getCheckBoxHandlerById(id);
            if (checkBoxAdapter != null) {
                checkBoxAdapter.getJfxCheckBox().setSelected(true);
                jfxCheckBoxSwitchBehaviour(checkBoxAdapter.getJfxCheckBox(),(StoredCategory) checkBoxAdapter.getRecord());
            }

        }
    }

    private void write() {
        String path = directoryChosserEvent.getPath();
        Price price = (Price) Registry.dataExchanger.get("price");
        boolean bigImgSize = swichImgSize.isSelected();
        boolean imgExistatnt = addImage.isSelected();
        boolean standatrCategorySelected = standatrCategory.isSelected();

        if (!standatrCategorySelected) {
            List<CheckBoxAdapter> checkBoxAdapters_ACTIVE = getActiveCheckBoxHandlersArrayList();
            Map<Integer, JFXCheckBox> jfxCheckBoxMap = activeCheckBoxHandlersArrayListToMAP(checkBoxAdapters_ACTIVE);
            List<ActiveRecord> filtered = filterRecords(jfxCheckBoxMap);
            Map<String, List<ActiveRecord>> filteredWithNameCategories = filteredWithNameCategories(filtered);
            Price newPrice = createPriceWithCustomeCategories(filteredWithNameCategories);
            newPrice.setPriceDate(price.getPriceDate());
            newPrice.setPriceDateString(price.getPriceDateString());
            new WriterForPrice(newPrice, path,bigImgSize,imgExistatnt);
        }else {
            new WriterForPrice(price, path,bigImgSize,imgExistatnt);
        }


    }

    private Map<Integer, JFXCheckBox> activeCheckBoxHandlersArrayListToMAP(List<CheckBoxAdapter> checkBoxAdapters_ACTIVE) {
        return checkBoxAdapters_ACTIVE.stream().collect(Collectors.toMap(o -> o.getRecord().getId(), o -> o.getJfxCheckBox()));
    }

    private List<CheckBoxAdapter> getActiveCheckBoxHandlersArrayList() {
        return checkBoxHandlersArrayList.stream()
                    .filter(checkBoxAdapter -> checkBoxAdapter.getJfxCheckBox().isSelected())
                    .collect(Collectors.toList());
    }

    private Price createPriceWithCustomeCategories(Map<String, List<ActiveRecord>> filteredWithNameCategories) {
        Price price=new Price();
        Set<Map.Entry<String, List<ActiveRecord>>> entries = filteredWithNameCategories.entrySet();

        List<Map.Entry<String, List<ActiveRecord>>> sortedCategories = entries.stream().sorted((o1, o2) -> {
            Integer rankFirst = ((PriceItem) o1.getValue().get(0)).getStoredCategory().getRank();
            Integer rankSecond = ((PriceItem) o2.getValue().get(0)).getStoredCategory().getRank();
            return rankFirst.compareTo(rankSecond);
        }).collect(Collectors.toList());


        sortedCategories.forEach(entry -> {
            ArrayList<PriceItem> list = new ArrayList<>();
            List<PriceItem> items = entry.getValue().stream().map(record -> ((PriceItem) record)).collect(Collectors.toList());
            List<PriceItem> priceItems = items.stream().sorted(Comparator.comparing(priceItem -> priceItem.toString())).collect(Collectors.toList());
            list.addAll(priceItems);
            price.createCategory(entry.getKey(),list);
        });
        return price;
    }

    private Map<String, List<ActiveRecord>> filteredWithNameCategories(List<ActiveRecord> activeRecordsFiltered) {
        Map<String, List<ActiveRecord>> map = activeRecordsFiltered.stream()
                .collect(Collectors.groupingBy(o -> ((PriceItem) o).getStoredCategory().getName()));

//        map.entrySet().stream().forEach(stringListEntry ->{
//            if(stringListEntry.getValue()==null) map.remove(stringListEntry.getKey());
//            if(stringListEntry.getValue().toArray().length==0) map.remove(stringListEntry.getKey());
//        });

       return map;
    }

    private List<ActiveRecord> filterRecords(Map<Integer, JFXCheckBox> jfxCheckBoxMap) {
        return PriceItem.getINSTANCE().getAllFullData().stream().filter(record -> {
                PriceItem priceItem = ((PriceItem) record);
                StoredCategory storedCategory = priceItem.getStoredCategory();
                if (storedCategory!=null && storedCategory.getId() != null && priceItem.getVisibitity().getBoolean() == true) {
                    Integer id = storedCategory.getId();
                    JFXCheckBox box = jfxCheckBoxMap.get(id);
                    if (box != null) {
                        return true;
                    }
                }
                return false;
            }).collect(Collectors.toList());
    }

    private CheckBoxAdapter getCheckBoxHandlerById(Integer id) {
        Optional<CheckBoxAdapter> first = checkBoxHandlersArrayList.stream().filter(c ->

        {
            Integer id_from_checkBoxHandlersArrayList = c.getRecord().getId();
            return id_from_checkBoxHandlersArrayList.equals(id);
        })
                .findAny();

        if (first.isPresent()) {
            CheckBoxAdapter checkBoxAdapter = first.get();
            return checkBoxAdapter;
        }
        return null;

    }

    private void jfxCheckBoxSwitchBehaviour(JFXCheckBox jfxCheckBox, StoredCategory record) {
        standatrCategory.setSelected(false);
        if (jfxCheckBox.isSelected()) {
            storedCategoryArrayList.add(record);
        }else {
            List<StoredCategory> collect = storedCategoryArrayList.stream()
                    .filter(storedCategory -> storedCategory.getName().equals(record.getName())).collect(Collectors.toList());
            if(collect.toArray().length>1){
                Registry.windowFabric.infoWindow("в storedCategoryArrayList > 2 одинаковых значений");
            }
            else {
            storedCategoryArrayList.remove(collect.get(0));
            }
        }
    }

}