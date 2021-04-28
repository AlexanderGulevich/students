package basisFx.appCore.windows;

import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.utils.Registry;
import javafx.stage.Stage;

public abstract class WindowFabric {
    public abstract WindowAbstraction mainWindow(Stage st,WindowBuilder builder);
    public abstract WindowAbstraction dialogWindow(String message, CallBackParametrized <Boolean> callBackParametrized);
    public abstract WindowAbstraction infoWindow(String message);
    public abstract WindowAbstraction customSubWindow(WindowBuilder builder);
    public abstract WindowAbstraction dateWindow();


    public static WindowFabric WindowDecorated(){
        WindowFabricDecorated windowFabric = new WindowFabricDecorated();
        Registry.windowFabric=windowFabric;
        return windowFabric;
    }
    public static WindowFabric WindowUndecorated(){

        WindowFabricUndecorated windowFabric = new WindowFabricUndecorated();
        Registry.windowFabric=windowFabric;
        return windowFabric;
    }
}
