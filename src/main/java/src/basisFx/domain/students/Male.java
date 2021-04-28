package basisFx.domain.students;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

public class Male extends ActiveRecord {

    private static Male INSTANCE = new Male();
    public static Male getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);


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




}