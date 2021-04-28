/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.dataSource;

import java.sql.Connection;

/**
 *
 * @author Alek
 */
public class Dbremote extends Db{

    @Override
    public Connection newConnection() {
        return null;
    }
}
