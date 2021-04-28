/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.appCore.utils;

import javafx.scene.Scene;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CSSHandler {

        private static CSSHandler instanse;
        private static Scene scene;

    public static CSSHandler getInstanse(){

        if(instanse==null) {
            instanse=new CSSHandler();
        }else{
             return instanse;
        }
        return instanse;
    }



    public void loadStylesToSceneFromDirectory(Scene scene){

        URI uri=null;
        File folder=null;
        File[] listOfFiles=null;

        String path = System.getProperty("user.dir") ;
        path = path.replace("\\","/");
        folder = new File(path);
        listOfFiles = folder.listFiles();

    }

    public void loadStylesToSceneFromJar(Scene scene){
        List<String> list=new ArrayList<>();
        list.add("borders.css");
        list.add("buttons.css ");
        list.add("chart.css ");
        list.add("colors.css");
        list.add("combobox.css");
        list.add("combobox_editable.css");
        list.add("leftSideMenu.css");
        list.add("dataPicker.css");
        list.add("fonts.css ");
        list.add("label.css");
        list.add("popup.css");
        list.add("leftSideMenuOnlyVertical.css");
        list.add("menuBar.css ");
        list.add("panels.css ");
        list.add("radio.css");
        list.add("styles.css ");
        list.add("tables.css");
        list.add("scrollpane.css");
        list.add("textField.css");
        list.add("windows.css");
        list.add("scrollbar.css");
        list.add("textArea.css");


        list.forEach(s -> scene.getStylesheets().addAll(PathToFileUtils.getResourseExternalForm("/css/"+s.trim())));




    }

    private void addStylesheetsToSceen(Scene scene, File[] listOfFiles) {
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                String fileName = listOfFile.getName();
                File parentFolder = listOfFile.getParentFile();

                scene.getStylesheets().addAll(PathToFileUtils.getFilePath("/src/res/res/css/"+parentFolder.getName()+"/"+fileName));

            }
        }
    }


}
