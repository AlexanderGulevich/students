package basisFx.dataSource;

import basisFx.DbSchemaPrice;

import java.sql.*;


public class DbEmbeded extends Db{
    
   protected Statement statement = null;

    public DbEmbeded(DbSchema  dbSchema)  {
        this.dbSchema=dbSchema;
        try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                Db.connection = DriverManager.getConnection(
                        "jdbc:hsqldb:file:database/db", "SA", "");    
                statement=this.connection.createStatement();
                statement.setQueryTimeout(30);
                System.out.println("Database connected!");
        } catch (Exception e) {
                System.out.println("Сбой при подключении к БД!");
                System.err.println( e.getMessage());
               
            
        }
        
        init();

}



    @Override
    public Connection newConnection() {
        return null;
    }

    protected void init(){
        dbSchema.create();
    }
}

