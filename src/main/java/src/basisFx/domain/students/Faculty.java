package basisFx.domain.students;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

public class Faculty extends ActiveRecord {

    private static Faculty INSTANCE = new Faculty();
    public static Faculty getINSTANCE() {
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