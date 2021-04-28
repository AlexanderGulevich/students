package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.util.*;
import java.util.stream.Collectors;

public class ChartXYDataHandler {

    public static ObservableList<XYChart.Series<Number, Number>> getXYData(Class aClass, ChartData_XY chartData){
        SeriesNumberNumber seriesNumberNumber = new SeriesNumberNumber();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map = activeRecords.stream().collect(Collectors.groupingBy(chartData::getSeriesName));
        map.entrySet().stream().forEach(e -> {
            seriesNumberNumber.createSet(e.getKey());
            e.getValue().stream().peek(record ->{
                    seriesNumberNumber.add(
                            chartData.getCalendar(record).getTime().getTime(),
                            chartData.getValue(record)
                          );
            } ).count();

        });
        ObservableList<XYChart.Series<Number, Number>> allSets = seriesNumberNumber.getAllSets();
        return allSets;
    }
    public static ObservableList<XYChart.Series<Number, Number>> getXYDataByPeriod(Class aClass, Calendar before, Calendar after, ChartData_XY chartData ){
        SeriesNumberNumber seriesNumberNumber = new SeriesNumberNumber();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map = activeRecords.stream().collect(Collectors.groupingBy(chartData::getSeriesName));

        map.entrySet().stream().forEach(e -> {
            seriesNumberNumber.createSet(e.getKey());
            e.getValue().stream().peek(activeRecord ->{
                if (chartData.getCalendar(activeRecord).after(after)
                        &&
                        chartData.getCalendar(activeRecord).before(before))
                {
                    seriesNumberNumber.add(chartData.getCalendar(activeRecord).getTime().getTime(), chartData.getValue(activeRecord));
                }
            } ).count();

        });
        ObservableList<XYChart.Series<Number, Number>> allSets = seriesNumberNumber.getAllSets();
        return allSets;
    }

}
