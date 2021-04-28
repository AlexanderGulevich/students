package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.annotation.Sorting;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class PacketPrice extends ActiveRecord {

    private static PacketPrice INSTANCE = new PacketPrice();
    private SimpleObjectProperty<Double> price = new SimpleObjectProperty<>(this, "price", null);
    @DataStore(SORTING = Sorting.DESC, ANALIZED_DATE = true)  private SimpleObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(this, "startDate", null);
    @DataStore (AS_OUTER_ID = true) private SimpleObjectProperty<Integer> packetId = new SimpleObjectProperty<>(this, "packetId", null);

    public static PacketPrice getINSTANCE() {
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

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public Integer getPacketId() {
        return packetId.get();
    }

    public SimpleObjectProperty<Integer> packetIdProperty() {
        return packetId;
    }

    public void setPacketId(Integer packetId) {
        this.packetId.set(packetId);
    }
}
