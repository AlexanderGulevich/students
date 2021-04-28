package basisFx.presentation;

import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.table.ColumnFabric;
import basisFx.domain.Equipment;

public class EquipmentPanel extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {
        SingleTableSet.builder()
                .aClass(Equipment.class)
                .isSortable(false) .isEditable(true)
                .currentWindow(window)
                .bigTitle("Оборудование")
                .littleTitle(" ")
                .cssClass("TABLE_BFx")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_MIDDLE)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
                .addButEvent(null)
                .delButEvent(null)
                .column(
                        ColumnFabric.stringCol(
                                "Наименование","name",1d,true
                        )
                )
                .build().configure();

    }


}
