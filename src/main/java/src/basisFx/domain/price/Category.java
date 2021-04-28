//package basisFx.domain.price;
//
//import basisFx.appCore.activeRecord.ActiveRecord;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.collections.ObservableList;
//
//public class Category extends ActiveRecord {
//
//    private static Category INSTANCE = new Category();
//    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
//
//    public static Category getINSTANCE() {
//        return INSTANCE;
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public SimpleObjectProperty<String> nameProperty() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public String toString(){
//        return getName();
//    }
//
//    @Override
//    public ObservableList<ActiveRecord> findAllByOuterId(int id) {
//        return null;
//    }
//
//
//}
