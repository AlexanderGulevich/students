package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.chart.ChartBfxPie;
import basisFx.appCore.chart.ChartBfxXY;
import basisFx.appCore.chart.SeriesToPie;
import basisFx.appCore.panelSets.ChartPanel;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.SalaryByMonth;
import javafx.geometry.Side;

public class ChartTestPie extends DynamicContentPanel {
    @Override
    protected void customDynamicElementsInit() {


        ChartPanel.builder()
                .commonLabelName("PIE")
                .fxmlFileName("chartPanel")
                .parent(dynamicContentAnchorHolder)
                .chartBfx(
                        ChartBfxPie.builder()
                                .aClass(SalaryByMonth.class)
                                .coordinate(new Coordinate(0d, 0d, 0d, 0d))
                                .side(Side.LEFT)
                                .build())
                .build().configure();
    }



    }

