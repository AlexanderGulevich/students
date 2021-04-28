package basisFx.domain.students;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.annotation.DataStore;
import basisFx.dataSource.Db;
import basisFx.domain.Counterparty;
import basisFx.domain.price.OutputTemplate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student  extends ActiveRecord {

    private static Faculty INSTANCE = new Faculty();
    public static Faculty getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<String> city =new SimpleObjectProperty<>(this, "city", null);
    private SimpleObjectProperty<String> enterprise =new SimpleObjectProperty<>(this, "enterprise", null);

    private SimpleObjectProperty<LocalDate> birthday =new SimpleObjectProperty<>(this, "birthday", null);

    private SimpleObjectProperty<StudentsGroup> studentsGroup  =new SimpleObjectProperty<>(this, "studentsGroup", null);
    @DataStore  (NOT_CHECK_FOR_TRANSACTIONS = true)
    private SimpleObjectProperty<Faculty> faculty  =new SimpleObjectProperty<>(this, "faculty", null);

    private SimpleObjectProperty<Male> male =new SimpleObjectProperty<>(this, "male", null);

    public Faculty getFaculty() {
        return faculty.get();
    }

    public SimpleObjectProperty<Faculty> facultyProperty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty.set(faculty);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleObjectProperty<String> cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getEnterprise() {
        return enterprise.get();
    }

    public SimpleObjectProperty<String> enterpriseProperty() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise.set(enterprise);
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup.get();
    }

    public SimpleObjectProperty<StudentsGroup> studentsGroupProperty() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup.set(studentsGroup);
    }

    public Male getMale() {
        return male.get();
    }

    public SimpleObjectProperty<Male> maleProperty() {
        return male;
    }

    public void setMale(Male male) {
        this.male.set(male);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
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
        return getName();
    }


    @Override
    public ObservableList<ActiveRecord> getAll() {
        ObservableList <ActiveRecord> list= FXCollections.observableArrayList();
        String expression="SELECT * FROM " +this.entityName+" ORDER BY ID";
        try {
            Statement stmt  = Db.connection.createStatement();
            ResultSet rs    = stmt.executeQuery(expression);
            while (rs.next()) {
                Student st=new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name") );
                st.setCity(rs.getString("city") );
                st.setEnterprise(rs.getString("enterprise") );
                st.setMale(((Male) Male.getINSTANCE().find(rs.getInt("maleid"))));
                st.setStudentsGroup(((StudentsGroup) StudentsGroup.getINSTANCE().find(rs.getInt("studentsGroupid"))));
                st.setBirthday(rs.getDate("birthday").toLocalDate());


                list.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    @Override
    public void insert() {
        try {
            String expression = "INSERT INTO " + this.entityName
                    + "("
                    + " name ,  "
                    + " city ,  "
                    + " enterprise ,  "
                    + " maleid ,  "
                    + " studentsGroupid ,  "
                    + " birthday   "
                    + ") VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, getName());
            pstmt.setString(2, getCity());
            pstmt.setString(3, getEnterprise());
            pstmt.setInt(4, getMale().getId());
            pstmt.setInt(5, getStudentsGroup().getId());
            pstmt.setDate(6, Date.valueOf(getBirthday()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        String expression = "UPDATE " + this.entityName + " SET  "
                + " name ,  "
                + " city ,  "
                + " enterprise ,  "
                + " maleid ,  "
                + " studentsGroupid ,  "
                + " birthday   "
                +" WHERE id= ?";
        try {

            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, getName());
            pstmt.setString(2, getCity());
            pstmt.setString(3, getEnterprise());
            pstmt.setInt(4, getMale().getId());
            pstmt.setInt(5, getStudentsGroup().getId());
            pstmt.setDate(6, Date.valueOf(getBirthday()));
            pstmt.executeUpdate();

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}