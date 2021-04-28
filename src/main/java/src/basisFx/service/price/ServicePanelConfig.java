package basisFx.service.price;

import basisFx.DbSchemaPrice;
import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.events.FileChooser;
import basisFx.appCore.utils.PropertiesUtils;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.dataSource.DbFactory;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ServicePanelConfig extends ServicePanels {
    @FXML
    private JFXButton add;
    @FXML
    private TextField fild;

    FileChooser fileChooser;

    DirectoryChosserEvent directoryChosserEvent;


    String db_name=PropertiesUtils.getProperty("db_name");
    String db_path=PropertiesUtils.getProperty("db_path");
    String db_folder=PropertiesUtils.getProperty("db_folder");



    @Override
    public void init() {



        directoryChosserEvent = new DirectoryChosserEvent();
        directoryChosserEvent.setEventToElement(add);
        directoryChosserEvent.setMediator(this);


        if (db_name != null && db_folder!=null && db_path!=null) {
            fild.setText(db_path +db_folder+"/"+ db_name);
        }




    }



    @Override
    public void commonLabelName(String name) {

    }


    public ServicePanelConfig() {
        Registry.dataExchanger.put("settings", this);
    }


    @Override
    public void inform(Object node) {
        if (node==add) {
            System.out.println(directoryChosserEvent.getPath());
            fild.setText(directoryChosserEvent.getPath());
            PropertiesUtils.setProperty("db_path",directoryChosserEvent.getPath());
            DbFactory.createDbServerHsql(new DbSchemaPrice());
            if (Db.connection != null) {

            }


        }


    }

}