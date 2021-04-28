package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class ChartPIEDataHandler {

    public static ObservableList<PieChart.Data> getPIEData(Class aClass, ChartData_PIE chartData ){
        SeriesToPie series = new SeriesToPie();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map =
                activeRecords.stream().collect(Collectors.groupingBy(chartData::getSeriesName));

        map.entrySet().stream().forEach(e -> {

            String name = e.getKey();
            double sum = e.getValue().stream()
                    .flatMapToDouble(activeRecord -> DoubleStream.of(chartData.getValue(activeRecord)))
                    .reduce((acc, val) -> acc + val).getAsDouble();

            series.add(name,sum);

        });
        ObservableList<PieChart.Data> allSets = series.getAllSets();
        return allSets;
    }
    public static ObservableList<PieChart.Data> getPIEDataByPeriod(Class aClass, Calendar before, Calendar after, ChartData_PIE chartData ){
        SeriesToPie series = new SeriesToPie();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map =
                activeRecords.stream().collect(Collectors.groupingBy(chartData::getSeriesName));

        map.entrySet().stream().forEach(e -> {
            String name = e.getKey();
            double sum = e.getValue().stream()
                    .filter(activeRecord ->chartData.getCalendar(activeRecord).after(after) )
                    .filter(activeRecord ->chartData.getCalendar(activeRecord).before(before) )
                    .flatMapToDouble(activeRecord -> DoubleStream.of(chartData.getValue(activeRecord)))
                    .reduce((acc, val) -> acc + val)
                    .getAsDouble();
            series.add(name,sum);
        });

        return     series.getAllSets();
    }

}
