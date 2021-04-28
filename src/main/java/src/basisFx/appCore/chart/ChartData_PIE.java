package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;

import java.util.GregorianCalendar;

public interface ChartData_PIE {
    String getSeriesName(ActiveRecord record);
    GregorianCalendar getCalendar(ActiveRecord record);
    double getValue(ActiveRecord record);
}
