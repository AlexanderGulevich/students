package basisFx.appCore.utils;

import basisFx.appCore.windows.WindowFabric;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.service.WindowService;

import java.util.HashMap;

public class Registry   {
   public static WindowFabric windowFabric;
   public static WindowAbstraction mainWindow;

   public static HashMap<String, WindowService> crossWindowMediators =new HashMap<>();
   public static HashMap<String, Object> dataExchanger =new HashMap<>();


}

