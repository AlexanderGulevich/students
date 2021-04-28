package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.interfaces.CallBack;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CallBackEvent  extends AppEvent{

    private Button but;
    private CallBack callBack;

    public CallBackEvent(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void setEventToElement(AppNode node) {
        but=(Button) node.getElement();
        but.setOnMouseClicked((event) -> {
            run();
        });
    }

    @Override
    public void setEventToElement(Node node) {
        but=(Button) node;
        but.setOnMouseClicked((event) -> {
            run();
        });
    }

    @Override
    public void setEventToElement(Node node, Stage stage) {

    }

    @Override
    public void run() {
       callBack.call();
    }


}