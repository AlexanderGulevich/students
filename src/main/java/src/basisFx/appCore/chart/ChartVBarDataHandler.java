package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartVBarDataHandler {


    public static ObservableList<XYChart.Series<String, Number>> getVBarData(Class aClass,ChartData_VBar dataVBar ){

        SeriesStringNumber series = new SeriesStringNumber();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map =
                activeRecords.stream().collect(Collectors.groupingBy(dataVBar::getSeriesName));

        map.entrySet().stream().forEach(e -> {
            String name = e.getKey();
            series.createSet(name);
//            e.getValue().stream()
//                    .collect(Collectors.groupingBy(o -> ((ChartData_XY) o).getVBarXAxisNames()))
//                    .forEach((string, list) -> {
//                        double asDouble = list.stream()
//                                .flatMapToDouble(
//                                        activeRecord -> DoubleStream.of(((ChartData_XY) activeRecord).getValue()))
//                                .reduce((acc, val) -> acc + val).getAsDouble();
//
//                        series.add(string, asDouble);
//
//                    });
        });
        ObservableList<XYChart.Series<String, Number>> allSets = series.getAllSets();
        return allSets;
    }

    public static ObservableList<XYChart.Series<String, Number>> getVBarDataByPeriod(Class aClass, Calendar before, Calendar after, ChartData_VBar dataVBar ){
        SeriesStringNumber series = new SeriesStringNumber();
        ObservableList<ActiveRecord> activeRecords=ChartRecordsGetter.getRecords(aClass);
        Map<String, List<ActiveRecord>> map =
                activeRecords.stream().collect(Collectors.groupingBy(dataVBar::getSeriesName));


//        map.entrySet().stream().forEach(e -> {
//            String name = e.getKey();
//            series.createSet(name);
//           namee.getValue().stream()
//                    .filter(activeRecord ->((ChartData_XY) activeRecord).getCalendar().after(after) )
//                    .filter(activeRecord ->((ChartData_XY) activeRecord).getCalendar().before(before) )
//                    .collect(Collectors.groupingBy(o -> ((ChartData_XY) o).getVBarXAxisNames()))
//                    .forEach((string, list) -> {
//                                double asDouble = list.stream()
//                                        .flatMapToDouble(
//                                                activeRecord -> DoubleStream.of(((ChartData_XY) activeRecord).getValue()))
//                                        .reduce((acc, val) -> acc + val).getAsDouble();
//
//                                series.add(string, asDouble);
//
//                                }
//                        );}
//                    );

//        return     series.getAllSets();
        return     null;
    }

}
