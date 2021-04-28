package basisFx.dataSource;

import basisFx.appCore.utils.Registry;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbSchema {
    public abstract void create();

    protected void init(String... val) {
        for (String tableName : val) {
            try {
                Connection connection = Db.connection;
                connection.createStatement().execute(tableName);
            } catch (SQLException e) {
                e.printStackTrace();
//                Registry.windowFabric.infoWindow("Не создалась таблица - " + tableName.toUpperCase() + "\n\n " + e.toString());
            }
        }
    }
}
