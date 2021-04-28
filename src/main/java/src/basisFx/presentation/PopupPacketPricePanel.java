package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.domain.PacketPrice;
import basisFx.appCore.DynamicContentPanel;

public class PopupPacketPricePanel extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {


        SingleTableSet.builder()
                .aClass(PacketPrice.class)
                .isSortable(false)
                .isEditable(true)
                .bigTitle(null)
                .currentWindow(window)
                .littleTitle("Архив тарифных ставок")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.doubleCol(
                                "Тариф","price",0.3d,true
                        )
                )
                .column(
                        ColumnFabric.dateCol(
                                 "Действует с ","startDate",0.7d,true
                        )
                )
                .build().configure();


    }

}
