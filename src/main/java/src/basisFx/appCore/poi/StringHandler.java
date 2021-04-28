package basisFx.appCore.poi;

public class StringHandler {
    

    public String changeWord(String str,String seach,String change){
          
         if(str.contains(seach)){
                  
                String  beginning=str.substring(0,str.indexOf(seach));
                          
                String ending=str.substring((str.indexOf(seach))+ seach.length());
                            
                String out=beginning+change+ending;
          
            return clearEdges(out) ;
        }else {
            return str;
        }
    }
    
    public String delDimension(String str, String seach,String end){
        if(str.contains(seach)){
           String beginning=str.substring(0,str.indexOf(seach));
           String ending=str.substring(str.indexOf(end, str.indexOf(seach))+1);

           String out=beginning+ending;

             return  clearEdges(out) ;
        }else {
            return str;
        }
        
    }
    public String delDimensionFromStringToEnd(String str, String seach){
        if(str.contains(seach)){
           String out=str.substring(0,str.indexOf(seach));

         
             return  clearEdges(out) ;
        }else {
            return str;
        }
        
    }
    public String delText(String str, String seach){
        if(str.contains(seach)){
            
           Integer delLength=seach.length();
           Integer indexFirstFinding=str.indexOf(seach);
           String beginning=str.substring(0,indexFirstFinding);
           //изменил ниже строку
           //было  
           //String ending=str.substring(str.indexOf(indexFirstFinding+delLength, str.indexOf(seach))+1);
           String ending=str.substring(indexFirstFinding+delLength);
           String out=beginning+ending;

             return  clearEdges(out) ;
        }else {
//            System.err.println("not founded");
            return str;
            
        }
        
    }
    
    public String clearEdges(String str){
        
           str=str.replaceAll("[\\s]{2,}", " ").trim();// удалить если есть несколько пробелов подряд
           
           if(str.charAt(str.length()-1)==','){
                        str.substring(0, str.length()-1);
                    }
    
        return str ;
    }
    
    public String delAllSpace(String str){
        
//         str=str.replaceAll("\\s+","");
//         str=str.trim().replaceAll(" +", " ");
              str=str.replaceAll("\\s","");   
              str=str.replaceAll(" ","");   //WTF what is a symbol???? 
              
              
              
         return str ;
    }
    
    
    
    
    
}
