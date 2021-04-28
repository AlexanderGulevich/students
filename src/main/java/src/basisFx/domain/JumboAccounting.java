package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.RecordWithDate;
import basisFx.dataSource.Db;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JumboAccounting extends ActiveRecord implements RecordWithDate {

    private static JumboAccounting INSTANCE = new JumboAccounting();
    private SimpleObjectProperty<Counterparty> counterparty=new SimpleObjectProperty<>(this, "counterparty", null);
    private SimpleObjectProperty<LocalDate> date =new SimpleObjectProperty<>(this, "date", null);
    private SimpleObjectProperty<Double> overallWeight=new SimpleObjectProperty<>(this, "overallWeight", null);

    public static JumboAccounting getINSTANCE() {
        return INSTANCE;
    }

    public Counterparty getCounterparty() {
        return counterparty.get();
    }

    public SimpleObjectProperty<Counterparty> counterpartyProperty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty.set(counterparty);
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public Double getOverallWeight() {
        return overallWeight.get();
    }

    public SimpleObjectProperty<Double> overallWeightProperty() {
        return overallWeight;
    }

    public void setOverallWeight(Double overallWeight) {
        this.overallWeight.set(overallWeight);
    }

    public static JumboAccounting getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> getAllByDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        ObservableList <ActiveRecord> list=FXCollections.observableArrayList();
        String expression="SELECT * FROM " +this.entityName+" where date=? " +" ORDER BY id";
        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                JumboAccounting pojo=new JumboAccounting();
                pojo.setId(rs.getInt("id"));
                pojo.setCounterparty((Counterparty) Counterparty.getINSTANCE().find(rs.getInt("Counterpartyid")));
                pojo.setDate(rs.getDate("date").toLocalDate());
                pojo.setOverallWeight(rs.getDouble("overallWeight"));

                list.add(pojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
