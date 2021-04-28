package basisFx;

import basisFx.appCore.menu.*;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Registry;
import basisFx.presentation.*;
import basisFx.service.WindowServiceDatePicker;

import static basisFx.appCore.settings.FontsStore.*;

public class MainMenuSketch implements MenuSketch {

    private LeftAndTopBarDirector menuDirector=new LeftAndTopBarDirector();

    private double iconSize = 25d;

    public MainMenuSketch() {

        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .description("  ПАНЕЛЬ УПРАВЛЕНИЯ \n")
                .callBack(WindowServiceDatePicker::closeIfExist)
                .fontsStore(FontsStore.FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF015")
                .panelCreator(null)
                .isActive(true)
                .fontSize(iconSize)
//                .panelCreator(MainPanel::new)
                .panelCreator(null)
                .build()
        );
        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .description("  Меню управления затратами и выработкой за день \n")
                .fontsStore(FontsStore.WEBHOSTINGHUB)
                .callBack(() -> Registry.windowFabric.dateWindow())
                .panelCreator( () -> new CommonPanel("\uF0A1",FontsStore.WEBHOSTINGHUB))
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF0A1")
                .isActive(false)
                .fontSize(iconSize)
                .build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Учет времени").panelCreator(TimeRecordingPanel::new ).build())
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Учет выходной продукции").panelCreator(OutputPanel::new ).build())
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Учет джамбо-ролей").panelCreator(JumboAccountingPanel::new).build());



//                LeftAndTopBarItemComposite.newBuilder().setDescription("Проводки")
//                        .setFontsStore(FontsStore.MATERIAL_ICONS)
//                        .setFontSymbol("\uE52D")
//                        .setFontSize(iconSize)
//                        .build().addButEvent(
//                              LeftAndTopBarItemLeaf.newBuilder()
//                                      .setDescription("Дневная выработка")
////                                      .setPanelCreator(fm.dailyOutputPanel())
//                                      .build()
//                ),
        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("\uF080",FontsStore.FAWESOME5SOLID))
                .callBack(WindowServiceDatePicker::closeIfExist)
                .description("  Меню вывода графиков ")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF080")
                .fontSize(iconSize)
                .build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Затраты").panelCreator(Chart_Packet::new).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Валюты").panelCreator(ChartTestLine::new).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Выход").panelCreator(ChartTestVerticalBar::new) .build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Рентабельность").panelCreator(ChartTestStackedArea::new).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Рентабельность").panelCreator(ChartTestPie::new).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Рентабельность").panelCreator(null).build() );


        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("\uF15C",FontsStore.FAWESOME5SOLID))
                .callBack(WindowServiceDatePicker::closeIfExist)
                .description("   Меню вывода статистической информации")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF15C")
                .fontSize(iconSize)
                .build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Затраты").panelCreator(null).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Валюты").panelCreator(null).build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Выход").panelCreator(null) .build() )
                .setLeaf(LeftAndTopBarItemLeaf.builder()
                        .name("Рентабельность").panelCreator(null).build() );


        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("\uF007",FontsStore.FAWESOME5REGULAR))
                .description("  Меню управления сотрудниками")
                .callBack(WindowServiceDatePicker::closeIfExist)
                .fontsStore(FontsStore.FAWESOME5REGULAR)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF007")
                .fontSize(iconSize)
                .build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Сотрудники").panelCreator(EmployeesPanel::new).build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Уволенные").panelCreator(FiredEmployeesPanel::new).build() );


        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("",FontsStore.MATERIAL_ICONS))
                .description("  Меню управления прочими категориями данных")
                .callBack(WindowServiceDatePicker::closeIfExist)
                .fontsStore(FontsStore.MATERIAL_ICONS)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("")
                .fontSize(iconSize)
                .build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Продукция").panelCreator(ProductPanel::new).build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Станки").panelCreator(EquipmentPanel::new).build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Контрагенты").panelCreator(CounterpartyPanel::new).build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Валюты").panelCreator(ExchangeRatesPanel::new).build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Ширины ролей").panelCreator(JumboPanel::new).build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Пакеты").panelCreator(PacketPanel::new).build()
        ).setLeaf( LeftAndTopBarItemLeaf.builder()
                .name("Этикетки").panelCreator(LabelPanel::new).build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Втулки").panelCreator(SleevePanel::new).build()
        ).setLeaf(LeftAndTopBarItemLeaf.builder()
                .name("Бумага").panelCreator(PaperPanel::new).build()
        );


    }


    @Override
    public MenuComponent getMenuTree() {
        return menuDirector.getMenuTree();
    }
}
