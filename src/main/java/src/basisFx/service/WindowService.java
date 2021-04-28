package basisFx.service;

import basisFx.appCore.interfaces.CallBack;
import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.interfaces.Mediator;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

public abstract class WindowService<T extends Object> implements Mediator {

    protected WindowAbstraction currentWindow;
    protected AnchorPane dynamicContentAnchor;
    @Setter protected CallBack preClosingCallBack;
    @Setter protected CallBackParametrized callBackParametrized;
    public void setCurrentWindow(WindowAbstraction currentWindow) {
        this.currentWindow = currentWindow;
    }

    public void setMessage(String str){
    }
    public abstract void init();

    protected void informParentWindowAboutClosing() {
        CallBack callBackSubWindowClosing = currentWindow.getWindowImpl().getCallBack();
        if (callBackSubWindowClosing != null) {
            callBackSubWindowClosing.call();
        }
        if (preClosingCallBack != null) {
            preClosingCallBack.call();
        }
    }

    public void close(){
        Registry.crossWindowMediators.values().remove(this);
        System.out.println("WindowService.close");

//         WindowInfoDispatcher.run();


//        WindowInfoDispatcher.run();

    }
}
