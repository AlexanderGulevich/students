package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Equipment  extends ActiveRecord {

    private static Equipment INSTANCE = new Equipment();
    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(null, "name", null);

    public static Equipment getINSTANCE() {
        return INSTANCE;
    }

    public String getName() {
        return name.get();
    }
    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }



}
