package basisFx.presentation;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.panelSets.TwoBindTableSet;
import basisFx.appCore.table.*;
import basisFx.domain.*;
import basisFx.appCore.DynamicContentPanel;

public class LabelPanel  extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {
        TwoBindTableSet.builder()
                .aClassLeft(Label.class).aClassRight(LabelPrice.class)
                .bigTitle("Управление этикетками и динамика цен")
                .littleTitleLeft("Этикетки").littleTitleRight("Цены")
                .percentWidthLeft(70).percentWidthRight(30)
                .parentAnchor(dynamicContentAnchorHolder)
                .currentWindow(window)
                .leftCols( ColumnFabric.stringCol(
                                "Наименование","name",0.4d,true
                        ))
                .leftCols( ColumnFabric.comboBoxCol(
                                Counterparty.class,"Наименование","counterparty",0.4d,true
                        ))
                .leftCols( ColumnFabric.bindCol(
                                "Валюта",0.2d,
                                r -> {
                                    Label var = (Label) r;
                                    if (!ActiveRecord.isNewDomane(var)) {
                                        return  var .getCounterparty().currencyProperty();
                                    }
                                    else return null;
                                }
                        ))
                .rightCols(
                        ColumnFabric.doubleCol(
                                "Цена","price",0.4d,true
                        ))
                .rightCols(
                        ColumnFabric.dateCol(
                                "Действует с","startDate",0.6d,true
                        ))
                .build().configure();




    }

}

