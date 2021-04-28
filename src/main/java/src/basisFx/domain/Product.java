package basisFx.domain;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.dataSource.Db;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product  extends ActiveRecord {

    private static Product INSTANCE = new Product();
    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<BoolComboBox> havingSleeve =new SimpleObjectProperty<>(this, "havingSleeve", null);

    public static Product getINSTANCE() {
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

    public BoolComboBox getHavingSleeve() {
        return havingSleeve.get();
    }

    public SimpleObjectProperty<BoolComboBox> havingSleeveProperty() {
        return havingSleeve;
    }

    public void setHavingSleeve(BoolComboBox havingSleeve) {
        this.havingSleeve.set(havingSleeve);
    }

    @Override
    public ObservableList<ActiveRecord> getAll() {
        ObservableList <ActiveRecord> list=FXCollections.observableArrayList();
        String expression="SELECT * FROM " +"Product"+" ORDER BY ID";
        try {
            Statement stmt  = Db.connection.createStatement();
            ResultSet rs    = stmt.executeQuery(expression);
            while (rs.next()) {
                Product pojo=new Product();
                pojo.setId(rs.getInt("id"));
                pojo.setName(rs.getString("name"));
                pojo.setHavingSleeve(new BoolComboBox(rs.getBoolean("havingSleeve") )  );
                list.add(pojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }
}
