package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class PACKETRESULTFULL extends ActiveRecord {

    private static PACKETRESULTFULL INSTANCE = new PACKETRESULTFULL();
    public static PACKETRESULTFULL getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<LocalDate> OUTDATE =new SimpleObjectProperty<>(this, "OUTDATE", null);
    private SimpleObjectProperty<Packet> PACKET=new SimpleObjectProperty<>(this, "PACKET", null);
    private SimpleObjectProperty<Integer> PRODUCTAMOUNT=new SimpleObjectProperty<>(this, "PRODUCTAMOUNT", null);
    private SimpleObjectProperty<Integer> NUMBERINPACKET=new SimpleObjectProperty<>(this, "NUMBERINPACKET", null);
    private SimpleObjectProperty<Integer> TOTALPACKETAMOUNT=new SimpleObjectProperty<>(this, "TOTALPACKETAMOUNT", null);
    private SimpleObjectProperty<Product> PRODUCT=new SimpleObjectProperty<>(this, "PRODUCT", null);
    private SimpleObjectProperty<PacketSize> PACKETSIZE=new SimpleObjectProperty<>(this, "PACKETSIZE", null);
    private SimpleObjectProperty<Double> PACKETPRICE=new SimpleObjectProperty<>(this, "PACKETPRICE", null);
    private SimpleObjectProperty<Double> TOTALPACKETCOST=new SimpleObjectProperty<>(this, "TOTALPACKETCOST", null);


    @Override
    public String toString() {
        return null;
    }

    public LocalDate getOUTDATE() {
        return OUTDATE.get();
    }

    public SimpleObjectProperty<LocalDate> OUTDATEProperty() {
        return OUTDATE;
    }

    public void setOUTDATE(LocalDate OUTDATE) {
        this.OUTDATE.set(OUTDATE);
    }

    public Packet getPACKET() {
        return PACKET.get();
    }

    public SimpleObjectProperty<Packet> PACKETProperty() {
        return PACKET;
    }

    public void setPACKET(Packet PACKET) {
        this.PACKET.set(PACKET);
    }

    public Integer getPRODUCTAMOUNT() {
        return PRODUCTAMOUNT.get();
    }

    public SimpleObjectProperty<Integer> PRODUCTAMOUNTProperty() {
        return PRODUCTAMOUNT;
    }

    public void setPRODUCTAMOUNT(Integer PRODUCTAMOUNT) {
        this.PRODUCTAMOUNT.set(PRODUCTAMOUNT);
    }

    public Integer getNUMBERINPACKET() {
        return NUMBERINPACKET.get();
    }

    public SimpleObjectProperty<Integer> NUMBERINPACKETProperty() {
        return NUMBERINPACKET;
    }

    public void setNUMBERINPACKET(Integer NUMBERINPACKET) {
        this.NUMBERINPACKET.set(NUMBERINPACKET);
    }

    public Integer getTOTALPACKETAMOUNT() {
        return TOTALPACKETAMOUNT.get();
    }

    public SimpleObjectProperty<Integer> TOTALPACKETAMOUNTProperty() {
        return TOTALPACKETAMOUNT;
    }

    public void setTOTALPACKETAMOUNT(Integer TOTALPACKETAMOUNT) {
        this.TOTALPACKETAMOUNT.set(TOTALPACKETAMOUNT);
    }

    public Product getPRODUCT() {
        return PRODUCT.get();
    }

    public SimpleObjectProperty<Product> PRODUCTProperty() {
        return PRODUCT;
    }

    public void setPRODUCT(Product PRODUCT) {
        this.PRODUCT.set(PRODUCT);
    }

    public PacketSize getPACKETSIZE() {
        return PACKETSIZE.get();
    }

    public SimpleObjectProperty<PacketSize> PACKETSIZEProperty() {
        return PACKETSIZE;
    }

    public void setPACKETSIZE(PacketSize PACKETSIZE) {
        this.PACKETSIZE.set(PACKETSIZE);
    }

    public Double getPACKETPRICE() {
        return PACKETPRICE.get();
    }

    public SimpleObjectProperty<Double> PACKETPRICEProperty() {
        return PACKETPRICE;
    }

    public void setPACKETPRICE(Double PACKETPRICE) {
        this.PACKETPRICE.set(PACKETPRICE);
    }

    public Double getTOTALPACKETCOST() {
        return TOTALPACKETCOST.get();
    }

    public SimpleObjectProperty<Double> TOTALPACKETCOSTProperty() {
        return TOTALPACKETCOST;
    }

    public void setTOTALPACKETCOST(Double TOTALPACKETCOST) {
        this.TOTALPACKETCOST.set(TOTALPACKETCOST);
    }
}
