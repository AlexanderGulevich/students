package basisFx.appCore.utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Range {

    ACTUAL("Актуальное значение"),
    LAST10("Последние 10 записей"),
    LAST30("Последние 30 записей"),
    DAY30("За 30 дней"),
    DAY60("За 60 дней"),
    DAY90("За 90 дней"),
    DAY180("За 180 дней"),
    YEAR2("За 2 года"),
    YEAR3("За 3 года"),
    YEAR("За текущий год"),
    MONTH("За текущий месяц"),
    ALLTIME("За все время");

    private final String name;
    private Range main;

    Range(String path) {
        this.name = path;
    }

    public String getName() {
        return name;
    }
    public String toString() {return getName();}

    public Range get(String s){
       return  Range.valueOf(s);
    }

    public static  ObservableList<Range> getParticular(Range... subsequent){
        return FXCollections.observableList(Arrays.asList(subsequent));
    }
    public static  ObservableList<Range> getAll(){
        List<Range> list = Arrays.asList(Range.values());
        return  FXCollections.observableList(list);
    }
    public static  ObservableList<Range> getAllPeriodicaly(){
        return  FXCollections.observableList(
                Arrays.stream(Range.values())
                        .filter(range -> range!=ACTUAL)
                        .filter(range -> range!=LAST30)
                        .filter(range -> range!=LAST10)
                        .collect(Collectors.toList())
        );
    }
    public static  ObservableList<Range> get(Range ...range){
        List<Range> list = Arrays.asList(range);
        return  FXCollections.observableList(list);
    }
    public Range getMain() {
        return main;
    }



}
