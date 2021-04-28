package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.interfaces.CallBackTyped;
import basisFx.dataSource.Db;
import basisFx.service.WindowService;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClosePopupAndSubWindow extends AppEvent{
    protected Button  but;
    protected Long sleepMillis;
    @Setter protected CallBackTyped callBackTyped;

    public void setSleepMillis(long sleepMillis) {
        this.sleepMillis = sleepMillis;
    }

    @Override
    public void setEventToElement(AppNode n) {
        this.nodeWrapper =n;
        this.but=(Button) n.getElement();
        but.setOnMouseClicked((event) ->run()) ;
        but.setOnAction((event) -> run());
    }

    public void check() {
        if (callBackTyped != null) {
            Boolean call = (Boolean) callBackTyped.call();
            if (call) {
                action();
            }
        } else {
            action();
        }
    }

    @Override
    public void setEventToElement(Node node) {
        this.but=(Button) node;
        but.setOnMouseClicked((event) -> check());
        but.setOnAction((event) ->  check());
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
        check();
    }

    private void action() {
        try {

            System.out.println("ClosePopupAndSubWindow.run");

            if (sleepMillis != null) {
                Thread.sleep(sleepMillis);
            }else {
                Thread.sleep(1);
            }

            Window window = but.getScene().getWindow();
            Stage stage= ((Stage) window);
            stage.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClosePopupAndSubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        if (mediator != null) {
            mediator.inform(but);
            ((WindowService) mediator).close();
        }
    }


}
