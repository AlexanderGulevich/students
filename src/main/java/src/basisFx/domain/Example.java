package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.dataSource.Db;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class Example extends ActiveRecord {
    private SimpleObjectProperty<String> name =new SimpleObjectProperty(this, "name", null);
    private SimpleObjectProperty<Currency> currency =new SimpleObjectProperty<>(this, "currency", null);
    private SimpleObjectProperty<PacketSize> packetSize =new SimpleObjectProperty<>(this, "packetSize", null);
    private SimpleObjectProperty<Counterparty> counterparty =new SimpleObjectProperty<>(this, "counterparty", null);
    private SimpleObjectProperty<BoolComboBox> havingSleeve =new SimpleObjectProperty<>(this, "havingSleeve", null);
    private SimpleObjectProperty<Equipment> equipment =new SimpleObjectProperty<>(this, "equipment", null);
    private SimpleObjectProperty<Product> product=new SimpleObjectProperty<>(this, "product", null);
    private SimpleObjectProperty<Integer> rodsNumber=new SimpleObjectProperty<>(this, "rodsNumber", null);
    private SimpleObjectProperty<Jumbo> jumbo=new SimpleObjectProperty<>(this, "Jumbo", null);
    private SimpleObjectProperty<Packet> packet=new SimpleObjectProperty<>(this, "packet", null);
    private SimpleObjectProperty<Counterparty> packetCounterparty=new SimpleObjectProperty<>(this, "packetCounterparty", null);
    private SimpleObjectProperty<Counterparty> paperCounterparty=new SimpleObjectProperty<>(this, "paperCounterparty", null);
    private SimpleObjectProperty<LocalDate> date =new SimpleObjectProperty<>(this, "date", null);

    public Equipment getEquipment() {
        return equipment.get();
    }

    public SimpleObjectProperty<Equipment> equipmentProperty() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment.set(equipment);
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

    public Integer getRodsNumber() {
        return rodsNumber.get();
    }

    public SimpleObjectProperty<Integer> rodsNumberProperty() {
        return rodsNumber;
    }

    public void setRodsNumber(Integer rodsNumber) {
        this.rodsNumber.set(rodsNumber);
    }

    public Jumbo getJumbo() {
        return jumbo.get();
    }

    public SimpleObjectProperty<Jumbo> jumboProperty() {
        return jumbo;
    }

    public void setJumbo(Jumbo jumbo) {
        this.jumbo.set(jumbo);
    }

    public Packet getPacket() {
        return packet.get();
    }

    public SimpleObjectProperty<Packet> packetProperty() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet.set(packet);
    }

    public Counterparty getPacketCounterparty() {
        return packetCounterparty.get();
    }

    public SimpleObjectProperty<Counterparty> packetCounterpartyProperty() {
        return packetCounterparty;
    }

    public void setPacketCounterparty(Counterparty packetCounterparty) {
        this.packetCounterparty.set(packetCounterparty);
    }

    public Counterparty getPaperCounterparty() {
        return paperCounterparty.get();
    }

    public SimpleObjectProperty<Counterparty> paperCounterpartyProperty() {
        return paperCounterparty;
    }

    public void setPaperCounterparty(Counterparty paperCounterparty) {
        this.paperCounterparty.set(paperCounterparty);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public BoolComboBox getHavingSleeve() {
        return havingSleeve.get();
    }

    public SimpleObjectProperty<BoolComboBox> havingSleeveProperty() {
        return havingSleeve;
    }

    public void setHavingSleeve(BoolComboBox havingSleeve) {
        this.havingSleeve.set(havingSleeve);
    }

    private static Example INSTANCE = new Example();

    public static Example getINSTANCE() {
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

    public Currency getCurrency() {
        return currency.get();
    }

    public SimpleObjectProperty<Currency> currencyProperty() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency.set(currency);
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
    public ObservableList<ActiveRecord> getAll() {
        ObservableList <ActiveRecord> list=FXCollections.observableArrayList();
        String expression="SELECT * FROM " +this.entityName+" ORDER BY ID";
        try {
            Statement stmt  = Db.connection.createStatement();
            ResultSet rs    = stmt.executeQuery(expression);
            while (rs.next()) {
                Example pojo=new Example();
                pojo.setId(rs.getInt("id"));
                pojo.setPacketSize((PacketSize) PacketSize.getINSTANCE().find(rs.getInt("packetSizeId")));
                pojo.setCounterparty((Counterparty) Counterparty.getINSTANCE().find(rs.getInt("counterpartyId")));
                pojo.setHavingSleeve(new BoolComboBox(rs.getBoolean("havingSleeve") )  );
                list.add(pojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void update() {
        String expression = "UPDATE " + this.entityName + " SET  " +
                " name = ?," +
                " currencyId = ?" +
                " WHERE id= ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, name.get());
            pstmt.setInt(2, currency.get().getId());
            pstmt.setInt(3, id.get());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Packet find(int id) {
        Packet pojo=new Packet() ;
        String expression="SELECT * FROM " +entityName+" WHERE ID=?";

        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                pojo.setId(rs.getInt("id"));
                pojo.setPacketSize((PacketSize) PacketSize.getINSTANCE().find(rs.getInt("packetSizeId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojo;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void insert() {
        try {
            String expression = "INSERT INTO " + this.entityName
                    + "("
                    + " EquipmentId ,  "
                    + " productId  ,  "
                    + " rodsNumber  ,  "
                    + " rodsWidth  ,  "
                    + " packetId ,  "
                    + " packetCounterpartyId,  "
                    + " paperCounterpartyId,  "
                    + " date   "
                    + ") VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setInt(1, getEquipment().getId());
            pstmt.setInt(2, getProduct().getId());
            pstmt.setInt(3, getRodsNumber());
            pstmt.setInt(4, getJumbo().getId());
            pstmt.setInt(5, getPacket().getId());
            pstmt.setInt(6, getPacketCounterparty().getId());
            pstmt.setInt(7, getPaperCounterparty().getId());
            pstmt.setDate(8, Date.valueOf(getDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }
}
