package basisFx.appCore.elements;

import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.FontLogic;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public  class TextAreaWrapper extends AppNode{

    protected TextArea element=new TextArea();
    protected Boolean isEditable;
    protected Boolean isWrapText=true;
    protected FontsStore font;
    protected Double fontSize;
    protected String text;

    private TextAreaWrapper(Builder builder) {
        events = builder.events;
        CSSid = builder.CSSid;
        width = builder.width;
        height = builder.height;
        coordinate = builder.coordinate;
        parentAnchor = builder.parentAnchor;
        parentGroup = builder.parentGroup;
        parentFlowPane = builder.parentFlowPane;
        parentScrollPane = builder.parentScrollPane;
        metaName = builder.metaName;
        stage = builder.stage;
        isEditable = builder.isEditable;
        isWrapText = builder.isWrapText;
        font = builder.font;
        fontSize = builder.fontSize;
        text = builder.text;
        windowAbstraction=builder.windowAbstraction;
        setElementToWindowRegistry();

        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();



        setText();
        setEditable();
        setFont();
        setWrapText();
        elocateEvents();
        applyCssId();
        bond(this);



    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private void setText() {
        if (text != null) {
            element.setText(text);
        }
    }

    private void setFont() {
        if (font != null && fontSize != null) {
            Font f = FontLogic.loadFont(font, fontSize);
            element.setFont(f);
        }
    }

    private void setWrapText() {
        if (isWrapText != null) {

            element.setWrapText(isWrapText);
        }
    }





    @Override
    public TextArea getElement() {
        return element;
    }

    private void setEditable() {
        if (isEditable != null) {
            element.setEditable(isEditable);
        }
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
        private String metaName;
        private Stage stage;
        private Boolean isEditable;
        private Boolean isWrapText;
        private FontsStore font;
        private Double fontSize;
        private String text;
        protected CSSclasses[] cssClasses;
        protected String[] cssClassesStrings;


        public void setCssClasses(CSSclasses...  cssClasses) {
            this.cssClasses = cssClasses;
        }

        public void setCssClassesStrings(String... cssClassesStrings) {
            this.cssClassesStrings = cssClassesStrings;
        }

        private Builder() {
        }

        public Builder setWindowAbstraction(WindowAbstraction windowAbstraction) {
            this.windowAbstraction = windowAbstraction;
            return this;
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

        public Builder setMetaName(String val) {
            metaName = val;
            return this;
        }

        public Builder setStage(Stage val) {
            stage = val;
            return this;
        }

        public Builder setIsEditable(Boolean val) {
            isEditable = val;
            return this;
        }

        public Builder setIsWrapText(Boolean val) {
            isWrapText = val;
            return this;
        }

        public Builder setFont(FontsStore val) {
            font = val;
            return this;
        }

        public Builder setFontSize(Double val) {
            fontSize = val;
            return this;
        }

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public TextAreaWrapper build() {
            return new TextAreaWrapper(this);
        }
    }
}
