package basisFx.appCore.chart;

import basisFx.appCore.utils.Coordinate;
import basisFx.service.ServicePanels;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Builder
public class ChartBfxVerticalBar implements ChartBfx{

    @Setter private ServicePanels service;
    private ChartData_VBar dataVBar;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private BarChart<String, Number> chart;
    private ObservableList<XYChart.Series<String, Number>> data;
    private String chartTitle;
    private String xLabel;
    private String yLabel;
    private String xPrefix;
    private String yPrefix;
    private boolean yAutoRanging;
    private boolean xAutoRanging;

    @Getter
    private Class aClass;
    private boolean xLines;
    private boolean yLines;

    private double yLowerBound;
    private double yUpperBound;
    private double yTickUnit;

    @Setter
    private AnchorPane parent;
    private Coordinate coordinate;

    private Cursor cursor;
    @Override
    public void configure() {

        service.setChartBfx(this);
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        chart = new BarChart<>(xAxis, yAxis);
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, yPrefix));
        chart.setTitle(chartTitle);

        chart.setAlternativeColumnFillVisible(true);
        chart.setAlternativeRowFillVisible(true);
        chart.setHorizontalGridLinesVisible(xLines);
        chart.setVerticalGridLinesVisible(yLines);
        chart.setCursor(cursor);

        xAxis.setLabel(xLabel);
        xAxis.setAutoRanging(xAutoRanging);

        yAxis.setLabel(yLabel);
        yAxis.setTickUnit(yTickUnit);
        yAxis.setAutoRanging(yAutoRanging);
        yAxis.setLowerBound(yLowerBound);
        yAxis.setUpperBound(yUpperBound);


        if (data != null) {
            chart.setData(data);
        }else {
            chart.setData(ChartVBarDataHandler.getVBarData(aClass,dataVBar));
        }


        coordinate.setChildNode(chart);
        coordinate.setParentAnchorPane(parent);
        coordinate.bonding();

    }
    @Override
    public void applyPeriod(Calendar before, Calendar after) {
        ObservableList<XYChart.Series<String, Number>> data = ChartVBarDataHandler.getVBarDataByPeriod(getAClass(), before, after,dataVBar);
        chart.setData(data);
    }

    @Override
    public void applyAllTime() {
        ObservableList<XYChart.Series<String, Number>> data = ChartVBarDataHandler.getVBarData(getAClass(),dataVBar);
        chart.setData(data);
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

    @Override
    public void setData(List data) {
        this.data = (ObservableList<XYChart.Series<String, Number>>) data;
    }
}
