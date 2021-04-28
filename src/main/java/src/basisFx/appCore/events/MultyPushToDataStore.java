package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.elements.ButtonWrapper;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MultyPushToDataStore  extends AppEvent{

    private Button but;
    private ButtonWrapper buttonWrapper;

    @Override
    public void setEventToElement(AppNode node) {

        buttonWrapper=(ButtonWrapper) node;
        but=(Button) node.getElement();
        but.setOnMouseClicked((event) -> {
            run();
        });
    }

    @Override
    public void setEventToElement(Node node) {

    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run()  {
        buttonWrapper.getServiceTables().inform(buttonWrapper);
    }
}
