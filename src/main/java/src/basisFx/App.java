package basisFx;

import basisFx.appCore.guiStructura.LeftAndTopMenuGUI;
import basisFx.appCore.menu.MenuFabric;
import basisFx.appCore.settings.Settings;
import basisFx.appCore.utils.*;
import basisFx.appCore.windows.ButtonsForStageThreeEntity;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.windows.WindowFabric;
import basisFx.dataSource.DbFactory;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

import static java.util.Arrays.sort;


public class App {

    public App(Stage primaryStage)  {
//        IconToPlatform.init(primaryStage);
        WindowFabric.WindowUndecorated();
        FontHandler.getInstanse().loadFontToScene();
//        DbFactory.createEmbeded(new DbSchemaStudent());




        PropertiesUtils.run();
        PropertiesUtils.setProperty("db_name","pricedb");
        PropertiesUtils.setProperty("db_folder","omts");

        PropertiesUtils.setProperty("db_path","C:/komfdb/");
        PropertiesUtils.setProperty("db_name","komdb");

        DbFactory.createDbServerHsql(new DbSchemaStudent());

        Registry.windowFabric.mainWindow(primaryStage,
                WindowBuilder.newBuilder()
                        .setFxmlFileName("students")
                        .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                        .setWidth(Settings.WIDTH).setHeight(Settings.HEIGHT)
                        .setPanelCreator(null)
                        .setTitle("Студенты")
                        .setMessage(null)
                        .build()
        );

    }
}
