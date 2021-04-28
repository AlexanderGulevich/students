package basisFx.appCore.windows;

import basisFx.appCore.interfaces.CallBackParametrized;
import javafx.stage.Stage;


public class WindowFabricDecorated extends WindowFabric {


    @Override
    public WindowAbstraction mainWindow(Stage st, WindowBuilder builder) {
        return null;
    }

    @Override
    public WindowAbstraction dialogWindow(String message, CallBackParametrized<Boolean> callBackParametrized) {
        return null;
    }


    @Override
    public WindowAbstraction infoWindow(String message) {
        return null;
    }

    @Override
    public WindowAbstraction customSubWindow(WindowBuilder builder) {
        return null;
    }

    @Override
    public WindowAbstraction dateWindow( ) {
        return null;
    }
}
