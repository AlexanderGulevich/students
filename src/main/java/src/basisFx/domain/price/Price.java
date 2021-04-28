package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


public class Price  {

    @Getter @Setter
    private ArrayList<PriceCategory> categoriesArrayList=new ArrayList<>();
    @Getter @Setter
    private ArrayList<PriceCategory> newCategoriesArrayListToCustomeOutput=new ArrayList<>();
    @Getter @Setter
    private ObservableList<ActiveRecord> allRecords= FXCollections.observableArrayList();

    @Getter @Setter
    private Date priceDate=null;
    @Getter @Setter
    private Double totalSumma=null;
    @Getter @Setter
    private String priceDateString=null;
    @Getter @Setter
    private boolean filledItems=false;

    public  static   ObservableList<ActiveRecord>  allFromDB;

    public Price() {
        this.allFromDB = null;
    }

    public void createCategory(String name, ArrayList<PriceItem> priceItems){

        PriceCategory category=new PriceCategory();
        category.setFilds(priceItems);
        category.setName(name);
        categoriesArrayList.add(category);
        allRecords.addAll(priceItems);



    }






}