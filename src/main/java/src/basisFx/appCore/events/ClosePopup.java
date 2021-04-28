package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClosePopup extends AppEvent{
    protected Button but;
    @Override
    public void setEventToElement(AppNode n) {

        this.nodeWrapper =n;
        this.but=(Button) n.getElement();


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
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        nodeWrapper.getStage().close();





    }

}
