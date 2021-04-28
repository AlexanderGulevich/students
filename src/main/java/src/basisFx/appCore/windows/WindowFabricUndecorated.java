package basisFx.appCore.windows;

import basisFx.appCore.interfaces.CallBackParametrized;
import basisFx.appCore.utils.Registry;
import basisFx.service.WindowService;
import javafx.stage.Stage;


public class WindowFabricUndecorated extends WindowFabric{

    @Override
    public WindowAbstraction mainWindow( Stage stage,WindowBuilder builder) {

        if (WindowAbstraction.isWindowNotExist(builder)) {
            WindowImpl mainWindow = WindowImplMain.getInstance(builder);
            WindowAbstractionUndecorated windowUndecorated=new WindowAbstractionUndecorated(stage,mainWindow);
            return windowUndecorated;
        }
       return null;
    }

    @Override
    public WindowAbstraction dialogWindow(String message, CallBackParametrized<Boolean> callBackParametrized) {

        WindowBuilder builder = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setButtonsForStage(null)
                .setPanelCreator(null)
                .setTitle("Вопрос")
                .setMessage(message)
                .setFxmlFileName("YN")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setHeight(280d)
                .setWidth(500d)
                .setPreClosingCallBack(null)
                .setCallBackParametrized(callBackParametrized)
                .build();

        WindowImplDialog windowImplInfo = new WindowImplDialog(builder) ;
        WindowAbstractionUndecorated windowUndecorated=new WindowAbstractionUndecorated(windowImplInfo);
        return windowUndecorated;
    }

    @Override
    public WindowAbstraction infoWindow(String message) {

        WindowBuilder builder = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setButtonsForStage(null)
                .setFxmlFileName("Info")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setHeight(280d)
                .setWidth(500d)
                .setPanelCreator(null)
                .setTitle("Сообщение")
                .setMessage(message)
                .build();

        WindowImplInfo windowImplInfo = new WindowImplInfo(builder) ;
        WindowAbstractionUndecorated windowUndecorated=new WindowAbstractionUndecorated(windowImplInfo);
        return windowUndecorated;
    }

    @Override
    public WindowAbstraction customSubWindow(WindowBuilder builder) {
        boolean exist = WindowAbstraction.isWindowNotExist(builder);
        if (exist) {
            WindowImplSubWindow windowImplSubWindow=new WindowImplSubWindow( builder);
            WindowAbstractionUndecorated windowUndecorated=new WindowAbstractionUndecorated(windowImplSubWindow);
            System.out.println("");
            return windowUndecorated;
        }
        return null;



    }

    @Override
    public WindowAbstraction dateWindow() {

        WindowService service =
                Registry.crossWindowMediators.get("SelectDate");

        if (service == null) {
            WindowBuilder builder = WindowBuilder.newBuilder()
                    .setFxmlFileName("SelectDate")
                    .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                    .setHeight(230d)
                    .setWidth(350d)
                    .setPanelCreator(null)
                    .setTitle("Выбор даты")
                    .build();

            WindowImplDatePicker  windowImplDatePicker = new WindowImplDatePicker(builder) ;
            WindowAbstractionUndecorated windowUndecorated=new WindowAbstractionUndecorated(windowImplDatePicker);
            return windowUndecorated;
        }
        return null;


    }

}
