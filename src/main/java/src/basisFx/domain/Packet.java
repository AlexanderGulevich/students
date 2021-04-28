package basisFx.domain;


import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Packet extends ActiveRecord {


    private static Packet INSTANCE = new Packet();
    private SimpleObjectProperty<PacketSize> packetSize =new SimpleObjectProperty<>(this, "packetSize", null);
    private SimpleObjectProperty<Counterparty> counterparty =new SimpleObjectProperty<>(this, "counterparty", null);

    public static Packet getINSTANCE() {
        return INSTANCE;
    }

    public PacketSize getPacketSize() {
        return packetSize.get();
    }

    public SimpleObjectProperty<PacketSize> packetSizeProperty() {
        return packetSize;
    }

    public void setPacketSize(PacketSize packetSize) {
        this.packetSize.set(packetSize);
    }

    public Counterparty getCounterparty() {
        return counterparty.get();
    }

    public SimpleObjectProperty<Counterparty> counterpartyProperty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty.set(counterparty);
    }

    @Override
    public String toString() {
        if (getPacketSize() != null) {
            return  getPacketSize().getSize() +" , "+ getCounterparty().getName();
        }
        return  null;
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }
}
