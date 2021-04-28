package basisFx.appCore.elements;

import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FontLogic;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.service.ServiceTables;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.ArrayList;

public class ButtonWrapper extends AppNode{

    protected Boolean isActive;
    protected Insets insects;
    protected FontsStore font;
    protected Double fontSize;
    protected Button element;
    protected String string;
    protected Node graphicNode;
    protected ContentDisplay contentDisplay;
    @Setter protected ServiceTables serviceTables;

    private ButtonWrapper(Builder builder) {
        element=new Button();

        events = builder.events;
        CSSid = builder.CSSid;
        width = builder.width;
        height = builder.height;
        coordinate = builder.coordinate;
        parentAnchor = builder.parentAnchor;
        parentHBox = builder.hBox;
        parentVBox = builder.vBox;
        parentGroup = builder.parentGroup;
        parentFlowPane = builder.parentFlowPane;
        parentScrollPane = builder.parentScrollPane;
        text = builder.text;
        stage = builder.stage;
        isActive = builder.isActive;
        insects = builder.insects;
        font = builder.font;
        fontSize = builder.fontSize;
        string = builder.string;
        graphicNode = builder.graphicNode;
        contentDisplay = builder.contentDisplay;
        serviceTables = builder.serviceTables;
        metaName=builder.metaName;

        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();

        windowAbstraction=builder.windowAbstraction;
        setElementToWindowRegistry();

        setId();
        setName();
        setPadding();
        setGraphics();
        setFont();
        setSize();
        bond(this);
        if (serviceTables != null) {
            elocateEvents(serviceTables);
        }else{
            elocateEvents();
        }


    }

    public ServiceTables getServiceTables() {
        return serviceTables;
    }

    public void makeActive() {
        if (isActive != null && isActive==true) {

            element.fire();

        }
    }

    private void setId() {
        if (CSSid != null) {
            element.setId(CSSid.get());
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    @Override
    public Button getElement() {
        return element;
    }


    private void setSize() {
        if(this.height!=null) {
            element.setPrefHeight(this.height);
        }
        if(this.width!=null) {
            element.setPrefWidth(this.width);
        }
    }

    private void setPadding() {
        if(insects!=null){
            element.setPadding(insects);
        }
    }

    private void setFont() {
        if(font!=null && fontSize!=null) {
            element.setFont(FontLogic.loadFont(font,fontSize));
        }
    }

    public void setGraphics(){
        if (graphicNode != null && contentDisplay!=null) {
            element.setGraphic(graphicNode);
            element.setContentDisplay(contentDisplay);
        }
    }

    public void setName(){

        if (text != null
//                && contentDisplay!=null
                ) {
            element.setText(text);
//            element.setContentDisplay(contentDisplay);
        }


    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }


    public static final class Builder {
        private String text;
        private WindowAbstraction windowAbstraction;
        private ArrayList<AppEvent> events;
        private CSSid CSSid;
        private Double width;
        private Double height;
        private Coordinate coordinate;
        private AnchorPane parentAnchor;
        private Group parentGroup;
        private FlowPane parentFlowPane;
        private ScrollPane parentScrollPane;
        private String name;
        private Stage stage;
        private boolean isActive;
        private Insets insects;
        private FontsStore font;
        private double fontSize;
        private String string;
        private Node graphicNode;
        private ContentDisplay contentDisplay;
        private ServiceTables serviceTables;
        private String metaName;
        private CSSclasses[] cssClasses;
        private String[] cssClassesStrings;
        private VBox vBox;
        private HBox hBox;


        public Builder setCssClasses(CSSclasses...  cssClasses) {
            this.cssClasses = cssClasses;
            return this;
        }

        public Builder setCssClassesStrings(String... cssClassesStrings) {
            this.cssClassesStrings = cssClassesStrings;
            return this;
        }

        private Builder() {
        }

        public Builder setWindowAbstraction(WindowAbstraction windowAbstraction) {
            this.windowAbstraction = windowAbstraction;
            return this;
        }

        public Builder setEvents(AppEvent ...val) {
            events=new ArrayList<>();
//            events.addAll(Arrays.asList(val));
            for (AppEvent event:val
                 ) {
                events.add(event);
            }
            return this;
        }

        public ButtonWrapper build() {
            return new ButtonWrapper(this);
        }

        public Builder setCSSid(CSSid val) {
            CSSid = val;
            return this;
        }

        public Builder setServiceTables(ServiceTables serviceTables) {
            this.serviceTables = serviceTables;
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
        public Builder setParentVBox(VBox box) {
            vBox = box;
            return this;
        }

        public Builder setParentHBox(HBox box) {
            hBox = box;
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

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder setStage(Stage val) {
            stage = val;
            return this;
        }

        public Builder setIsActive(boolean val) {
            isActive = val;
            return this;
        }

        public Builder setInsects(Insets val) {
            insects = val;
            return this;
        }

        public Builder setFont(FontsStore val) {
            font = val;
            return this;
        }

        public Builder setFontSize(double val) {
            fontSize = val;
            return this;
        }

        public Builder setString(String val) {
            string = val;
            return this;
        }

        public Builder setGraphicNode(Node val) {
            graphicNode = val;
            return this;
        }

        public Builder setContentDisplay(ContentDisplay val) {
            contentDisplay = val;
            return this;
        }

        public Builder setMetaName(String metaName) {
            this.metaName = metaName;
            return this;
        }
    }
}
