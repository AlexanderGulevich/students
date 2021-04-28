package basisFx.dataSource;

import basisFx.DbSchemaPrice;
import org.hsqldb.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Db {

    protected DbSchema dbSchema;

   
    public static Connection connection = null;

    public static Server sonicServer = null;


    public Connection getConnection() {
        return newConnection();
    }
    public static void fullClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sonicServer.shutdown();
    }

    public abstract Connection newConnection() ;









}
