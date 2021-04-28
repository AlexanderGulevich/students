package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.activeRecord.BoolComboBox;
import basisFx.dataSource.Db;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fired extends ActiveRecord {

    private static Fired INSTANCE = new Fired();
    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<BoolComboBox> isFired =new SimpleObjectProperty<>(this, "isFired", new BoolComboBox(false));


    public static Fired getINSTANCE() {
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

    public BoolComboBox getIsFired() {
        return isFired.get();
    }

    public SimpleObjectProperty<BoolComboBox> isFiredProperty() {
        return isFired;
    }

    public void setIsFired(BoolComboBox isFired) {
        this.isFired.set(isFired);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void delete() {
        String expression = "UPDATE Employer SET  " +
                " isfired = ?" +
                " WHERE id= ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = Db.connection.prepareStatement(expression);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, id.get());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
