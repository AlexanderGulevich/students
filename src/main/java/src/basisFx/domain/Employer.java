package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import javafx.beans.property.SimpleObjectProperty;

public class Employer extends ActiveRecord {

    private static Employer INSTANCE = new Employer();
    public static Employer getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<BoolComboBox> isFired =new SimpleObjectProperty<>(this, "isFired", new BoolComboBox(false));


    public String getName() {
        return name.get();
    }
    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public BoolComboBox getIsFired() {
        return isFired.get();
    }

    public SimpleObjectProperty<BoolComboBox> isFiredProperty() {
        return isFired;
    }

    public void setIsFired(BoolComboBox isFired) {
        this.isFired.set(isFired);
    }

    @Override
    public String toString() {
        return getName();
    }




}
