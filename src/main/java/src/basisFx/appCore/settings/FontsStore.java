/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.appCore.settings;

/**
 *
 * @author Alek
 */
public enum FontsStore {


    FAWESOME5REGULAR("/fonts/Font-Awesome-5-Free-Regular-400.ttf"),
    FAWESOME5SOLID("/fonts/Font-Awesome-5-Free-Solid-900.ttf"),
    WEBHOSTINGHUB("/fonts/webhostinghub-glyphs.ttf"),
    fontcustom("/fonts/foundation-icons.ttf"),
    IONICONS("/fonts/ionicons.ttf"),
    THEMIFY("/fonts/themify.ttf"),
    BATCH("/fonts/batch-icons-webfont.ttf"),
    MATERIAL_ICONS("/fonts/MaterialIcons-Regular.ttf"),

    MARGOT("/fonts/Margot.ttf"),
    ROBOTO_BOLD("/fonts/Roboto-Bold.ttf"),
    ROBOTO_LIGHT("/fonts/Roboto-Light.ttf"),
    FIRA_BOLD("/fonts/FiraSans-Bold.ttf");
    

    
    private final String path;

    private FontsStore(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
    
 
}
