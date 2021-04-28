package basisFx.appCore.elements;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lombok.Getter;

import java.util.Optional;

public class ComboboxAdapter {

    @Getter
    private ComboBox comboBox;
    private Mediator mediator;
    @Getter
    private ActiveRecord comboboxRecords;
    @Getter
    private ActiveRecord selected;
    ObservableList<ActiveRecord> all;

    public ComboboxAdapter(ComboBox comboBox, Mediator mediator, ActiveRecord comboboxRecords) {
        this.comboBox = comboBox;
        this.mediator = mediator;
        this.comboboxRecords = comboboxRecords;

        refresh();

        comboBox.setOnAction((e) -> {
            selected= (ActiveRecord) comboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mediator.inform(comboBox);
            }
        });


    }

    public void choiceItem(ActiveRecord record){
        comboBox.getSelectionModel().select(record);
        selected= (ActiveRecord) comboBox.getSelectionModel().getSelectedItem();
        mediator.inform(comboBox);
    }
    public void choiceItemById(ActiveRecord r){
        Optional<ActiveRecord> any = all.stream().filter(record1 -> r.getId().equals(record1.getId())).findFirst();
        selected = any.get();
        if (selected == null) throw new NullPointerException() ;
        comboBox.getSelectionModel().select(selected);
//        selected= (ActiveRecord) comboBox.getSelectionModel().getSelectedItem();
        mediator.inform(comboBox);
    }

    public void refresh() {
        all = comboboxRecords.getAll();
        comboBox.setItems(all);
    }


}
