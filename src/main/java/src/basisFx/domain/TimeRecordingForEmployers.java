package basisFx.domain;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.RecordWithDate;
import basisFx.dataSource.Db;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class TimeRecordingForEmployers extends ActiveRecord implements RecordWithDate {
    private static TimeRecordingForEmployers INSTANCE = new TimeRecordingForEmployers();
    private SimpleObjectProperty<Employer> employer =new SimpleObjectProperty<>(this, "employer", null);
    private SimpleObjectProperty<Double> hours =new SimpleObjectProperty<>(this, "hours", null);
    private SimpleObjectProperty<LocalDate> date =new SimpleObjectProperty<>(this, "date", null);

    public static TimeRecordingForEmployers getINSTANCE() {
        return INSTANCE;
    }

    public Double getHours() {
        return hours.get();
    }

    public SimpleObjectProperty<Double> hoursProperty() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours.set(hours);
    }

    public Employer getEmployer() {
        return employer.get();
    }

    public SimpleObjectProperty<Employer> employerProperty() {
        return employer;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    @Override
    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public void setEmployer(Employer employer) {
        this.employer.set(employer);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ObservableList<ActiveRecord> getAllByDate(LocalDate date) {
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();


            String expression=
                    "  SELECT EMPLOYERID, HOURS,NAME,ISFIRED,ID, date FROM (\n" +
                            "\t          \t SELECT * FROM EMPLOYER e LEFT JOIN TIMERECORDINGFOREMPLOYERS t ON t.EMPLOYERID=e.ID \n" +
                            "\t             WHERE  NOT(ISFIRED=TRUE ) and NOT (date is  NULL) AND  DATE=? \n" +
                            "\t             )AS records\n" +
                            "\t             RIGHT JOIN EMPLOYER em ON em.ID=records.EMPLOYERID\n" +
                            "\t             WHERE  NOT(em.ISFIRED=TRUE )";

             try {
                PreparedStatement pstmt = Db.connection.prepareStatement(expression);
                pstmt.setDate(1, Date.valueOf(date));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    TimeRecordingForEmployers pojo=new TimeRecordingForEmployers();
                    pojo.setDate(inspectDate(rs));
                    pojo.setHours(rs.getDouble("hours"));
                    pojo.setEmployer((Employer) Employer.getINSTANCE().find( rs.getInt("id")));

                    list.add(pojo);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return list;
    }

    private Boolean inspectExisting()     {
        String expression="SELECT  * from " + this.entityName + " " +
                "where " +
                "  EMPLOYERID= ? and date = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = Db.connection.prepareStatement(expression);
            pstmt.setInt(1, getEmployer().getId());
            pstmt.setDate(2, Date.valueOf(getDate()));

            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private LocalDate inspectDate(ResultSet rs) throws SQLException {
        Date date = rs.getDate("date");
        if (date != null) {
            return date.toLocalDate();
        }
        return null;

    }

    @Override
    public void insert() {
        if (getHours()==0d){
            delete();
        }
        else if (inspectExisting()) {
            update();
        }else {
            super.insert();
        }


    }

    @Override
    public void update() {


        String expression = "UPDATE " + this.entityName + " SET  " +
                " HOURS = ?" +
                " WHERE EMPLOYERID= ? and date=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = Db.connection.prepareStatement(expression);
            pstmt.setDouble(1, getHours());
            pstmt.setInt(2, getEmployer().getId());
            pstmt.setDate(3, Date.valueOf(getDate()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete() {
        try {
            String expression="delete from " +entityName+"  WHERE EMPLOYERID= ? and date=?  ";
            PreparedStatement pstmt =  Db.connection.prepareStatement(expression);
            pstmt.setInt(1,getEmployer().getId());
            pstmt.setDate(2, Date.valueOf(getDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
