package basisFx.appCore.chart;

import basisFx.appCore.utils.Coordinate;
import basisFx.service.ServicePanels;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;


@Builder
public class ChartBfxPie implements ChartBfx {

    @Setter private ServicePanels service;
    private ChartData_PIE chartDataPIE;
    private PieChart chart ;
    @Getter private Class aClass;
    private ObservableList<PieChart.Data> data;
    private String chartTitle;
    private Side side;
    @Setter private AnchorPane parent;
    private Coordinate coordinate;
    private Cursor cursor;
   static double overall;

    @Override
    public void configure() {

        service.setChartBfx(this);
        overall =0;
        chart = new PieChart();
        chart.setTitle(chartTitle);
        chart.setLegendSide(side);
        data= ChartPIEDataHandler.getPIEData(aClass, chartDataPIE);
        chart.setData(data);


        coordinate.setChildNode(chart);
        coordinate.setParentAnchorPane(parent);
        coordinate.bonding();

        addSliceTooltip(chart);


    }
    private void addSliceTooltip(PieChart chart) {
        ObservableList<PieChart.Data> data = chart.getData();
        overall=0;
        data.forEach(d -> { overall = overall +d.getPieValue(); });

        data.forEach(d -> {
            Tooltip tip = new Tooltip();
            BigDecimal val = new BigDecimal(d.getPieValue())
                    .setScale(3,RoundingMode.HALF_UP);
            BigDecimal sum = new BigDecimal(overall)
                    .setScale(3,RoundingMode.HALF_UP);
            BigDecimal percent=val
                    .divide(sum,3,RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
            tip.setText(d.getName()+"-"+val.doubleValue() + " /  "+ percent.doubleValue()+"%");
            Tooltip.install(d.getNode(), tip);
        });




    }
    @Override
    public void setData(List data) {
        this.data = (ObservableList<PieChart.Data> ) data;
        addSliceTooltip(chart);
    }


    @Override
    public void applyPeriod(Calendar before, Calendar after) {
        ObservableList<PieChart.Data> data = ChartPIEDataHandler.getPIEDataByPeriod(getAClass(), before, after, chartDataPIE);
        chart.setData(data);
        addSliceTooltip(chart);
    }

    @Override
    public void applyAllTime() {
        ObservableList<PieChart.Data> data = ChartPIEDataHandler.getPIEData(getAClass(), chartDataPIE);
        chart.setData(data);
        addSliceTooltip(chart);
    }

    @Override
    public void applyYearFormat() {

    }

    @Override
    public void applyMonthFormat() {

    }

    @Override
    public void applyMonthYearFormat() {

    }

    @Override
    public void applyDayFormat() {

    }
}
