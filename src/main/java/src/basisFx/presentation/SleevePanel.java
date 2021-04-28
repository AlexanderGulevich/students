package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.DynamicContentPanel;
import basisFx.domain.*;

public class SleevePanel  extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {

        SingleTableSet.builder()
                .aClass(SleevePrice.class)
                .isSortable(false) .isEditable(true)
                .currentWindow(window)
                .bigTitle("Цена втулок")
                .littleTitle(" ")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_BUT_AND_COMBOBOX)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.doubleCol(
                                "Цена","price",0.4d,true
                        )
                )
                .column(
                        ColumnFabric.dateCol(
                                "Действует с","startDate",0.6d,true
                        )
                )
                .build().configure();

    }



}
