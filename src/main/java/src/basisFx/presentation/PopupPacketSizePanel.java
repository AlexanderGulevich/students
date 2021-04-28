package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.domain.PacketSize;
import basisFx.appCore.DynamicContentPanel;

public class PopupPacketSizePanel extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {


        SingleTableSet.builder()
                .aClass(PacketSize.class)
                .isSortable(false)
                .currentWindow(window)
                .isEditable(true)
                .bigTitle(null)
                .littleTitle("Размер пакета")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_BIG)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.stringCol(
                                "Размер пакета","size",1d,true
                        )
                )
                .build().configure();


    }


}
