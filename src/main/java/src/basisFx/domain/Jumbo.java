package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.Setter;


public class Jumbo extends ActiveRecord {

    private static Jumbo INSTANCE = new Jumbo();
    private SimpleObjectProperty<Integer> width = new SimpleObjectProperty<>(this, "width", null);
    private SimpleObjectProperty<Integer> numberOfProduct = new SimpleObjectProperty<>(this, "numberOfProduct", null);

    public static Jumbo getINSTANCE() {
        return INSTANCE;
    }

    public Integer getWidth() {
        return width.get();
    }

    public SimpleObjectProperty<Integer> widthProperty() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width.set(width);
    }

    public Integer getNumberOfProduct() {
        return numberOfProduct.get();
    }

    public SimpleObjectProperty<Integer> numberOfProductProperty() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(Integer numberOfProduct) {
        this.numberOfProduct.set(numberOfProduct);
    }

    @Override
    public String toString() {
        if (getWidth() != null) {

            return String.valueOf(getWidth());
        }
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {

        return null;

    }
}