package basisFx.appCore.elements;

import basisFx.appCore.grid.GridOrganization;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.table.ColWrapper;
import basisFx.appCore.table.ColWrapperPopupViaBtnButYN;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.DynamicContentPanel;
import basisFx.service.ServiceTables;
import basisFx.appCore.events.AppEvent;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.dataSource.UnitOfWork;
import basisFx.appCore.table.TableListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import basisFx.appCore.settings.FontsStore;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

public  class TableWrapper extends AppNode  {
    private boolean isEditable;
    @Setter @Getter private TableWrapper outerTable;
    private DynamicContentPanel dynamicContentPanel;
    private GridPaneWrapper gridPaneWrapper;
    private Callback<TableView.ResizeFeatures,Boolean> columnResizePolicy ;
    private boolean isSortableColums;
    private Double widthPercent;
    private ReadOnlyDoubleProperty parentWidthProperty;
    private double prefHeight;
    private ServiceTables serviceTables;
    private ColWrapper[] colWrappers;
    private TableView<ActiveRecord> element;
    public ObservableList<ActiveRecord>  list;
    public UnitOfWork unitOfWork;
    private TableListener  tableListener=new TableListener (this);
    public  ActiveRecord clickedDomain;
    public  Class activeRecordClass;
    public  ActiveRecord activeRecord;
    private boolean gridLinesVisibility;
    private GridOrganization gridOrganization;
    private String gridName;
    private String className;
    private List<ColWrapper>   colWrapper;
    private List<ColWrapperPopupViaBtnButYN.Builder> colWrapperPopupViaBtnButYN;

    private TableWrapper(Builder builder) {

        events = builder.events;
        dynamicContentPanel = builder.dynamicContentPanel;
        width = builder.width;
        height = builder.height;
        parentAnchor = builder.parentAnchor;
        parentGroup = builder.parentGroup;
        parentFlowPane = builder.parentFlowPane;
        parentScrollPane = builder.parentScrollPane;
        metaName = builder.metaName;
        stage = builder.stage;
        isEditable = builder.isEditable;
        columnResizePolicy = builder.columnResizePolicy;
        isSortableColums = builder.isSortableColums;
        prefHeight = builder.prefHeight;
        setServiceTables(builder.serviceTables);
        colWrappers = builder.colWrappers;
        unitOfWork = builder.unitOfWork;
        clickedDomain = builder.clickedDomain;
        activeRecordClass = builder.activeRecordClass;
        widthPercent=builder.widthPercent;
        parentWidthProperty=builder.parentWidthProperty;
        isEditable=builder.isEditable;
        gridLinesVisibility=builder.gridLinesVisibility;
        gridOrganization=builder.gridOrganization;
        gridName=builder.gridName;
        className=builder.className;
        coordinate=builder.coordinate;
        windowAbstraction=builder.windowAbstraction;
        colWrapper=builder.colWrapper;
        colWrapperPopupViaBtnButYN=builder.colWrapperPopupViaBtnButYN;
        cssClass=builder.cssClass;
        setElementToWindowRegistry();
        createActiveRecord(builder);
        element =new TableView<>();

        cssClassesStrings=builder.cssClassesStrings;
        cssClassesStringsList=builder.cssClassesStringsList;
        cssClasses=builder.cssClasses;

        element.getStyleClass().add(CSSclasses.TABLE_BFx.get());
//        element.setId("first_child_Red");

        applyCssClasses();

        applyColumnResizePolicy();
        applyColums();
        applyEditable();
        applyColumnResizePolicy();
        applyPlaceholder();
        applySortableAllCollums();
        applyTablesWidthProperty();
        setClickedRowDetection();
        createGrid();
        applyClassName();
        bond(this);
    }

    private void applyClassName() {
        if (className != null) {
            element.getStyleClass().add(className);
        }else {
            element.getStyleClass().add("wrappedHeaderColumn_font_size_16");

        }
    }

