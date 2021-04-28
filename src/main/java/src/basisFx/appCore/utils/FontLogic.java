/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.appCore.utils;

import basisFx.appCore.settings.FontsStore;
import javafx.scene.text.Font;

public class FontLogic {
    

    
    private static FontLogic instance;
    
    private FontLogic() {}
    
    public static FontLogic run() {
        
        if(instance==null) {
            instance=new FontLogic();
        }else{
             return instance;
        }
        return instance;
    }
        
    
  
    
    public static Font loadFont(FontsStore fs, double size){

        Font f=Font.loadFont(
                PathToFileUtils.getResourseExternalForm( fs.getPath()), size);
        
        if(f!=null ){
                     return f;
        }else{
        
             throw new NullPointerException("setFont()- null after loading  in FontLogic.loadFont()"); 
        
        }
        
      

    }
    
    

    
}
