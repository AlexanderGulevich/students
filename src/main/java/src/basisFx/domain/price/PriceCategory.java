package basisFx.domain.price;

import java.util.ArrayList;

public class PriceCategory {
     
            String name;
            
            
            ArrayList<PriceItem> categoryFilds;
            
            public void setName(String n){
                this.name=n;
            }
            public String getName() {
                return name;
            }
            public ArrayList<PriceItem> getFilds() {
                return categoryFilds;
            }
            public void setFilds(ArrayList<PriceItem> filds) {
                this.categoryFilds = filds;
            }

}
