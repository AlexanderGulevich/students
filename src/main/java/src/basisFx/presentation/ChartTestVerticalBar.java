package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.chart.*;
import basisFx.appCore.panelSets.ChartPanel;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.SalaryByMonth;
import javafx.scene.Cursor;

public class ChartTestVerticalBar extends DynamicContentPanel {
    @Override
    protected void customDynamicElementsInit() {
        ChartPanel.builder()
                .commonLabelName("ChartTestVerticalBar")
                .fxmlFileName("chartPanel")
                .parent(dynamicContentAnchorHolder)
                .chartBfx(
                        ChartBfxVerticalBar.builder()
                                .aClass(SalaryByMonth.class)
                                .xLabel("Дата").yLabel("Зарплата")
                                .xPrefix("x").yPrefix("y")
                                .xLines(true).yLines(true)
                                .coordinate(new Coordinate(20d, 30d, 0d, 0d))
                                .xAutoRanging(true)
                                .yAutoRanging(true)
                                .build())
                .build().configure();

    }

}
