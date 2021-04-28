package basisFx.appCore.poi;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Alek
 */
public class CategoryPojo   {

    public CategoryPojo(String name ) {
        
        setCategoryNameOfTnp(name);

        
    }
    public CategoryPojo() {}
    
 
        private  StringProperty categoryNameOfTnp =
			 new SimpleStringProperty(this, "categoryNameOfTnp", null); 
        
        private  StringProperty shortName =
			 new SimpleStringProperty(this, "shortName", null);  
              
        private  IntegerProperty leftId =
			 new SimpleIntegerProperty(this, "leftId", 0);  
        
        private  IntegerProperty rightId =
			 new SimpleIntegerProperty(this, "rightId", 0);  
        
        private  IntegerProperty id =
			 new SimpleIntegerProperty(this, "id", 0);  
        
        private  IntegerProperty levelId =
			 new SimpleIntegerProperty(this, "levelId", 0);  
        
        private  BooleanProperty isExpanded =
			 new SimpleBooleanProperty(this, "levelId", false);  
        
        
        //categoryNameOfTnp accessors
         public Boolean getIsExpanded() {
                 return this.isExpanded.get();
    }
         public void setIsExpanded(int state) {
                if(state==0){
                this.isExpanded.set(false);
        
                 }
                if(state==1){
                    this.isExpanded.set(true);
                }
                  
    } 
         public  BooleanProperty isExpandedProperty() {
		return isExpanded;
	}
         public String getShortName() {
                 return this.shortName.get();
    }
         public void setShortName(String shortName) {
                  this.shortName.set(shortName);
    }
        //categoryNameOfTnp accessors
         public String getCategoryNameOfTnp() {
                 return this.categoryNameOfTnp.get();
    }
         public void setCategoryNameOfTnp(String categoryName) {
                  this.categoryNameOfTnp.set(categoryName);
    }
         public  StringProperty categoryNameOfTnpProperty() {
		return categoryNameOfTnp;
	}
        //leftId accessors
         public Integer getLeftId() {
                 return this.leftId.get();
    }
         public void setLeftId(Integer id) {
                  this.leftId.set(id);
    }
         public  IntegerProperty leftIdProperty() {
		return leftId;
	}
        //rightId accessors
         public Integer getRightId() {
                 return this.rightId.get();
    }
         public void setRightId(Integer id) {
                  this.rightId.set(id);
    }
         public  IntegerProperty rightIdProperty() {
		return rightId;
	}
        //id accessors
         public Integer getId() {
                 return this.id.get();
    }
         public void setId(Integer id) {
                  this.id.set(id);
    }
         public  IntegerProperty idProperty() {
		return id;
	}
        //levelId accessors
         public Integer getLevelId() {
                 return this.levelId.get();
    }
         public void setLevelId(Integer id) {
                  this.levelId.set(id);
    }
         public  IntegerProperty levelIdProperty() {
		return levelId;
	}
         
         public boolean hasDescendants(){
             
            
             if (this.getLeftId()==this.getRightId()-1) {
                 
                 return false;
             }else{
                 

                 return true;
             
             }
         }
         
}
