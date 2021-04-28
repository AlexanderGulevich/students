package basisFx.appCore.events;

import basisFx.appCore.utils.PropertiesUtils;
import basisFx.dataSource.Db;
import basisFx.appCore.elements.AppNode;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Setter;

public class CloseMainWindow extends AppEvent{
    protected Button  but;
    @Setter
    protected Stage stage;
    @Override
    public void setEventToElement(AppNode n) {

        this.nodeWrapper =n;
        this.but=(Button) n.getElement();
        stage=nodeWrapper.getStage();


        but.setOnMouseClicked((event) -> {
            run();
        }
        ) ;

    }

    @Override
    public void setEventToElement(Node node) {
        this.but=(Button) node;

        but.setOnMouseClicked((event) -> {
                    run();
                }
        ) ;
    }

    @Override
    public void setEventToElement(Node node,Stage stage) {
        this.but=(Button) node;
        this.stage=stage;

        but.setOnMouseClicked((event) -> {
                    run();
                }
        ) ;
    }

    @Override
    public void run() {

        try {

//Todo Db.getSonicServer().shutdown();
            if (Db.sonicServer != null) {
                Db.sonicServer.shutdown();
            }
            if (Db.connection!= null) {
                Db.connection.close();

            }

            Thread.sleep(500);
            PropertiesUtils.store();
            stage.close();
            System.exit(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(CloseMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
