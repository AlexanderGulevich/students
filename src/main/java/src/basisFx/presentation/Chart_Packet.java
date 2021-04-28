package basisFx.presentation;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.chart.ChartBfxXY;
import basisFx.appCore.chart.ChartData_XY;
import basisFx.appCore.panelSets.ChartPanel;
import basisFx.appCore.utils.Coordinate;
import basisFx.domain.PACKETRESULTFULL;

import java.time.ZoneId;
import java.util.GregorianCalendar;


public class Chart_Packet extends DynamicContentPanel {
    @Override
    protected void customDynamicElementsInit() {


        ChartPanel.builder()
                .commonLabelName("PACKETRESULTFULL")
                .fxmlFileName("chartPanel")
                .parent(dynamicContentAnchorHolder)
                .chartBfx(
                        ChartBfxXY.builder()
                                .aClass(PACKETRESULTFULL.class)
                                .kind(ChartBfxXY.KIND.AREA)
                                .dateBasedChart(true)
                                .dateformat(ChartBfxXY.DATEFORMAT.MONTHYEAR)
                                .xLabel("Дата").yLabel("Сумма")
                                .xPrefix("x").yPrefix("y")
                                .xLines(true).yLines(true)
                                .coordinate(new Coordinate(20d, 30d, 0d, 0d))
                                .xAutoRanging(true)
                                .yAutoRanging(true)
                                .chartDataXY(new ChartData_XY() {
                                    @Override
                                    public String getSeriesName(ActiveRecord record) {
                                        return   ((PACKETRESULTFULL) record).getPACKET().toString();
                                    }

                                    @Override
                                    public GregorianCalendar getCalendar(ActiveRecord record) {
                                        return   GregorianCalendar.from(((PACKETRESULTFULL) record)
                                                .getOUTDATE().atStartOfDay(ZoneId.systemDefault()));
                                    }

                                    @Override
                                    public double getValue(ActiveRecord record) {
                                        return((PACKETRESULTFULL) record).getTOTALPACKETCOST();
                                    }
                                })
                                .build())
                .build().configure();
    }

}
