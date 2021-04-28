package basisFx.appCore.elements;

import basisFx.appCore.events.AppEvent;
import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.settings.CSSid;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Range;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.service.ServiceTables;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class ComboBoxWrapper  extends AppNode {

    protected ComboBox <Range> element;
    protected Range startRange;
    protected String string;
    protected ServiceTables serviceTables;
    private ObservableList<Range> comboboxValues;
    private Range selectedRange;


    private ComboBoxWrapper(Builder builder) {

        events = builder.events;
        startRange=builder.startRange;
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
        text = builder.text;
        stage = builder.stage;
        string = builder.string;
        startRange = builder.startRange;
        serviceTables = builder.serviceTables;
        comboboxValues=builder.comboboxValues;
        element = new ComboBox <>(comboboxValues);

        getElement().getStyleClass().add(CSSclasses.COMBOBOX_BFx.get());
        element.setEditable(false);
        windowAbstraction=builder.windowAbstraction;
        setElementToWindowRegistry();
        setValue();
        setCellFactory();

        cssClassesStrings=builder.cssClassesStrings;
        cssClasses=builder.cssClasses;
        applyCssClasses();


        element.setOnAction((e) -> {
            selectedRange=element.getSelectionModel().getSelectedItem();
            serviceTables.inform(this);
        });

        setId();
        bond(this);
        elocateEvents();

    }

    public Range getSelectedRange() {
        return selectedRange;
    }

    private void setValue() {
        if (startRange != null) {
            element.setValue(startRange);
        }
    }

    private void setCellFactory() {
        element.setCellFactory(
                new Callback<ListView<Range>, ListCell<Range>>() {
                    @Override public ListCell<Range> call(ListView<Range> param) {
                        final ListCell<Range> cell = new ListCell<Range>() {
                            {
                                super.setPrefWidth(200);
                            }
                            @Override
                            public void updateItem(Range item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item == null || empty) {
                                    setText(null);
                                } else {
                                    if (isEditing()) {
                                        if (element != null) {
                                            element.setValue(item );
                                        }
                                        String s = item.getName();
                                        if (s != null) {
                                            s=" "+s;
                                        }
                                        setText(s);
                                    } else {
                                        String s= item.getName();
                                            if (s != null) {
                                                s=" "+s;
                                            }
                                        setText(s);
                                    }
                                }
                            }

                        };
                        return cell;
                    }});
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
    public Node getElement() {
        return element;
    }


    public static final class Builder {
        public Range startRange;
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
        private String text;
        private Stage stage;
        private String string;
        private ServiceTables serviceTables;
        private ObservableList<Range> comboboxValues;
        protected CSSclasses[] cssClasses;
        protected String[] cssClassesStrings;


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

        public void setWindowAbstraction(WindowAbstraction windowAbstraction) {
            this.windowAbstraction = windowAbstraction;
        }

        public Builder setStartRange(Range startRange) {
            this.startRange = startRange;
            return this;
        }

        public Builder setComboboxValues(ObservableList<Range> comboboxValues) {
            this.comboboxValues = comboboxValues;
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

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder setStage(Stage val) {
            stage = val;
            return this;
        }

        public Builder setString(String val) {
            string = val;
            return this;
        }

        public Builder setServiceTables(ServiceTables val) {
            serviceTables = val;
            return this;
        }

        public ComboBoxWrapper build() {
            return new ComboBoxWrapper(this);
        }
    }
}



