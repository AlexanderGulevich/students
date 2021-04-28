package basisFx.domain.price;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class GoodsPojo implements Serializable{
    
    private  StringProperty priceCategory =
			 new SimpleStringProperty(this, "priceCategory", null);
    private  IntegerProperty isInArchiv =
			 new SimpleIntegerProperty(this, "isInArchiv", 0);
    private  StringProperty realCategory =
			 new SimpleStringProperty(this, "realCategory", null);
    private  StringProperty productNamePlan =
			 new SimpleStringProperty(this, "productNamePlan", null);
    private  StringProperty productNameCommon =
			 new SimpleStringProperty(this, "productNameCommon", null);
    private  StringProperty productNamePrice =
			 new SimpleStringProperty(this, "productNamePrice", null);
    private  StringProperty order =
			 new SimpleStringProperty(this, "order", null);
    private  StringProperty measure =   //единица измерения
			 new SimpleStringProperty(this, "name", null);
    private  DoubleProperty amountOfOrder =   
			 new SimpleDoubleProperty(this, "amountOfOrder", 0);
    private  StringProperty amountInBox = 
			 new SimpleStringProperty(this, "amountInBox", null);
    private  DoubleProperty amountOfPrice =   
			 new SimpleDoubleProperty(this, "amountOfPrice", 0);
    private  DoubleProperty emittedAmountOfOrder =   
			 new SimpleDoubleProperty(this, "emittedAmountOfOrder", 0);
    private  DoubleProperty remainAmountOfOrder =   
			 new SimpleDoubleProperty(this, "remainAmountOfOrder", 0);
    private  DoubleProperty pricePerUnit =   
			 new SimpleDoubleProperty(this, "pricePerUnit", 0);
    private  ObjectProperty<LocalDate> dateOfPrice =
			 new SimpleObjectProperty<>(this, "dateOfPrice", null);
    private  BooleanProperty closedOrder =
			 new SimpleBooleanProperty(this, "closedOrder", false);
    private  IntegerProperty year =   
			 new SimpleIntegerProperty(this, "year", 0);
    private  IntegerProperty leftOrderNum =   
			 new SimpleIntegerProperty(this, "leftOrderNum", 0);
    private  IntegerProperty id =   
			 new SimpleIntegerProperty(this, "id", 0);
    private  IntegerProperty categoryId =   
			 new SimpleIntegerProperty(this, "categoryId", 0);
    private  DoubleProperty percentRemained =   
			 new SimpleDoubleProperty(this, "percentRemained", 0);
    private  StringProperty amountOfOrderString =   
			 new SimpleStringProperty(this, "amountOfOrderString", null);
    private  StringProperty emittedAmountOfOrderString =   
			 new SimpleStringProperty(this, "emittedAmountOfOrderString", null);
    private  StringProperty remainAmountOfOrderString =   
			 new SimpleStringProperty(this, "remainAmountOfOrderString", null);
    private  StringProperty pricePerUnitString =   
			 new SimpleStringProperty(this, "pricePerUnitString", null);

    public String getPricePerUnitString() {
            return pricePerUnitString.get();
        }
    public void setPricePerUnitString(String stringPricePerUnit) {
        this.pricePerUnitString.set(stringPricePerUnit);
    }
    public String getRemainAmountOfOrderString() {
            return remainAmountOfOrderString.get();
        }
    public void setRemainAmountOfOrderString(String stringRemainAmount) {
        this.remainAmountOfOrderString.set(stringRemainAmount);
    }
    public String getAmountOfOrderString() {
            return amountOfOrderString.get();
        }
    public void setAmountOfOrderString(String stringAmountOfOrder) {
        this.amountOfOrderString.set(stringAmountOfOrder);
    }
    public String getEmittedAmountOfOrderString() {
            return emittedAmountOfOrderString.get();
        }
    public void setEmittedAmountOfOrderString(String stringEmittedAmount) {
        this.emittedAmountOfOrderString.set(stringEmittedAmount);
    }
    public Boolean getClosedOrder(){
    
        return this.closedOrder.get();
    }
    public BooleanProperty getClosedOrderProperty(){
    
        return this.closedOrder;
    }
    public void setClosedOrder(Boolean a){
        
        this.closedOrder.set(a);
    
    }   
    public Integer getIsInArchiv(){
    
        return this.isInArchiv.get();
    }
    public IntegerProperty getIsInArchivProperty(){
    
        return this.isInArchiv;
    }
    public void setIsInArchiv(Integer a){
        
        this.isInArchiv.set(a);
    
    }
    public String getProductNameCommon(){
    
        return this.productNameCommon.get();
    }
    public void setProductNameCommon(String n){
        
        this.productNameCommon.set(n);
    
    }
    public Integer getCategoryId(){
    
        return this.categoryId.get();
    }
    public void setCategoryId(Integer l){
        
        this.categoryId.set(l);
    
    }
    public Integer getId(){
    
        return this.id.get();
    }
    public void setId(Integer l){
        
        this.id.set(l);
    
    }
    public Integer getLeftOrderNum(){
    
        return this.leftOrderNum.get();
    }
    public void setLeftOrderNum(Integer l){
        
        this.leftOrderNum.set(l);
    
    }
    public Integer getYear(){
    
        return this.year.get();
    }
    public void setYear(Integer y){
        
        this.year.set(y);
    
    }
    public String getPriceCategory() {
        return priceCategory.get();
    }
    public void setPriceCategory(String priceCategory) {
        this.priceCategory.set(priceCategory);
    }
    public String getRealCategory() {
        return realCategory.get();
    }
    public void setRealCategory(String realCategory) {
        this.realCategory.set(realCategory);
    }
    public String getProductNamePlan() {
        return productNamePlan.get();
    }
    public void setProductNamePlan(String productNamePlan) {
        this.productNamePlan.set(productNamePlan);
    }
    public String getProductNamePrice() {
        return productNamePrice.get();
    }
    public void setProductNamePrice(String productNameTNP) {
        this.productNamePrice.set(productNameTNP);
    }
    public String getOrder() {
        return order.get();
    }
    public void setOrder(String order) {
        this.order.set(order);
    }
    public String getMeasure() {
        return measure.get();
    }
    public void setMeasure(String measure) {
        this.measure.set(measure);
    }
    public Double getAmountOfOrder() {
        return amountOfOrder.get();
    }
    public void setAmountOfOrder(Double amountOfOrder) {
        this.amountOfOrder.set(amountOfOrder);
    }
    public String getAmountInBox() {
        return amountInBox.get();
    }
    public void setAmountInBox(String amountInBox) {
        this.amountInBox.set(amountInBox);
    }
    public Double getAmountOfPrice() {
        return amountOfPrice.get();
    }
    public void setAmountOfPrice(Double amountOfPrice) {
        this.amountOfPrice.set(amountOfPrice);
    }
    public Double getEmittedAmountOfOrder() {
        return emittedAmountOfOrder.get();
    }
    public void setEmittedAmountOfOrder(Double emittedAmountOfOrder) {
        this.emittedAmountOfOrder.set(emittedAmountOfOrder);
    }
    public Double getRemainAmountOfOrder() {
        return remainAmountOfOrder.get();
    }
    public void setRemainAmountOfOrder(Double remainAmountOfOrder) {
        this.remainAmountOfOrder.set(remainAmountOfOrder);
    }
    public Double getPricePerUnit() {
        return pricePerUnit.get();
    }
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit.set(pricePerUnit);
    }
    public LocalDate getDateOfPrice() {
        return dateOfPrice.get();
    }
    public void setDateOfPrice(LocalDate dateOfPrice) {
        this.dateOfPrice.set(dateOfPrice);
    }
    public Double getPercentRemained() {
        return percentRemained.get();
    }
    public void setPercentRemained(Double percentRemained) {
        this.percentRemained.set(percentRemained);
    }
    
    
}