    public GridPaneWrapper getGridPaneWrapper() {
        return gridPaneWrapper;
    }

    private void createGrid() {
        if (gridOrganization != null) {
            gridOrganization.setTableWrapper(this);
              gridPaneWrapper = GridPaneWrapper.newBuilder()
                    .setGridLinesVisibility(gridLinesVisibility)
                    .setGridName(gridName)
                    .setOrganization(gridOrganization)
                    .build();
        }
    }

    public ServiceTables getMediator() {
        return serviceTables;
    }

    private void createActiveRecord(Builder builder) {
        try {
            activeRecord= (ActiveRecord) builder.activeRecordClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ObservableList<ActiveRecord> getItems(){
        return element.getItems();
    }
    private void applyListener() {
        if (list != null) {
            list.addListener(tableListener);
        }
    }

    public void setItems(ObservableList<ActiveRecord> items){
        list=items;
        applyListener();
        element.setItems(list);
        element.refresh();
        element.getSelectionModel().focus(0);



    }
    private void applyEditable() {
        element.setEditable(isEditable);
    }
    private void applyPlaceholder() {
        TextWrapper wrapper =TextWrapper.newBuilder()
                .setText("Контент отсутствует".toLowerCase())
                .setFont(FontsStore.ROBOTO_LIGHT)
                .setFontSize(25d)
                .setCSSid(CSSid.PLACEHOLDER)
                .build();

        element.setPlaceholder(wrapper.getElement());
    }
    private void applyColumnResizePolicy(){
        if (columnResizePolicy != null) {
            element.setColumnResizePolicy(columnResizePolicy);
        }
    }
    private void applyColums(){
        if (colWrappers != null) {
            for (ColWrapper cw : colWrappers) {
                element.getColumns().add(cw.getColumn());
                applyColumsSize(cw);
                cw.tableWrapper=this;
            }
        }
        if (colWrapper != null) {
            for (ColWrapper cw : colWrapper) {
                element.getColumns().add(cw.getColumn());
                applyColumsSize(cw);
                cw.tableWrapper=this;
            }
        }
        if (colWrapperPopupViaBtnButYN != null) {
            for (ColWrapperPopupViaBtnButYN.Builder builder : colWrapperPopupViaBtnButYN) {

                builder.setTableWrapper(this);
                ColWrapperPopupViaBtnButYN colWrapperPopupViaBtnButYN1 = builder.build();

                element.getColumns().add(colWrapperPopupViaBtnButYN1.getColumn());
                applyColumsSize(colWrapperPopupViaBtnButYN1);
                colWrapperPopupViaBtnButYN1.tableWrapper=this;
            }
        }
    }
    private void applySortableAllCollums(){
        ObservableList<TableColumn<ActiveRecord, ?>> columns = element.getColumns();
        for (Iterator<TableColumn<ActiveRecord, ?>> iterator = columns.iterator(); iterator.hasNext();) {
            TableColumn<ActiveRecord, ? extends Object> next = iterator.next();
            next.setSortable(isSortableColums);
        }
    }
    private void applyColumsSize(ColWrapper colWrapper) {
        TableColumn column = colWrapper.getColumn();
        column.prefWidthProperty()
                .bind(this.element.widthProperty().multiply(
                        colWrapper.columnSize
                ));
    }
    private void applyTablesWidthProperty() {
        if (widthPercent != null) {
            if (parentWidthProperty != null) {
                element.prefWidthProperty()
                        .bind(parentWidthProperty.multiply(widthPercent));
            }
        }
    }
    public TableView<ActiveRecord> getElement() {
        return element;
    }

    public ObservableList cloneAllPojo()throws CloneNotSupportedException{
        ObservableList<ActiveRecord>  clon=FXCollections.observableArrayList(this.list);
        return clon;
    }
    private void setClickedRowDetection(){
        element.setRowFactory(tv -> {
            TableRow<ActiveRecord> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                        singleClickHandler(row, event);
//                        doubleClickHandler(row, event);
                    }
            );
            return row ;
        });
    }

//    private void doubleClickHandler(TableRow<ActiveRecord> row, MouseEvent event) {
//        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
//                && event.getClickCount() == 2) {
//            clickedDomain=row.getItem();
//
//            if (windowAbstraction != null) {
//                if (windowAbstraction.hasParentWindow()) {
//                    if (windowAbstraction.getCrossWindowMediator() != null) {
//                        windowAbstraction.getCrossWindowMediator().informParentWindow(new ActiveRecordDTO(clickedDomain));
//                    }
//
//
//                }
//            }
//
//        }
//    }

