package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.interfaces.DataStoreCallBack;
import basisFx.appCore.panelSets.AutoCommitByDateTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.DateViaPopup;
import basisFx.appCore.utils.Registry;
import basisFx.appCore.windows.WindowInfoDispatcher;
import basisFx.dataSource.BFxPreparedStatement;
import basisFx.domain.*;
import basisFx.appCore.DynamicContentPanel;

import java.sql.Date;
import java.time.LocalDate;

public class JumboAccountingPanel extends DynamicContentPanel {
    @Override
    public void customDynamicElementsInit() {
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
                                + entry.getPaperCounterparty().getName().toUpperCase().trim() );

                return false;
            }
            return true;
        };
        AutoCommitByDateTableSet.builder()
                .aClass(JumboAccounting.class)
                .dateGetter(new DateViaPopup())
                .checkingCallBack(cb_paper_price)
                .isEditable(true).isSortable(false)
                .currentWindow(window)
                .bigTitle("Учет джамбо-ролей за день")
                .littleTitle(null)
                .cssClass(null)
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_BOTTON)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.comboBoxCol(
                                Counterparty.class,
                                "Контрагент",
                                "counterparty",
                                0.7d,
                                false
                        ))
                .column(
                        ColumnFabric.doubleCol(
                                "Общий вес ролей",
                                "overallWeight",
                                0.3d,
                                true
                        ))
                .build().configure();


    }

}
