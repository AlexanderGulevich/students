package basisFx.appCore.windows;


import basisFx.appCore.utils.Registry;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class WindowInfoDispatcher {
    private static List  <String> text=new ArrayList<>();
    public static void add(String  string){
        text.add(string);
    }
    public static void run(){
            text.stream().forEach(s -> Platform.runLater(() -> Registry.windowFabric.infoWindow(s)) );
            text.clear();

        }

        }


