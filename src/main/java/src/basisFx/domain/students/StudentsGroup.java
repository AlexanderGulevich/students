package basisFx.domain.students;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

public class StudentsGroup extends ActiveRecord {

        private static StudentsGroup INSTANCE = new StudentsGroup();
        public static StudentsGroup getINSTANCE() {
            return INSTANCE;
        }

        private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);

        private SimpleObjectProperty<Faculty> faculty =new SimpleObjectProperty<>(this, "faculty", null);

        public String getName() {
            return name.get();
        }
        public SimpleObjectProperty<String> nameProperty() {
            return name;
        }
        public void setName(String name) {
            this.name.set(name);
        }

    public Faculty getFaculty() {
        return faculty.get();
    }

    public SimpleObjectProperty<Faculty> facultyProperty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty.set(faculty);
    }

    @Override
        public String toString() {
            return getName();
        }




}