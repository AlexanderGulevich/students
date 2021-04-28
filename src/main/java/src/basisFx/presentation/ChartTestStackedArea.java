package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.chart.ChartBfxXY;
import basisFx.appCore.chart.SeriesNumberNumber;
import basisFx.appCore.panelSets.ChartPanel;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.SalaryByMonth;
import javafx.scene.Cursor;

public class ChartTestStackedArea extends DynamicContentPanel {
    @Override
    protected void customDynamicElementsInit() {

        ChartPanel.builder()
                .commonLabelName("STACKED")
                .fxmlFileName("chartPanel")
                .parent(dynamicContentAnchorHolder)
                .chartBfx(
                        ChartBfxXY.builder()
                                .aClass(SalaryByMonth.class)
                                .kind(ChartBfxXY.KIND.STACKED)
                                .dateBasedChart(true)
                                .dateformat(ChartBfxXY.DATEFORMAT.MONTHYEAR)
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
