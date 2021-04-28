package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.BFxPreparedStatement;
import basisFx.dataSource.Db;
import basisFx.service.price.ServicePanelTableViewer;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class PriceItem extends ActiveRecord {
    private static PriceItem INSTANCE = new PriceItem();


    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true)  private SimpleObjectProperty<String> name=
            new SimpleObjectProperty<>(this, "name", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true)  private SimpleObjectProperty<String> barcode=
            new SimpleObjectProperty<>(this, "barcode", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true)  private SimpleObjectProperty<String> alias=
            new SimpleObjectProperty<>(this, "alias", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true)  private SimpleObjectProperty<String> orderNumber=
            new SimpleObjectProperty<>(this, "orderNumber", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<String> pure_order=
            new SimpleObjectProperty<>(this, "pure_order", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<String> measure=
            new SimpleObjectProperty<>(this, "measure", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<String> amountInBox=
            new SimpleObjectProperty<>(this, "amountInBox", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<StoredCategory> storedCategory=
            new SimpleObjectProperty<>(this, "storedCategory",  null );
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<Double> amountInPrice=
            new SimpleObjectProperty<>(this, "amountInPrice", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<Double> pricePerUnit=
            new SimpleObjectProperty<>(this, "pricePerUnit", null);
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true) private SimpleObjectProperty<BoolComboBox> visibitity=
            new SimpleObjectProperty<>(this, "visibitity", new BoolComboBox(true));
    @DataStore(NOT_CHECK_FOR_TRANSACTIONS = true)private SimpleObjectProperty<Img> img=
            new SimpleObjectProperty<>(this, "img", null);


    public static PriceItem getINSTANCE() {
        return INSTANCE;
    }
    @Override
    public String toString() {
        if (getAlias() != null) {
            return getAlias();
        }
        return getName();
    }



    @Override
    public ObservableList<ActiveRecord> getAll() {
        Price price = (Price) Registry.dataExchanger.get("price");
        if (price != null) {
//            boolean filledItems = price.isFilledItems();
            ObservableList<ActiveRecord> priceAllRecords = price.getAllRecords();
//            if (!filledItems) {
//                priceAllRecords.forEach((record -> {
//                    PriceItem priceItem = (PriceItem) record;
//
//                    priceItem.setImg( Img.getINSTANCE().find(priceItem) );
//                    priceItem.setStoredCategory(priceItem.findStoredCategory() );
//                    priceItem.setAlias(priceItem.findAlias() );
//                    priceItem.setVisibitity(new BoolComboBox(priceItem.findVisibility()));
//                }));
//                price.setFilledItems(true);
//            }
            return priceAllRecords;
        } Registry.windowFabric.infoWindow("Чтобы отобразить данные сначала загрузите прайс!");
        return null;
    }
    public ObservableList<ActiveRecord> getAllFullData() {
        Price price = (Price) Registry.dataExchanger.get("price");
        if (price != null) {
            ObservableList<ActiveRecord> priceAllRecords = price.getAllRecords();
            List<ActiveRecord> collect = priceAllRecords.stream().peek((record -> {
                PriceItem priceItem = (PriceItem) record;

                priceItem.setImg(Img.getINSTANCE().find(priceItem));
                priceItem.setStoredCategory(priceItem.findStoredCategory());
                priceItem.setAlias(priceItem.findAlias());
                priceItem.setVisibitity(new BoolComboBox(priceItem.findVisibility()));
            })).collect(Collectors.toList());

            ObservableList<ActiveRecord> priceAllRecordsNew=FXCollections.observableArrayList();
            priceAllRecordsNew.addAll(collect);
            price.setAllRecords(priceAllRecordsNew);

            return priceAllRecordsNew;
        } Registry.windowFabric.infoWindow("Чтобы отобразить данные сначала загрузите прайс!");
        return null;
    }

    public ObservableList<ActiveRecord> getAllFromDB() {
        if (Price.allFromDB!= null && !ServicePanelTableViewer.bindRecordsHasChanged) {
            return Price.allFromDB;
        }
        ObservableList<ActiveRecord> list = FXCollections.observableArrayList();
        String expression="SELECT * FROM " +this.entityName+" ORDER BY ID";
        try {
            Statement stmt  = Db.connection.createStatement();
            ResultSet rs    = stmt.executeQuery(expression);
            while (rs.next()) {
                PriceItem pojo=new PriceItem();
                pojo.setId(rs.getInt("id"));
                pojo.setVisibitity(new BoolComboBox(rs.getBoolean("visibitity")));
                pojo.setAlias(rs.getString("alias"));
                pojo.setStoredCategory((StoredCategory) StoredCategory.getINSTANCE().find(rs.getInt("storedCategoryId")));
                pojo.setPure_order(rs.getString("pure_order"));
                pojo.setBarcode(rs.getString("barcode"));
                pojo.setImg((Img) Img.getINSTANCE().find(rs.getInt("imgId")));
                pojo.setName(rs.getString("name"));
                pojo.setOrderNumber(rs.getString("orderNumber"));
                list.add(pojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Price.allFromDB=list;
        return list;
    }


    @Override
    public void update() {

        Boolean byBarcodeAndPureOrder = findByBarcodeAndPureOrder();
        if (byBarcodeAndPureOrder) {
            updateBuOrderAndBarcode();
        }
        else if(findByPureOrder() && !byBarcodeAndPureOrder){
            updateByPureOrder();
        }


    }

    private void updateBuOrderAndBarcode() {
        String expression = "UPDATE " + this.entityName + " SET  "
                + " storedCategoryId =?,  "
                + " imgId  =?,  "
                + " name  =?,  "
                + " alias  =?,  "
                + " orderNumber=?,  "
                + " visibitity =?  "
                +" WHERE barcode= ? and pure_order =?";
        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);

        Integer id_StoredCategory = null;
        if (getStoredCategory() != null && getStoredCategory().getId()!=null) {
            id_StoredCategory=getStoredCategory().getId();
                pstmt.setInt(1, id_StoredCategory);
        }else {
            pstmt.setNull(1, Types.INTEGER );
        }
        if (getImg() != null   ) {
            if (getBarcode() != null) {
                Img img = Img.getINSTANCE().find(this);
                pstmt.setInt(2, img.getId());
            }


        }else {
            pstmt.setNull(2, Types.INTEGER);
        }
            pstmt.setString(3, getName());
            pstmt.setString(4, getAlias());
            pstmt.setString(5, getOrderNumber());
            pstmt.setBoolean(6, getVisibitity().getBoolean());
            pstmt.setString(7,getBarcode());
            pstmt.setString(8,getPure_order());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPureOrder() {
        String expression = "UPDATE " + this.entityName + " SET  "
                + " storedCategoryId =?,  "
                + " imgId  =?,  "
                + " name  =?,  "
                + " alias  =?,  "
                + " orderNumber=?,  "
                + " visibitity =?  "
                +" WHERE    pure_order =? and barcode is null";
        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);

        Integer id_StoredCategory = null;
        if (getStoredCategory() != null && getStoredCategory().getId()!=null) {
            id_StoredCategory=getStoredCategory().getId();
                pstmt.setInt(1, id_StoredCategory);
        }else {
            pstmt.setNull(1, Types.INTEGER );
        }
        Integer id_Img = null;
            if (getImg() != null   ) {
                if (getBarcode() != null) {
                    Img img = Img.getINSTANCE().find(this);
                    pstmt.setInt(2, img.getId());
                }


            }else {
            pstmt.setNull(2, Types.INTEGER);
        }
            pstmt.setString(3, getName());
            pstmt.setString(4, getAlias());
            pstmt.setString(5, getOrderNumber());
            pstmt.setBoolean(6, getVisibitity().getBoolean());
            pstmt.setString(7,getPure_order());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {

        Boolean byBarcodeAndPureOrder = findByBarcodeAndPureOrder();
        Boolean byPureOrder = findByPureOrder();
        if (byBarcodeAndPureOrder) {
            update();
        }
        else if(byPureOrder && !byBarcodeAndPureOrder){
            updateByPureOrder();
        } else {
            insertNew();
        }
    }

    private void insertNew() {
        try {
            String expression = "INSERT INTO " + this.entityName
                    + "("
                    + " storedCategoryId ,  "
                    + " imgId  ,  "
                    + " name  ,  "
                    + " alias  ,  "
                    + " barcode ,  "
                    + " orderNumber,  "
                    + " pure_order,  "
                    + " visibitity   "
                    + ") VALUES(?,?,?,?,?,?,?,?)";


            PreparedStatement pstmt = Db.connection.prepareStatement(expression);

            Integer id_StoredCategory = null;
            if (getStoredCategory() != null) {
                id_StoredCategory=getStoredCategory().getId();
                pstmt.setInt(1, id_StoredCategory);
            }else {
                pstmt.setNull(1, Types.INTEGER );
            }
            if (getImg() != null   ) {
                if (getBarcode() != null) {
                    Img img = Img.getINSTANCE().find(this);
                    pstmt.setInt(2, img.getId());
                }


            }else {
                pstmt.setNull(2, Types.INTEGER);
            }

            pstmt.setString(3, getName());
            pstmt.setString(4, getAlias());
            pstmt.setString(5, getBarcode());
            pstmt.setString(6, getOrderNumber());
            pstmt.setString(7, getPure_order());
            pstmt.setBoolean(8, getVisibitity().getBoolean());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Boolean findByBarcodeAndPureOrder() {
        return BFxPreparedStatement.create("SELECT * FROM " +entityName+" WHERE barcode=? and pure_order=? ")
                .setString(1,getBarcode())
                .setString(2,getPure_order())
                .executeAndCheck();
    }

    public Boolean findByPureOrder() {
        return BFxPreparedStatement.create("SELECT * FROM " +entityName+" WHERE barcode is null and pure_order=? ")
                .setString(1,getPure_order())
                .executeAndCheck();
    }

    public StoredCategory findStoredCategory() {
        if (getBarcode() != null) {
            return findStoredCategoryByBarcodeAndOrder();
        }else{
            return findStoredCategoryByOrder();
        }
    }

    private StoredCategory findStoredCategoryByOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String pure_order = priceItem.getPure_order();
            if (getPure_order()!=null) {
                if (getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}

        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric .infoWindow( "Что-то пошло не так: в бд есть две или более записи  с одним и тем же штрихкодом и номером заказа\n" + "штрихкод-" + getBarcode() + "\n заказ-" + getOrderNumber());
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getStoredCategory();
        }

        return null;
    }

    private StoredCategory findStoredCategoryByBarcodeAndOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String barcode = priceItem.getBarcode();
            String pure_order = priceItem.getPure_order();
            if (getBarcode() != null && getPure_order()!=null) {
                if (getBarcode() .equals(barcode) && getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}

        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric .infoWindow( "Что-то пошло не так: в бд есть две или более записи  с одним и тем же штрихкодом и номером заказа\n" + "штрихкод-" + getBarcode() + "\n заказ-" + getOrderNumber());
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getStoredCategory();
        }

        return null;
    }


    public String findAlias() {

        if (getBarcode() != null) {
            return findAliasByBarcodeAndOrder();
        }else {
            return findAliasByOrder();
        }
    }

    private String findAliasByOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String pure_order = priceItem.getPure_order();
            if ( getPure_order()!=null) {
                if ( getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}
        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric
                    .infoWindow(
                            "Что-то пошло не так: в бд есть две или более записи " +
                                    "с одним и тем же штрихкодом и номером заказа\n" +
                                    "штрихкод-" + getBarcode() +
                                    "\n заказ-" + getOrderNumber()
                    );
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getAlias();
        }

        return null;
    }

    private String findAliasByBarcodeAndOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String barcode = priceItem.getBarcode();
            String pure_order = priceItem.getPure_order();
            if (getBarcode() != null && getPure_order()!=null) {
                if (getBarcode() .equals(barcode) && getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}
        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric
                    .infoWindow(
                            "Что-то пошло не так: в бд есть две или более записи " +
                                    "с одним и тем же штрихкодом и номером заказа\n" +
                                    "штрихкод-" + getBarcode() +
                                    "\n заказ-" + getOrderNumber()
                    );
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getAlias();
        }

        return null;
    }


    public Boolean findVisibility() {
        if (getBarcode() != null) {
            return findVisibilityByDarcodeAndOrder();
        }else{
            return findVisibilityByOrder();
        }





    }

    private Boolean findVisibilityByOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String pure_order = priceItem.getPure_order();
            if (  getPure_order()!=null) {
                if (getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}
        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric
                    .infoWindow(
                            "Что-то пошло не так: в бд есть две или более записи " +
                                    "с одним и тем же штрихкодом и номером заказа\n" +
                                    "штрихкод-" + getBarcode() +
                                    "\n заказ-" + getOrderNumber()
                    );
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getVisibitity().getBoolean();
        }

        return true;
    }

    private Boolean findVisibilityByDarcodeAndOrder() {
        List<ActiveRecord> collect = getAllFromDB().stream().filter(record -> {
            PriceItem priceItem = (PriceItem) record;
            String barcode = priceItem.getBarcode();
            String pure_order = priceItem.getPure_order();
            if (getBarcode() != null && getPure_order()!=null) {
                if (getBarcode() .equals(barcode) && getPure_order() .equals(pure_order)) { return true; }return false;
            }else {return  false;}
        }).collect(Collectors.toList());

        int length = collect.toArray().length;
        if (length > 1) {
            Registry.windowFabric
                    .infoWindow(
                            "Что-то пошло не так: в бд есть две или более записи " +
                                    "с одним и тем же штрихкодом и номером заказа\n" +
                                    "штрихкод-" + getBarcode() +
                                    "\n заказ-" + getOrderNumber()
                    );
        }
        if (length == 1) {
            PriceItem priceItem = (PriceItem) collect.get(0);
            return priceItem.getVisibitity().getBoolean();
        }

        return true;
    }


    public String getPure_order() {
        return pure_order.get();
    }

    public SimpleObjectProperty<String> pure_orderProperty() {
        return pure_order;
    }

    public void setPure_order(String pure_order) {
        this.pure_order.set(pure_order);
    }


    public StoredCategory getStoredCategory() {
        return storedCategory.get();
    }

    public SimpleObjectProperty<StoredCategory> storedCategoryProperty() {
        return storedCategory;
    }

    public void setStoredCategory(StoredCategory storedCategory) {
        this.storedCategory.set(storedCategory);
    }

    public BoolComboBox getVisibitity() {
        return visibitity.get();
    }

    public SimpleObjectProperty<BoolComboBox> visibitityProperty() {
        return visibitity;
    }

    public void setVisibitity(BoolComboBox visibitity) {
        this.visibitity.set(visibitity);
    }

    public Img getImg() {
        return img.get();
    }

    public SimpleObjectProperty<Img> imgProperty() {
        return img;
    }

    public void setImg(Img img) {
        this.img.set(img);
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

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleObjectProperty<String> barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public String getAlias() {
        return alias.get();
    }

    public SimpleObjectProperty<String> aliasProperty() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public SimpleObjectProperty<String> orderNumberProperty() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber.set(orderNumber);
    }

    public String getMeasure() {
        return measure.get();
    }

    public SimpleObjectProperty<String> measureProperty() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure.set(measure);
    }

    public String getAmountInBox() {
        return amountInBox.get();
    }

    public SimpleObjectProperty<String> amountInBoxProperty() {
        return amountInBox;
    }

    public void setAmountInBox(String amountInBox) {
        this.amountInBox.set(amountInBox);
    }

    public Double getAmountInPrice() {
        return amountInPrice.get();
    }

    public SimpleObjectProperty<Double> amountInPriceProperty() {
        return amountInPrice;
    }

    public void setAmountInPrice(Double amountInPrice) {
        this.amountInPrice.set(amountInPrice);
    }

    public Double getPricePerUnit() {
        return pricePerUnit.get();
    }

    public SimpleObjectProperty<Double> pricePerUnitProperty() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit.set(pricePerUnit);
    }








}
