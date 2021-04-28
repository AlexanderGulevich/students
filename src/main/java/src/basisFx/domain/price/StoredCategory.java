package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.domain.Packet;
import basisFx.domain.PacketSize;
import basisFx.service.price.ServicePanelTableViewer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StoredCategory extends ActiveRecord {
    private static StoredCategory INSTANCE = new StoredCategory();
    public static StoredCategory getINSTANCE() {
        return INSTANCE;
    }


    private SimpleObjectProperty<String> name =new SimpleObjectProperty(this, "name", null);
    private SimpleObjectProperty<Integer> rank =new SimpleObjectProperty(this, "rank", null);


    public String getName() {
        return name.get();
    }

    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getRank() {
        return rank.get();
    }

    public SimpleObjectProperty<Integer> rankProperty() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank.set(rank);
    }

    @Override
    public String toString() {
        return getName();
    }


    @Override
    public ObservableList<ActiveRecord> getAll() {
        ObservableList<ActiveRecord> all = super.getAll();
        List<ActiveRecord> recordList = all.stream().sorted(Comparator.comparing(record -> ((StoredCategory) record).getRank())).collect(Collectors.toList());
        all.setAll(recordList);
        all.addListener((ListChangeListener<ActiveRecord>) c -> {
            while (c.next()) {
                if (c.wasRemoved()) {
                    ServicePanelTableViewer.bindRecordsHasChanged = true;
                    System.out.println("remuved@@@@@@@@");
                }
            }
        });
        return all;
    }



    public StoredCategory findStoredCategory(String barcode,String pure_order) {
        StoredCategory pojo=new StoredCategory() ;
        String expression="SELECT * FROM " +entityName+" WHERE barcode=? and pure_order=?";

        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, barcode);
            pstmt.setString(2, pure_order);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                pojo.setId(rs.getInt("id"));
                pojo.setName(rs.getString("id"));
                pojo.setRank(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojo;







    }
}
