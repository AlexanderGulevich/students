package basisFx.appCore.events;

import basisFx.appCore.elements.AppNode;
import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.CallBackTyped;
import basisFx.appCore.interfaces.Mediator;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


public abstract class AppEvent {

    protected AppNode nodeWrapper;
    @Setter
    @Getter
    protected Mediator mediator;
    @Setter
    @Getter
    protected CallBackParametrized callBackParametrized;
    @Setter
    @Getter
    protected CallBackTyped callBackTyped;
    @Setter
    @Getter
    protected CallBack callBack;

    public abstract void setEventToElement(AppNode node);
    public abstract void setEventToElement(Node node);

    public abstract void setEventToElement(Node node, Stage stage);

    public abstract void run() ;

    protected Boolean booleanCalBack() {

        if (getCallBackTyped() != null) {
            return (Boolean) callBackTyped.call();
        }
        return true;
    }

}
