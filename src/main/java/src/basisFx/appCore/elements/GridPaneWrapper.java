package basisFx.appCore.elements;
import basisFx.appCore.events.AppEvent;
import basisFx.appCore.grid.GridOrganization;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public  class GridPaneWrapper extends AppNode {
    protected GridPane element;
    protected Boolean gridLinesVisibility;
    protected GridOrganization gridOrganization;
    public LabelWrapper label;
    private ArrayList <ColumnConstraints> column ;
    private ArrayList <RowConstraints> rows=new ArrayList<>();

    private GridPaneWrapper(Builder builder) {
        element=new GridPane();

        events = builder.events;
        CSSid = builder.CSSid;
        width = builder.width;
        height = builder.height;
        coordinate = builder.coordinate;
        parentAnchor = builder.parentAnchor;
        parentGroup = builder.parentGroup;
        parentFlowPane = builder.parentFlowPane;
        parentScrollPane = builder.parentScrollPane;
        text = builder.gridName;
        stage = builder.stage;
        column=builder.columns;
        gridLinesVisibility=builder.gridLinesVisibility;
        gridOrganization =builder.gridOrganization;
        windowAbstraction=builder.windowAbstraction;

        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();

        setElementToWindowRegistry();
        applyLabel();
        applyRows();
        applyColums();
        bond(this);
        applyLineVisibility();
        applyCssId();
        applyGridConfiguration();
        apllyBindingByParentHeight();
    }

    public LabelWrapper getLabel() {
        return label;
    }

    private void apllyBindingByParentHeight() {
        if (parentAnchor != null) {
            bindGridToParentAnchorHeight();
        }
        if (parentScrollPane != null) {
            bindGridToParentScrollPaneHeight();
        }
        if (parentFlowPane != null) {
            bindGridToParentFlowHeight();
        }
    }

    private void bindGridToParentAnchorHeight() {
        parentAnchor.heightProperty().addListener((observable, oldValue, newValue) -> {
            element.setPrefHeight(newValue.doubleValue()-10d);
            System.err.println(newValue.doubleValue());

        });
    }
    private void bindGridToParentFlowHeight() {
        parentFlowPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            element.setPrefHeight(newValue.doubleValue()-10d);
            System.err.println(newValue.doubleValue());

        });
    }
    private void bindGridToParentScrollPaneHeight() {
        parentScrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            element.setPrefHeight(newValue.doubleValue()-10d);
            System.err.println(newValue.doubleValue());

        });
    }


    private void applyGridConfiguration() {
        if (gridOrganization != null) {
            gridOrganization.setParentGridWrapper(this);
            gridOrganization.organize();
        }
    }

    private void applyLineVisibility() {
        if (gridLinesVisibility != null) {
            element.setGridLinesVisible(gridLinesVisibility);
        }else{
            element.setGridLinesVisible(false);
        }
    }

    private void applyColums() {
        for (ColumnConstraints columnConstraints:column) {

            element.getColumnConstraints().add(columnConstraints);
        }
    }
    private void applyRows() {
        for (RowConstraints rowConstraints:rows) {

            element.getRowConstraints().add(rowConstraints);
        }
    }

    private void applyLabel() {

        if (text != null) {
            label =LabelWrapper.newBuilder()
                    .setCSSid(CSSid.LABEL_TEXT)
                    .setText(text)
                    .setFont(FontsStore.ROBOTO_LIGHT)
                    .setAlignment(Pos.BASELINE_LEFT)
                    .setFontSize(20d)
                    .build();


        }


    }
    public void tableHeightSwitchingByGrid(TableView tableView) {

        element.heightProperty().addListener((obs, oldVal, newVal) -> {

            tableView.setPrefHeight(element.getHeight());
        });

    }
    public void tableWidthSwitchingByGrid(TableView tableView) {

        element.widthProperty().addListener((obs, oldVal, newVal) -> {

            tableView.setPrefWidth(element.getWidth());
        });

    }

    public static Builder newBuilder() {
        return new Builder();
    }




    public void setRowConstraints(){
        RowConstraints rc = new RowConstraints();
        element.getRowConstraints().add(rc);

    }

    public void setColumnVsPercent(double percentWidth){
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(percentWidth);
        element.getColumnConstraints().add(column);

    }

    public void setColumnFixed( double width ){
        ColumnConstraints column = new ColumnConstraints();
        column.setPrefWidth(width);
        element.getColumnConstraints().add(column);


    }
    public void setColumnWidthByContent( ){
        ColumnConstraints column = new ColumnConstraints();
        element.getColumnConstraints().add(column);
    }

    public void setColumnComputerWidth(  ){
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow( Priority.ALWAYS );
        element.getColumnConstraints().add(column);



    }


    //добавляет элемент, который будет для нескольких колонок
    public void addSpanNode(Node child,int columnIndex,int rowIndex,int colspan,int rowspan,HPos halignment, VPos valignment,Insets insets){


        element.add( child, columnIndex, rowIndex, colspan, rowspan);
        setConstraints(child, halignment,  valignment);
        setMargin(child,insets);

    }


    private void setConstraints(Node child,HPos halignment, VPos valignment){
        GridPane.setValignment(child,valignment);
        GridPane.setHalignment(child,halignment);
    }

    private void setMargin(Node child, Insets in){
        GridPane.setMargin(child,in);

    }


    @Override
    public GridPane getElement() {
        return element;
    }


    public static final class Builder {
        public WindowAbstraction windowAbstraction;
        private ArrayList<AppEvent> events;
        private CSSid CSSid;
        private Double width;
        private Double height;
        private Coordinate coordinate;
        private AnchorPane parentAnchor;
        private Group parentGroup;
        private FlowPane parentFlowPane;
        private ScrollPane parentScrollPane;
        private String gridName;
        private Stage stage;
        private ArrayList <ColumnConstraints> columns=new ArrayList<>();
        private ArrayList <RowConstraints> rows=new ArrayList<>();
        private boolean gridLinesVisibility;
        private GridOrganization gridOrganization;
        protected CSSclasses[] cssClasses;
        protected String[] cssClassesStrings;


        public void setCssClasses(CSSclasses...  cssClasses) {
            this.cssClasses = cssClasses;
        }

        public void setCssClassesStrings(String... cssClassesStrings) {
            this.cssClassesStrings = cssClassesStrings;
        }

        public Builder setWindowAbstraction(WindowAbstraction windowAbstraction) {
            this.windowAbstraction = windowAbstraction;
            return this;
        }

        public Builder setGridLinesVisibility(boolean gridLinesVisibility) {
            this.gridLinesVisibility = gridLinesVisibility;
            return this;
        }

        public Builder setOrganization(GridOrganization gridOrganization) {
            this.gridOrganization = gridOrganization;
            return this;
        }

        private Builder() {
        }

        public Builder setEvents(ArrayList<AppEvent> val) {
            events = val;
            return this;
        }

        public Builder setCSSid(CSSid val) {
            CSSid = val;
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

        public Builder setCoordinate(Coordinate val) {
            coordinate = val;
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

        public Builder setGridName(String val) {
            gridName = val;
            return this;
        }

        public Builder setStage(Stage val) {
            stage = val;
            return this;
        }

        public Builder setColumnVsPercent(double percentWidth){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(percentWidth);
            columns.add(column);
            return this;

        }

        public Builder setColumnFixed( double width ){
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(width);
            columns.add(column);
            return this;


        }
        public Builder setColumnWidthByContent( ){
            ColumnConstraints column = new ColumnConstraints();
            columns.add(column);
            return this;


        }


        public Builder setColumnComputerWidth(  ){
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow( Priority.ALWAYS );
            columns.add(column);
            return this;



        }

        public Builder setRowVsPercent(double percentHeight){
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(percentHeight);
            rows.add(rc);
            return this;

        }






        public GridPaneWrapper build() {
            return new GridPaneWrapper(this);
        }
    }
}
