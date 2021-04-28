package basisFx.presentation;

import basisFx.appCore.panelSets.TwoBindTableSet;
import basisFx.appCore.table.*;
import basisFx.domain.ProductPrice;
import basisFx.domain.Product;
import basisFx.appCore.DynamicContentPanel;

public class ProductPanel  extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {

        TwoBindTableSet.builder()
                .aClassLeft(Product.class).aClassRight(ProductPrice.class)
                .bigTitle("Управление продуктами и динамика цен")
                .littleTitleLeft("Список продукции ").littleTitleRight("Цены")
                .percentWidthLeft(70).percentWidthRight(30)
                .parentAnchor(dynamicContentAnchorHolder)
                .currentWindow(window)
                .leftCols(
                        ColumnFabric.stringCol(
                        "Наименование","name",0.7d,true
                ))
                .leftCols(
                        ColumnFabric.boolCol(
                        "Втулка","havingSleeve",0.3d,true
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
