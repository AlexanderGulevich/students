package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.dataSource.Db;
import basisFx.service.price.ServicePanelTableViewer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OutputTemplate extends ActiveRecord {
    private static OutputTemplate INSTANCE = new OutputTemplate();

    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<ArrayList<Integer>>  storedCategory =new SimpleObjectProperty<>(this, "storedCategory", null);

    public static OutputTemplate getINSTANCE() {
        return INSTANCE;
    }
    @Override
    public String toString() {
        return getName();
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

    public ArrayList<Integer> getStoredCategory() {
        return storedCategory.get();
    }

    public SimpleObjectProperty<ArrayList<Integer>>  storedCategoryProperty() {
        return storedCategory;
    }

    public void setStoredCategory(ArrayList<Integer> storedCategory) {
        this.storedCategory.set(storedCategory);
    }

    @Override
    public ObservableList<ActiveRecord> getAll() {
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        String expression="SELECT * FROM " +this.entityName+" ORDER BY ID";
        try {
            Statement stmt  = Db.connection.createStatement();
            ResultSet rs    = stmt.executeQuery(expression);
            while (rs.next()) {
                OutputTemplate pojo=new OutputTemplate();
                pojo.setId(rs.getInt("id"));
                pojo.setName(rs.getString("name") );

                Array arr=rs.getArray("storedCategory");
                Object array = arr.getArray();
                Object[] intArr=(Object[]) array;
                ArrayList <Integer> arrayList=new ArrayList<>();
                for (int i = 0; i < intArr.length; i++) {
                    arrayList.add((Integer)intArr[i]);
                }
                pojo.setStoredCategory(arrayList);
                list.add(pojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    @Override
    public void insert() {
        try {
                String expression = "INSERT INTO " + this.entityName
                        + "("
                        + " name ,  "
                        + " storedCategory   "
                        + ") VALUES(?,?)";

                PreparedStatement pstmt = Db.connection.prepareStatement(expression);
                pstmt.setString(1, getName());
                pstmt.setArray(2, Db.connection.createArrayOf("INTEGER",getStoredCategory().toArray()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update() {
        String expression = "UPDATE " + this.entityName + " SET  " +
                " name = ?," +
                " storedCategory = ?" +
                " WHERE id= ?";
        PreparedStatement pstmt = null;
        try {
            pstmt.setString(1, "cnhjrf");
            pstmt.setArray(2, Db.connection.createArrayOf("INTEGER",getStoredCategory().toArray()));
            pstmt.setInt(3, getId());
            pstmt.executeUpdate();

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
