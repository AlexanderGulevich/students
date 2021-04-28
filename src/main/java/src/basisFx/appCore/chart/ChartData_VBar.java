package basisFx.appCore.chart;

import basisFx.appCore.activeRecord.ActiveRecord;

import java.util.GregorianCalendar;

public interface ChartData_VBar {
    String getSeriesName(ActiveRecord record);
    double getValue(ActiveRecord record);
}
