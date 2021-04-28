package basisFx.dataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BFxPreparedStatement {
    static  final  BFxPreparedStatement inst=new BFxPreparedStatement();
    static PreparedStatement statement;
    static ResultSet rs;

    public static BFxPreparedStatement create(String exp){
        try {
            statement=Db.connection.prepareStatement(exp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }

    public   BFxPreparedStatement setInt(int parameterIndex, int x){
        try {
            statement.setInt(parameterIndex,x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }
    public   BFxPreparedStatement setDate(int parameterIndex, java.sql.Date x){
        try {
            statement.setDate(parameterIndex,x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }
    public   BFxPreparedStatement setDouble(int parameterIndex, double x){
        try {
            statement.setDouble(parameterIndex,x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }
    public   BFxPreparedStatement setBoolean(int parameterIndex, boolean x){
        try {
            statement.setBoolean(parameterIndex,x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }
    public   BFxPreparedStatement setString(int parameterIndex, String x){
        try {
            statement.setString(parameterIndex,x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inst;
    }
    public ResultSet execute(){
        try {
            return   statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean executeAndCheck(){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
