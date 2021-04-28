package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HideWindow extends AppEvent{
    protected Button  but;
    @Override
    public void setEventToElement(AppNode node) {
        this.nodeWrapper =node;
        this.but=(Button) node.getElement();


        but.setOnMouseClicked( (event) -> {
           run();

        }

        ) ;


}

    @Override
    public void setEventToElement(Node node) {
        this.but=(Button) node;


        but.setOnMouseClicked( (event) -> {
                    run();

                }

        ) ;
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
         try {
                Thread.sleep(200);
                nodeWrapper.getStage().setIconified(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(CloseMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
