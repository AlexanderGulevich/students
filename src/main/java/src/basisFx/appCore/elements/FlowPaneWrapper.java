package basisFx.appCore.elements;

import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class FlowPaneWrapper extends AppNode{

    private FlowPane element =new FlowPane();
    private Double vGap;
    private Double hGap;
    private Insets insects;
    private DropShadow dropShadow;

    private FlowPaneWrapper(Builder builder) {
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
        vGap = builder.vGap;
        hGap = builder.hGap;
        insects = builder.insects;
        dropShadow = builder.dropShadow;
        windowAbstraction=builder.windowAbstraction;
        setElementToWindowRegistry();

        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();


        bond(this);
        setGap();
        setSize();
        setDropShadow();
        setPadding();
        applyCssId();
        elocateEvents();


    }


    private void setPadding() {
        if(insects!=null) element.setPadding(insects);
    }

    private void setDropShadow() {
        if(dropShadow!=null) element.setEffect(dropShadow);
    }

    private void setSize() {
        if(this.height!=null) {
            element.setPrefHeight(this.height);
        }
        if(this.width!=null) {
            element.setPrefWidth(this.width);
        }
    }

    private void setGap() {
        if (vGap != null) {
            element.setVgap(vGap);
        }
        if (hGap != null) {
            element.setHgap(hGap);
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public javafx.scene.layout.FlowPane getElement() {
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
        private javafx.scene.layout.FlowPane parentFlowPane;
        private ScrollPane parentScrollPane;
        private String metaName;
        private Double vGap;
        private Double hGap;
        private Insets insects;
        private DropShadow dropShadow;
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

        public Builder setParentFlowPane(javafx.scene.layout.FlowPane val) {
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

        public Builder setVGap(Double val) {
            vGap = val;
            return this;
        }

        public Builder setHGap(Double val) {
            hGap = val;
            return this;
        }

        public Builder setInsects(Insets val) {
            insects = val;
            return this;
        }

        public Builder setDropShadow(DropShadow val) {
            dropShadow = val;
            return this;
        }

        public FlowPaneWrapper build() {
            return new FlowPaneWrapper(this);
        }
    }



}
