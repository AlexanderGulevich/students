package basisFx.presentation;

import basisFx.appCore.elements.GridPaneWrapper;
import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.grid.*;
import basisFx.appCore.table.ColWrapperDate;
import basisFx.appCore.table.ColWrapperDouble;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.EmployeesRatePerHour;
import basisFx.appCore.DynamicContentPanel;
import basisFx.service.ServiceTablesSingle;

public class RatePerHourPanel extends DynamicContentPanel {
    private ServiceTablesSingle mediator;
    private TableWrapper tableWrapper;
    @Override
    public void createServices() {
        mediator = new ServiceTablesSingle();
    }

    @Override
    public void customDynamicElementsInit() {

        tableWrapper = TableWrapper.newBuilder()
                .setActiveRecordClass(EmployeesRatePerHour.class)
                .setUnitOfWork(unitOfWork)
                .setIsEditable(true)
                .setIsSortableColums(false)
                .setServiceTables(mediator)
                .setColWrappers(
                        ColWrapperDouble.newBuilder()
                                .setColumnName("Тариф")
                                .setColumnSize(0.3d)
                                .setIsEditeble(true)
                                .setPropertyName("rate")
                                .build(),
                        ColWrapperDate.newBuilder()
                                .setColumnName("Начало действия")
                                .setColumnSize(0.7d)
                                .setIsEditeble(true)
                                .setPropertyName("startDate")
                                .build()
                )
                .build();

        GridPaneWrapper.newBuilder()
                .setGridName("Архив тарифных ставок")
                .setParentAnchor(dynamicContentAnchorHolder)
                .setCoordinate(new Coordinate(0d, 0d, 10d, 0d))
                .setGridLinesVisibility(gridVisibility)
                .setOrganization(
                        new SingleTable(
                                window,
                                tableWrapper,
                                new ButSizeBig(),
                                new CtrlPosTop()
                        ) )
                .build();

        window.setNodeToMap(tableWrapper,"tableWrapper");

    }

    @Override
    public void initServices() {
        mediator.setTableWrapper(tableWrapper);
        mediator.initElements();
    }

}
