package basisFx.appCore.elements;

import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

public class AnchorWrapper  extends AppNode  {

    protected AnchorPane element;
    protected Insets insects;
    protected FontsStore font;
    protected double fontSize;


    public AnchorPane getElement() {
        return element;
    }

    private AnchorWrapper(Builder builder) {
        element=new AnchorPane();

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
        insects = builder.insects;
        font = builder.font;
        fontSize = builder.fontSize;
        stage=builder.stage;
        windowAbstraction=builder.windowAbstraction;
        setElementToWindowRegistry();
        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();

        bond(this);

        applyWidth();
        applyHeight();
        applyCssId();
        applyPadding();
        elocateEvents();

    }

    private void applyPadding() {
        if (insects != null) {
            element.setPadding(insects);
        }
    }

    private void applyHeight() {
        if (height != null) {
            element.setPrefHeight(height);
        }
    }

    private void applyWidth() {
        if (width != null) {
            element.setPrefWidth(width);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }



    public static final class Builder {
        public WindowAbstraction windowAbstraction;
        private Stage stage;
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
        private Insets insects;
        private FontsStore font;
        private double fontSize;
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

        public Builder setStage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public Builder setEvents(AppEvent ...val) {
            events=new ArrayList<>();
            events.addAll(Arrays.asList(val));
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

        public AnchorWrapper build() {
            return new AnchorWrapper(this);
        }
    }
}
