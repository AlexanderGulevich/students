package basisFx.domain;
import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class Counterparty extends ActiveRecord {

    private static Counterparty INSTANCE = new Counterparty();
    private SimpleObjectProperty<String> name =new SimpleObjectProperty(this, "name", null);
    private SimpleObjectProperty<Currency> currency =new SimpleObjectProperty<>(this, "currency", null);


    public static Counterparty getINSTANCE() {
        return INSTANCE;
    }
    public Currency getCurrency() {
        return currency.get();
    }
    public SimpleObjectProperty<Currency> currencyProperty() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency.set(currency);
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
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }

}
