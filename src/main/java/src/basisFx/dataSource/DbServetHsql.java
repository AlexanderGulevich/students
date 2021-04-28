/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.dataSource;

import basisFx.appCore.utils.PropertiesUtils;
import basisFx.appCore.utils.Registry;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbServetHsql extends Db{

    protected Statement statement = null;
    private String db_name= PropertiesUtils.getProperty("db_name");
    private String db_path=PropertiesUtils.getProperty("db_path");
    private String db_folder=PropertiesUtils.getProperty("db_folder");

    public DbServetHsql(DbSchema dbSchema)  {

        this.dbSchema=dbSchema;

        if (db_path == null) {
            Registry.windowFabric.infoWindow("В локальных свойствах не задан путь БД");
        }else {
            init();
            dbSchema.create();
        }
    }

    private void init() {
        HsqlProperties props = new HsqlProperties();
        props.setProperty("server.database.0", "file:" + db_path +db_folder+"/"+ db_name);
        props.setProperty("server.dbname.0", db_name);
        props.setProperty("server.remote_open", true);
        props.setProperty("server.silent ", false);
        props.setProperty("server.trace ", true);
        props.setProperty("hsqldb.tx", " MVCC");
        props.setProperty("server.port", "9003");

        sonicServer = new Server();

        try {
            sonicServer.setProperties(props);
        } catch (Exception e) {

            Registry.windowFabric.infoWindow("sonicServer.setProperties(props)   не сработал!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());
        }

        try {


            if (isConnection() == null) {

                sonicServer.start();
            }

        } catch (Exception e) {
            Registry.windowFabric.infoWindow("sonicServer.start()   не сработал!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());
        }

//           sonicServer.shutdown();

        createConnection();
        newConnection();
    }

    private void createConnection() {
        try {

//            Class.forName("org.hsqldb.jdbc.JDBCDriver");
//            Db.connection= DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/"+db_name  +";hsqldb.tx=mvcc" , "SA", "");
//            statement=this.connection.createStatement();
//            statement.setQueryTimeout(30);

        } catch (Exception e) {
            Registry.windowFabric.infoWindow("Не получилось подключиться к базе данных!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());

        }
    }
    public Connection newConnection() {
        try {

            Class.forName("org.hsqldb.jdbc.JDBCDriver" );



            Db.connection= DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003/"+db_name  +";hsqldb.tx=mvcc" , "SA", "");
            statement=this.connection.createStatement();
            statement.setQueryTimeout(30);

//
//
//            Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9002/" + db_name+";hsqldb.tx=mvcc", "SA", "");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);
//
//            Connection connection2 = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9002/" + db_name+";hsqldb.tx=mvcc", "SA", "");
//            Statement statement2 = connection2.createStatement();
//            statement2.setQueryTimeout(30);



        } catch (Exception e) {
            Registry.windowFabric.infoWindow("Не получилось подключиться к базе данных!!\n\n".toUpperCase()+ e.getMessage() );
            System.err.println(e.getMessage());

        }

        return null;
    }

    public Connection isConnection() {
        Connection connection2=null;
        try {

            Class.forName("org.hsqldb.jdbc.JDBCDriver" );



             connection2 = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003/" + db_name+";hsqldb.tx=mvcc", "SA", "");
            Statement statement2 = connection2.createStatement();
            statement2.setQueryTimeout(30);



        } catch (Exception e) {
//            Registry.windowFabric.infoWindow("нет соединения" );

        }

        return connection2;
    }

}
