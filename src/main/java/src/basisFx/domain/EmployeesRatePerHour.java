package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.annotation.DataStore;
import basisFx.appCore.annotation.Sorting;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class EmployeesRatePerHour extends ActiveRecord {

    private static EmployeesRatePerHour INSTANCE = new EmployeesRatePerHour();

    @DataStore(SORTING = Sorting.DESC, ANALIZED_DATE = true)
    private SimpleObjectProperty<LocalDate> startDate =new SimpleObjectProperty<>(this, "startDate", null);

    @DataStore (AS_OUTER_ID = true)
    private SimpleObjectProperty<Integer> employerId =new SimpleObjectProperty<>(this, "employerId", null);

    private SimpleObjectProperty<Double> rate =new SimpleObjectProperty<>(this, "rate", null);


    public static EmployeesRatePerHour getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return null;
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public Double getRate() {
        return rate.get();
    }

    public SimpleObjectProperty<Double> rateProperty() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate.set(rate);
    }

    public Integer getEmployerId() {
        return employerId.get();
    }

    public SimpleObjectProperty<Integer> employerIdProperty() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId.set(employerId);
    }
}

