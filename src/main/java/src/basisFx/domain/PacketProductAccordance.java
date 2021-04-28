package basisFx.domain;


import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.dataSource.Db;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PacketProductAccordance extends ActiveRecord {

    private static PacketProductAccordance INSTANCE = new PacketProductAccordance();
    private SimpleObjectProperty<Product> product = new SimpleObjectProperty<>(this, "product", null);
    private SimpleObjectProperty<PacketSize> packetSize = new SimpleObjectProperty<>(this, "packetSize", null);
    private SimpleObjectProperty<Integer> number = new SimpleObjectProperty<>(this, "number", null);

    public static PacketProductAccordance getINSTANCE() {
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

    public Product getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Product> productProperty() {
        return product;
    }

    public void setProduct(Product product) {
        this.product.set(product);
    }

    public Integer getNumber() {
        return number.get();
    }

    public SimpleObjectProperty<Integer> numberProperty() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id){

    ObservableList<ActiveRecord> list = createNewActiveRecordList();
    String expression = "SELECT * FROM " + this.entityName + " where packetSizeId= " + id;
        try {
        Statement stmt = Db.connection.createStatement();
        ResultSet rs = stmt.executeQuery(expression);
        while (rs.next()) {
            PacketProductAccordance pojo = new PacketProductAccordance();
            pojo.setId(rs.getInt("id"));
            pojo.setNumber(rs.getInt("number"));
            pojo.setProduct((Product) Product.getINSTANCE().find(rs.getInt("productId")));
            list.add(pojo);
        }
    } catch (
    SQLException e) {
        e.printStackTrace();
    }
        return list;

    }
}
