package basisFx.appCore.elements;

import basisFx.appCore.settings.CSSclasses;
import basisFx.appCore.utils.Range;
import basisFx.appCore.interfaces.Mediator;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.Getter;

public class RangeDirector {

    @Getter protected ComboBox<Range> comboBox;
    @Getter private Range selectedRange;

    public RangeDirector(ComboBox<Range> comboBox,
                         Mediator mediator,
                         Range startRange,
                         ObservableList<Range> range) {
        this.comboBox = comboBox;
        comboBox.getStyleClass().add(CSSclasses.COMBOBOX_BFx.name());
        comboBox.getStyleClass().add("COMBOBOX_BFx_forGrid");

        comboBox.setOnAction((e) -> {
            selectedRange=comboBox.getSelectionModel().getSelectedItem();
            mediator.inform(this);
        });

        comboBox.setValue(startRange);
        selectedRange=startRange;

        comboBox.setItems(range);

        setCellFactory();
    }



    private void setCellFactory() {
        comboBox.setCellFactory(
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
                                        if (comboBox != null) {
                                            comboBox.setValue(item );
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

}
