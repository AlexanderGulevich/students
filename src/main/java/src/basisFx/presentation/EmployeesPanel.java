package basisFx.presentation;
import basisFx.appCore.events.RowDeleteFromTable;
import basisFx.appCore.events.SubWindowCreaterByBut;
import basisFx.appCore.events.YNWindowCreaterForTable;
import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.*;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.ActualEmployersRate;
import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.elements.TableWrapper;

public class EmployeesPanel extends DynamicContentPanel {
    private WindowBuilder dateResearchWindowBuilder;
    private WindowBuilder hiringWindowBuilder;

    @Override
    public void customDynamicElementsInit() {


        dateResearchWindowBuilder = WindowBuilder.newBuilder()
                .setPanelCreator(RatePerHourPanel::new)
                .setTitle("Архив тарифов")
                .setMessage(null)
                .setFxmlFileName("ByDateResearchWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d)
                .setHeight(600d)
                .setPreClosingCallBack(
                        () -> {
                            TableWrapper tableWrapper =(TableWrapper)   Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
                            tableWrapper.getMediator().refresh(tableWrapper);
                        })
                .build();


        hiringWindowBuilder = WindowBuilder.newBuilder()
                .setPanelCreator(null)
                .setTitle(null)
                .setMessage(null)
                .setFxmlFileName("EmployerHire")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(530d)
                .setHeight(350d)
                .setPreClosingCallBack(
                        () -> {
                            TableWrapper tableWrapper =(TableWrapper)   Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
                            tableWrapper.getMediator().refresh(tableWrapper);
                        })
                .build();




        SingleTableSet.builder()
                .aClass(ActualEmployersRate.class)
                .isSortable(false) .isEditable(true)
                .currentWindow(window)
                .cssClass("wrappedHeaderColumn_font_size_16")
                .bigTitle("Текущий список сотрудников и актуальные тарифы")
                .littleTitle("  ")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_MIDDLE)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(new SubWindowCreaterByBut(hiringWindowBuilder) )
                .delButEvent(new YNWindowCreaterForTable( new RowDeleteFromTable(),"Вы уверены, что хотите уволить сотрудника") )
                .column(
                        ColumnFabric.stringCol(
                                "Фамилия / Имя / Отчество",
                                "NAME",
                                0.5d,
                                true
                        ))
                .column(
                        ColumnFabric.doubleCol(
                                "Тариф - руб./ч. ",
                                "RATE",
                                0.15d,
                                false
                        ))
                .column(
                        ColumnFabric.dateCol(
                                "Начало действия",
                                "STARTDATE",
                                0.15d,
                                false
                        ))
                .column(
                        ColumnFabric.popupViaBtnCol(
                                "Архив",
                                "Показать",
                                0.2d,
                                dateResearchWindowBuilder
                        ))
                .build().configure();





    }


}
