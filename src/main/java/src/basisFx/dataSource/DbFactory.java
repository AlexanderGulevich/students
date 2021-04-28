package basisFx.dataSource;

import java.sql.SQLException;

public class DbFactory {
    
    public static void createEmbeded(DbSchema dbSchema ) {
         new DbEmbeded(  dbSchema);
    }
    public static void createDbServerHsql(DbSchema dbSchema ) {
         new DbServetHsql(dbSchema );
    }
    
  
    
}
