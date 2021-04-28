package basisFx.presentation;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.panelSets.AutoCommitByDateTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.DateViaPopup;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.windows.WindowInfoDispatcher;
import basisFx.dataSource.BFxPreparedStatement;
import basisFx.domain.*;
import basisFx.appCore.DynamicContentPanel;

import java.sql.Date;
import java.time.LocalDate;

public class OutputPanel  extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {
        WindowBuilder windowBuilder = WindowBuilder.newBuilder()
                .setGUIStructura(null)
                .setPanelCreator(PacketForPopup::new)
                .setTitle("Пакет")
                .setMessage(null)
                .setFxmlFileName("AddDellPopupWindow")
                .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                .setWidth(700d)
                .setHeight(600d)
//                .setPreClosingCallBack(
//                        () -> {
//                            TableWrapper tableWrapper = (TableWrapper) Registry.mainWindow.getNodeFromMap("outer_table_wrapper");
//                            tableWrapper.getMediator().refresh(tableWrapper);
//                        })
                .build();

        DataStoreCallBack cb_product_price = activeRecord -> {
            OutputPerDay  entry = (OutputPerDay) activeRecord;
            LocalDate date = entry.getDate();
            Integer id = entry.getProduct().getId();
            boolean filled = BFxPreparedStatement
                            .create("SELECT * FROM PRODUCTPRICE " + "WHERE PRODUCTID=?" + " and " +" STARTDATE<=?")
                            .setInt(1, id) .setDate(2, Date.valueOf(date))
                            .executeAndCheck();
            if (!filled) {
                WindowInfoDispatcher.add( "Для данного продукта на указаную дату не установлена цена: \n"
                        + entry.getProduct().getName().toUpperCase().trim()) ;
                return false;
            }
            return true;
        };

        DataStoreCallBack cb_packet_price = activeRecord -> {
            OutputPerDay  entry = (OutputPerDay) activeRecord;
            LocalDate date = entry.getDate();
            Integer id = entry.getPacket().getId();
            boolean filled = BFxPreparedStatement
                            .create("SELECT * FROM PACKETPRICE " + "WHERE PACKETID=?" + " and " +" STARTDATE<=?")
                            .setInt(1, id) .setDate(2, Date.valueOf(date))
                            .executeAndCheck();
            if (!filled) {
                WindowInfoDispatcher.add( "Для данного вида пакетов на указаную дату не установлена цена: \n"
                        + entry.getPacket().toString().toUpperCase().trim() );
                return false;
            }
            return true;
        };

        DataStoreCallBack cb_paper_price = activeRecord -> {
            OutputPerDay  entry = (OutputPerDay) activeRecord;
            LocalDate date = entry.getDate();
            Integer id = entry.getPaperCounterparty().getId();
            boolean filled = BFxPreparedStatement
                    .create("SELECT * FROM PAPERPRICE " + "WHERE PAPERID=?" + " and " +" STARTDATE<=?")
                    .setInt(1, id) .setDate(2, Date.valueOf(date))
                    .executeAndCheck();
            if (!filled) {
                WindowInfoDispatcher.add( "Для данного контрагента  на указаную дату не установлена цена на бумагу: \n"
                        + entry.getPaperCounterparty().getName().toUpperCase().trim()) ;
                return false;
            }
            return true;
        };

        AutoCommitByDateTableSet.builder()
                .aClass(OutputPerDay.class)
                .dateGetter(new DateViaPopup())
                .checkingCallBack(cb_product_price)
                .checkingCallBack(cb_packet_price)
                .checkingCallBack(cb_paper_price)
                .isEditable(true).isSortable(false)
                .currentWindow(window)
                .bigTitle("Учет выходной продукции")
                .littleTitle(null)
                .cssClass("wrappedHeaderColumn")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_BOTTON)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(null)
                .delButEvent(null)
                .checkingTableRecordCallBack(record -> {
                    OutputPerDay outputPerDay = (OutputPerDay) record;
                    if (outputPerDay.getRodsNumber() != null && outputPerDay.getRodsNumber()==0) {
                        outputPerDay.setRodsNumber(null);
                        Registry.windowFabric.infoWindow("Количество стержней не может быть равно 0.");
                    }
                })
                .column(ColumnFabric.comboBoxCol(
                                Equipment.class,
                                "Станок",
                                "equipment",
                                0.15d,
                                true
                        ))
                .column(ColumnFabric.comboBoxCol(
                                 Product.class,
                                "Продукт",
                                "product",
                                0.15d,
                                true
                        ))
                .column(ColumnFabric.intCol(
                          "Кол-во\nстержней",
                                "rodsNumber",
                                0.1d,
                                true
                        ))
                .column(ColumnFabric.popup(
                          "Пакет",
                                "packet",
                                0.25d,
                                windowBuilder
                        ))
                .column(ColumnFabric.comboBoxCol(
                                Jumbo.class,
                                "Ширина\n роля",
                                "jumbo",
                                0.15d,
                                true
                ))
                .column(ColumnFabric.comboBoxCol(
                                  Counterparty.class,
                            "Поставщик\n бумаги",
                                "paperCounterparty",
                                0.2d,
                                true
                ))
                .build().configure();

    }

}
