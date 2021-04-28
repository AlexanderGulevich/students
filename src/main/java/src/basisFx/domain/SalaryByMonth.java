package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class SalaryByMonth extends ActiveRecord   {

    private static SalaryByMonth INSTANCE = new SalaryByMonth();
    private SimpleObjectProperty<Double> SALARY =new SimpleObjectProperty<>(this, "SALARY", null);
    private SimpleObjectProperty<Employer> employer  =new SimpleObjectProperty<>(this, "Employer", null);
    private SimpleObjectProperty<Integer> syear =new SimpleObjectProperty<>(this, "syear", null);



    private SimpleObjectProperty<Integer> smonth =new SimpleObjectProperty<>(this, "smonth", null);
    private SimpleObjectProperty<Boolean> isfired =new SimpleObjectProperty<>(this, "isfired", null);
    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);

    public static SalaryByMonth getINSTANCE() {
        return INSTANCE;
    }

    public Integer getSmonth() {
        return smonth.get();
    }

    public SimpleObjectProperty<Integer> smonthProperty() {
        return smonth;
    }

    public void setSmonth(Integer smonth) {
        this.smonth.set(smonth);
    }
    public Double getSALARY() {
        return SALARY.get();
    }

    public SimpleObjectProperty<Double> SALARYProperty() {
        return SALARY;
    }

    public void setSALARY(Double SALARY) {
        this.SALARY.set(SALARY);
    }

    public Employer getEmployer() {
        return employer.get();
    }

    public SimpleObjectProperty<Employer> employerProperty() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer.set(employer);
    }

    public Integer getSyear() {
        return syear.get();
    }

    public SimpleObjectProperty<Integer> syearProperty() {
        return syear;
    }

    public void setSyear(Integer syear) {
        this.syear.set(syear);
    }

    public Boolean getIsfired() {
        return isfired.get();
    }

    public SimpleObjectProperty<Boolean> isfiredProperty() {
        return isfired;
    }

    public void setIsfired(Boolean isfired) {
        this.isfired.set(isfired);
    }

    public String getName() {
        return name.get();
    }

    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }


}
