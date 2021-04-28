package basisFx.appCore.menu;

import basisFx.appCore.interfaces.CallBack;

public interface MenuComponent {
      boolean isComposit();
      void setParent(MenuComponent component);
      void setCallBack(CallBack callBack);
      CallBack getCallBack();
      boolean hasParent();
}
