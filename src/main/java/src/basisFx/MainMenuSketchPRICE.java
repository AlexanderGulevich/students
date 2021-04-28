package basisFx;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.menu.*;
import basisFx.appCore.panelSets.Panel;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Registry;
import basisFx.presentation.*;
import javafx.scene.control.Label;

import static basisFx.appCore.settings.FontsStore.*;

public class MainMenuSketchPRICE implements MenuSketch {

    private LeftAndTopBarDirector menuDirector=new LeftAndTopBarDirector();

    private double iconSize = 25d;

    public MainMenuSketchPRICE() {
        Label label = ((Label) Registry.mainWindow.getNodeFromMap("commonMenuLabel"));
        label.setText("ПРАЙС-ЛИСТ");

        new CommonPanel("\uF06C",FontsStore.FAWESOME5SOLID)
                .initTemplateMethod(Registry.mainWindow);

        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator(  () -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        Panel.builder()
                                .commonLabelName("Прайс-лист")
                                .fxmlFileName("priceLoader")
                                .parent(dynamicContentAnchorHolder)
                                .build().configure();
                    }
                })
                .description("Загрузка и проверка прайса \n")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF019")
                .isActive(false)
                .fontSize(iconSize)
                .build()
        );




        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator(  () -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        Panel.builder()
                                .commonLabelName("Задание псевдонимов и категорий")
                                .fxmlFileName("priceWritter")
                                .parent(dynamicContentAnchorHolder)
                                .build().configure();
                    }
                })
                .description("Управление категориями вывода, картинками \n")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF093")
                .isActive(false)
                .fontSize(iconSize)
                .build()
        );






        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator(  () -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        Panel.builder()
                                .commonLabelName("Таблица представления прайса")
                                .fxmlFileName("priceTableViewer")
                                .parent(dynamicContentAnchorHolder)
                                .build().configure();
                    }
                })
                    .description("Загруженный прайс")
                    .fontsStore(FAWESOME5SOLID)
                    .fxmlFileName("vbut.fxml")
                    .fontSymbol("\uF0CB")
                    .fontSize(iconSize)
                    .build()
        ) ;





        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator(  () -> new DynamicContentPanel() {
                    @Override
                    protected void customDynamicElementsInit() {
                        Panel.builder()
                                .commonLabelName("Конфигурация")
                                .fxmlFileName("settings")
                                .parent(dynamicContentAnchorHolder)
                                .build().configure();
                    }
                })
                    .description("Управление категориями и задание псевдонимов")
                    .fontsStore(FAWESOME5SOLID)
                    .fxmlFileName("vbut.fxml")
                    .fontSymbol("\uF085")
                    .fontSize(iconSize)
                    .build()
        ) ;





    }


    @Override
    public MenuComponent getMenuTree() {
        return menuDirector.getMenuTree();
    }
}
