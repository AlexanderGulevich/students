package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.panelSets.TwoBindTableSet;
import basisFx.appCore.table.ColWrapperDate;
import basisFx.appCore.table.ColWrapperDouble;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.EmployeesRatePerHour;
import basisFx.domain.Jumbo;
import basisFx.domain.students.Faculty;
import basisFx.domain.students.StudentsGroup;
import basisFx.service.ServiceTablesSingle;

public class FacultyPopap extends DynamicContentPanel {

    @Override
    public void customDynamicElementsInit() {

        SingleTableSet.builder()
                .aClass(StudentsGroup.class)
                .isSortable(false) .isEditable(true)
                .currentWindow(window)
                .bigTitle("Ширины джамбо ролей и выход продукции по ширине")
                .littleTitle("")
                .parentAnchor(dynamicContentAnchorHolder)
                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_MIDDLE)
                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
                .addButEvent(null)
                .delButEvent(null)
//                .column(
//                        ColumnFabric.intCol(
//                                "Ширина","width",0.6d,true
//                        )
//                )
//                .column(
//                        ColumnFabric.intCol(
//                                "Кол-во продукции на выходе ","numberOfProduct",0.4d,true
//                        )
//                )
                .build().configure();



//        TwoBindTableSet.builder()
//                .aClassLeft(StudentsGroup.class).aClassRight(Faculty.class)
//                .bigTitle("Список факультетов и групп")
//                .littleTitleLeft("Факультеты").littleTitleRight("Группы")
//                .percentWidthLeft(70).percentWidthRight(30)
//                .parentAnchor(dynamicContentAnchorHolder)
//                .currentWindow(window)
//                .leftCols(
//                        ColumnFabric.stringCol(
//                                "Наименование","name",0.1d,true
//                        ))
//                .rightCols(
//                        ColumnFabric.stringCol(
//                                "Наименование","name",0.1d,true
//                        ))
//                .build().configure();
//




    }


}
