package basisFx.presentation;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.table.ColWrapperComboBox;
import basisFx.appCore.table.ColWrapperPopup;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.domain.Counterparty;
import basisFx.domain.Packet;
import basisFx.appCore.DynamicContentPanel;
import basisFx.service.ServiceTablesSingle;

public class PacketForPopup extends DynamicContentPanel {
    private ServiceTablesSingle serviceTablesSingle1;
    private ServiceTablesSingle serviceTablesSingle2;
    private TableWrapper t1;

    @Override
    public void createServices() {
        serviceTablesSingle1 =new ServiceTablesSingle();
        serviceTablesSingle2 =new ServiceTablesSingle();
    }

    @Override
    public void customDynamicElementsInit() {

        WindowBuilder dateResearchWindowBuilder = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setButtonsForStage(null)
                .setPanelCreator(PopupPacketPricePanel::new)
                .setTitle("Реестр цен")
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

        WindowBuilder windowBuilder = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setPanelCreator(PopupPacketSizePanel::new)
                .setTitle("Размеры пакетов")
                .setMessage(null)
                .setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d)
                .setHeight(600d)
                .setPreClosingCallBack(
                        () -> {
                            TableWrapper tableWrapper = (TableWrapper) Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
                            tableWrapper.getMediator().refresh(tableWrapper);
                        })
                .build();

        t1 = TableWrapper.newBuilder()
                .setGridLinesVisibility(gridVisibility)
                .setGridName("Пакеты ")
                .setOrganization(new SingleTable(new ButSizeLittle(),new CtrlPosTop()))
                .setActiveRecordClass(Packet.class)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(false)
                .setIsSortableColums(false)
                .setServiceTables(serviceTablesSingle1)
                .setColWrappers(
                        ColWrapperPopup.newBuilder()
                                .setColumnName("Размер")
                                .setColumnSize(0.4d)
                                .setIsEditeble(true)
                                .setWindowBuilder(windowBuilder)
                                .setPropertyName("packetSize")
                                .build(),
                        ColWrapperComboBox.newBuilder(Counterparty.class)
                                .setColumnName("Поставщик")
                                .setColumnSize(0.6d)
                                .setIsEditeble(true)
                                .setPropertyName("counterparty")
                                .build()
                )
                .build();



        window.setNodeToMap(t1,"tableWrapper");





        GridPaneWrapper commonGridPaneWrapper = GridPaneWrapper.newBuilder()
                .setGridLinesVisibility(gridVisibility)
                .setGridName("Управление информацией о пакетах")
                .setParentAnchor(dynamicContentAnchorHolder)
                .setCoordinate(new Coordinate(0d,  0d, 10d, 0d))
                .setOrganization(
                        new SingleTable(t1,new ButSizeNon(), new CtrlPosNON())
                )
                .build();

    }

    @Override
    public void initServices() {
        serviceTablesSingle1.setTableWrapper(t1);
        serviceTablesSingle1.initElements();

    }

}