    private void singleClickHandler(TableRow<ActiveRecord> row, MouseEvent event) {
        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                && event.getClickCount() == 1) {
           clickedDomain=row.getItem();
            if (serviceTables != null) {
                serviceTables.inform(this);
            }
        }
    }
    public void fireRowClick(TableRow<ActiveRecord> row) {
        element.getSelectionModel().focus(row.getIndex());
        element.getSelectionModel().select(row.getIndex());
        clickedDomain=row.getItem();
    }

    public void scrollToItem(ActiveRecord domainObject) {
        int newPojoIndex = element.getItems().indexOf(domainObject);
        element.scrollTo(newPojoIndex);
        element.getSelectionModel().select(newPojoIndex);
    }

    public void focusItem(ActiveRecord domainObject) {
        int newPojoIndex = element.getItems().indexOf(domainObject);
        element.scrollTo(newPojoIndex);
        element.getSelectionModel().focus(newPojoIndex);
    }

    public boolean haveNewItem() {
        ObservableList<ActiveRecord> items = element.getItems();
        ActiveRecord activeRecord=null;
        if (items != null) {
            if (items.size()>0) {
                activeRecord = items.get(items.size() - 1);
                return ActiveRecord.isNewDomane(activeRecord);
            }
            if(items.size()==0){
                return false;
            }
        }else {
            throw new NullPointerException();
        }
        return false;
    }

    public boolean  isItemListExist() {
        ObservableList<ActiveRecord> items = element.getItems();
        if ( items!=null) {
            return true;
        }
        return false;
    }

    public void setServiceTables(ServiceTables serviceTables) {
        this.serviceTables = serviceTables;
    }

    public static final class Builder {

        public Coordinate coordinate;
        public WindowAbstraction windowAbstraction;
        public DynamicContentPanel dynamicContentPanel;
        private Class activeRecordClass;
        private ReadOnlyDoubleProperty parentWidthProperty;
        private double widthPercent;
        private ArrayList<AppEvent> events;
        private Double width;
        private Double height;
        private AnchorPane parentAnchor;
        private Group parentGroup;
        private FlowPane parentFlowPane;
        private ScrollPane parentScrollPane;
        private String metaName;
        private Stage stage;
        private boolean isEditable;
        private Callback<TableView.ResizeFeatures, Boolean> columnResizePolicy;
        private boolean isSortableColums;
        private double prefHeight;
        private ServiceTables serviceTables;
        private ColWrapper[] colWrappers;
        private List<ColWrapper> colWrapper;
        private List<ColWrapperPopupViaBtnButYN.Builder> colWrapperPopupViaBtnButYN;
        private UnitOfWork unitOfWork;
        private ActiveRecord clickedDomain;
        private boolean gridLinesVisibility=false;
        private GridOrganization gridOrganization;
        private String gridName;
        private String className;
        protected CSSclasses[] cssClasses;
        protected String[] cssClassesStrings;
        protected List<String> cssClassesStringsList;
        private String cssClass;


        public List<ColWrapperPopupViaBtnButYN.Builder> getColWrapperPopupViaBtnButYN() {
            return colWrapperPopupViaBtnButYN;
        }

        public Builder setColWrapperPopupViaBtnButYN(List<ColWrapperPopupViaBtnButYN.Builder> colWrapperPopupViaBtnButYN) {
            this.colWrapperPopupViaBtnButYN = colWrapperPopupViaBtnButYN;
            return this;
        }

        public Builder setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Builder setCssClasses(CSSclasses...  cssClasses) {
            this.cssClasses = cssClasses;
            return this;
        }

        public Builder setCssClass(String cssClass) {
            this.cssClass = cssClass;
            return this;
        }

        public Builder setCssClassesStrings(String... cssClassesStrings) {
            this.cssClassesStrings = cssClassesStrings;
            return this;
        }
        public Builder setCssClassesStrings(List<String> cssClassesStringsList) {
            this.cssClassesStringsList = cssClassesStringsList;
            return this;
        }

        public  Builder setGridName(String val) {
            gridName = val;
            return this;
        }

        public Builder setWindowAbstraction(WindowAbstraction windowAbstraction) {
            this.windowAbstraction = windowAbstraction;
            return this;
        }

        public void setDynamicContentPanel(DynamicContentPanel dynamicContentPanel) {
            this.dynamicContentPanel = dynamicContentPanel;
        }

        public  Builder setOrganization(GridOrganization gridOrganization) {
            this.gridOrganization = gridOrganization;
            return this;
        }

        public Builder setGridLinesVisibility(boolean gridLinesVisibility) {
            this.gridLinesVisibility = gridLinesVisibility;
            return this;
        }
        public Builder setWidthPercent(double widthPercent) {
            this.widthPercent = widthPercent;
            return this;
        }


        public Builder setSortableColums(boolean sortableColums) {
            isSortableColums = sortableColums;
            return this;
        }

        public Builder setParentWidthProperty(ReadOnlyDoubleProperty property) {
            parentWidthProperty = property;
            return this;
        }



        private Builder() {
        }



        public Builder setEvents(ArrayList<AppEvent> val) {
            events = val;
            return this;
        }

        public Builder setWidth(Double val) {
            width = val;
            return this;
        }

        public Builder setHeight(Double val) {
            height = val;
            return this;
        }

        public Builder setParentAnchor(AnchorPane val) {
            parentAnchor = val;
            return this;
        }

        public Builder setParentGroup(Group val) {
            parentGroup = val;
            return this;
        }

        public Builder setParentFlowPane(FlowPane val) {
            parentFlowPane = val;
            return this;
        }

        public Builder setParentScrollPane(ScrollPane val) {
            parentScrollPane = val;
            return this;
        }

        public Builder setMetaName(String val) {
            metaName = val;
            return this;
        }

        public Builder setStage(Stage val) {
            stage = val;
            return this;
        }

        public Builder setIsEditable(boolean val) {
            isEditable = val;
            return this;
        }

        public Builder setColumnResizePolicy(Callback<TableView.ResizeFeatures, Boolean> val) {
            columnResizePolicy = val;
            return this;
        }

        public Builder setIsSortableColums(boolean val) {
            isSortableColums = val;
            return this;
        }

        public Builder setPrefHeight(double val) {
            prefHeight = val;
            return this;
        }

        public Builder setServiceTables(ServiceTables val) {
            serviceTables = val;
            return this;
        }

        public Builder setColWrappers(ColWrapper... val) {
            colWrappers = val;
            return this;
        }
        public Builder setColWrappers( List<ColWrapper>  val) {
            colWrapper = val;
            return this;
        }

        public Builder setUnitOfWork(UnitOfWork val) {
            unitOfWork = val;
            return this;
        }

        public Builder setClickedDomain(ActiveRecord val) {
            clickedDomain = val;
            return this;
        }

        public Builder setActiveRecordClass(Class activeRecordClass) {
            this.activeRecordClass = activeRecordClass;
            return this;
        }

        public Builder setClass(String s) {
            this.className=s;
            return this;

        }

        public TableWrapper build() {
            return new TableWrapper(this);
        }

    }
}
