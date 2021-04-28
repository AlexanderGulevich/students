package basisFx.presentation;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.CallBackTypedAndParametrized;
import basisFx.appCore.panelSets.TwoVerticalTablesSet;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.table.*;
import basisFx.domain.*;
import basisFx.appCore.DynamicContentPanel;

public class PacketPanel  extends DynamicContentPanel {

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

        WindowBuilder packetSize = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setPanelCreator(PopupPacketSizePanel::new)
                .setTitle("Размеры пакетов")
                .setMessage(null)
                .setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d)
                .setHeight(600d)
                .build();

        WindowBuilder counterparty = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setPanelCreator(PopupCounterpartyPanel::new)
                .setTitle("Список контрагентов")
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


        CallBackTypedAndParametrized clb=r -> { Packet var = (Packet) r;
            if (!ActiveRecord.isNewDomane(var)) {
                return var.getCounterparty().currencyProperty();
            } else return null; };



        TwoVerticalTablesSet.builder()
                .aClassTop(Packet.class).aClassBottom(PacketProductAccordance.class)
                .bigTitle("Управление информацией о пакетах")
                .littleTitleTop("Пакеты").littleTitleBottom("Вместимость пакетов")
                .parentAnchor(dynamicContentAnchorHolder)
                .currentWindow(window)
                .topCols(ColumnFabric.popup("Размер","packetSize",0.3d,packetSize))
                .topCols(ColumnFabric.popup("Поставщик","counterparty",0.3d,counterparty))
                .topCols(ColumnFabric.bindCol("Валюта",0.2d,clb))
                .topCols(ColumnFabric.popupViaBtnCol("Архив цен","Архив цен",0.2d,dateResearchWindowBuilder))
                .bottomCols(ColumnFabric.comboBoxCol(Product.class,"Продукция","product",0.5d,true))
                .bottomCols(ColumnFabric.comboBoxCol(PacketSize.class,"Размер","packetSize",0.3d,true))
                .bottomCols(ColumnFabric.intCol("Кол-во","number",0.2d,true))
                .build().configure();


    }


}