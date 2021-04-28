package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.annotation.Sorting;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class ProductPrice extends ActiveRecord {

    private static ProductPrice INSTANCE = new ProductPrice();
    private SimpleObjectProperty<Double> price =new SimpleObjectProperty<>(this, "price", null);
    @DataStore (AS_OUTER_ID = true) private SimpleObjectProperty<Integer> productId =new SimpleObjectProperty<>(this, "productId", null);
    @DataStore (SORTING = Sorting.DESC, ANALIZED_DATE = true)private SimpleObjectProperty<LocalDate> startDate =new SimpleObjectProperty<>(this, "startDate", null);

    public static ProductPrice getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return getPrice().toString();
    }
    public Double getPrice() {
        return price.get();
    }

    public SimpleObjectProperty<Double> priceProperty() {
        return price;
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public Integer getProductId() {
        return productId.get();
    }

    public SimpleObjectProperty<Integer> productIdProperty() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId.set(productId);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

}
