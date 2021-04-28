package basisFx.presentation;

import basisFx.appCore.panelSets.TwoBindTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.domain.Currency;
import basisFx.domain.ExchangeRates;
import basisFx.appCore.DynamicContentPanel;

public class ExchangeRatesPanel extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {
        TwoBindTableSet.builder()
                .aClassLeft(Currency.class).aClassRight(ExchangeRates.class)
                .bigTitle("Управление валютами и динамика курсов")
                .littleTitleLeft("Валюта ").littleTitleRight("Курсы валют")
                .percentWidthLeft(70).percentWidthRight(30)
                .parentAnchor(dynamicContentAnchorHolder)
                .currentWindow(window)
                .leftCols(
                        ColumnFabric.stringCol(
                                "Наименование","name",1d,true
                        ))
                .rightCols(
                        ColumnFabric.doubleCol(
                                "Курс","rate",0.6d,true
                        ))
                .rightCols(
                        ColumnFabric.dateCol(
                                "Дата","startDate",0.4d,true
                        ))
                .build().configure();

    }



}
