package basisFx.appCore.table;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.interfaces.DataStoreCallBack;
import javafx.scene.control.TableColumn;
public  abstract class ColWrapper<T,K> {

    public TableWrapper tableWrapper;
    public String propertyName;
    public String columnName;
    public Double columnSize;
    public Boolean isEditeble;
    protected static String message=   "Произошла ошибка.\nВ поле было введено неправильное значение.";

    public abstract void setOnEditCommit();
    public abstract TableColumn<T, K> getColumn();
    protected abstract boolean checkValue(TableColumn.CellEditEvent<T, K> event);
    protected abstract boolean checkValue(String s);

}
