package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.appCore.utils.ImgUtils;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.domain.Counterparty;
import basisFx.domain.Example;
import basisFx.domain.Packet;
import basisFx.domain.PacketSize;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.compress.utils.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class Img extends ActiveRecord {

    private static Img INSTANCE = new Img();
    public static Img getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<String> barcode =new SimpleObjectProperty<>(this, "barcode", null);

    private SimpleObjectProperty<InputStream> imgBig =new SimpleObjectProperty<>(this, "imgBig", null);
    private SimpleObjectProperty<InputStream> imgSmall =new SimpleObjectProperty<>(this, "imgSmall", null);

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleObjectProperty<String> barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    @Override
    public String toString() {
        return null;
    }

    public InputStream getImgBig() {
        return imgBig.get();
    }

    public SimpleObjectProperty<InputStream> imgBigProperty() {
        return imgBig;
    }

    public void setImgBig(InputStream imgBig) {
        this.imgBig.set(imgBig);
    }

    public InputStream getImgSmall() {
        return imgSmall.get();
    }

    public SimpleObjectProperty<InputStream> imgSmallProperty() {
        return imgSmall;
    }

    public void setImgSmall(InputStream imgSmall) {
        this.imgSmall.set(imgSmall);
    }

    @Override
    public ObservableList<ActiveRecord> getAll() {
//        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
//        String expression="SELECT * FROM " +this.entityName+" ORDER BY ID";
//        try {
//            Statement stmt  = Db.connection.createStatement();
//            ResultSet rs    = stmt.executeQuery(expression);
//            while (rs.next()) {
//                Img pojo=new Img();
//                pojo.setId(rs.getInt("id"));
//                pojo.setBarcode(rs.getString("barcode"));
//                pojo.setImgBig(rs.getBlob("barcode"));
//
//
//
//                list.add(pojo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
        return null;
    }

    @Override
    public void insert()  {

        try {
            String expression = "INSERT INTO " + this.entityName
                    + "("
                    + " barcode,  "
                    + " imgBig,   "
                    + " imgSmall   "
                    + ") VALUES(?,?,?)";

            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, getBarcode());
            pstmt.setBlob(2, getImgBig());
            pstmt.setBlob(3, getImgSmall());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update() {
        String expression = "UPDATE " + this.entityName + " SET  " +
                " barcode = ?," +
                " imgBig = ?," +
                " imgSmall = ?" +
                " WHERE barcode= ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, getBarcode());
            pstmt.setBlob(2, getImgBig());
            pstmt.setBlob(3, getImgSmall());
            pstmt.setString(4, getBarcode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Img find(PriceItem priceItem)  {

        String barcode = priceItem.getBarcode();
        Img pojo=new Img() ;
        String expression="SELECT * FROM " +entityName+" WHERE barcode=?";
        boolean hasInner=false;
        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                hasInner=true;
                pojo.setId(rs.getInt("id"));
                pojo.setBarcode(barcode);

                try {

                    Blob image_blob_big=rs.getBlob("imgBig");
                    int blobLength_big = (int) image_blob_big.length();
                    byte[] blobAsBytes_big = image_blob_big.getBytes(1, blobLength_big);
                    InputStream in_big=new ByteArrayInputStream( blobAsBytes_big );
                    BufferedImage bufferedImageBig = ImageIO.read(in_big);
                    if (bufferedImageBig != null) {

                        pojo.setImgBig(ImgUtils.toInputStream(bufferedImageBig));
                    }

                    Blob image_blob_small=rs.getBlob("imgSmall");
                    int blobLength_small = (int) image_blob_small.length();
                    byte[] blobAsBytes_small = image_blob_small.getBytes(1, blobLength_small);
                    InputStream in_small=new ByteArrayInputStream( blobAsBytes_small );
                    BufferedImage bufferedImageSmall = ImageIO.read(in_small);
                    if (bufferedImageSmall != null) {

                        pojo.setImgSmall(ImgUtils.toInputStream(bufferedImageSmall));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            Registry.windowFabric.infoWindow("Ошибка в загрузке картинки из БД\n\n"+e.getMessage());
        }

        if (hasInner){
            return pojo;
        }else {
            return null;
        }



    }
}
