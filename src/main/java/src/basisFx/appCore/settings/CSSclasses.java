package basisFx.appCore.settings;

import java.util.prefs.AbstractPreferences;

public enum CSSclasses {
    LEFT_SIDE_MENU_HORIZONTAL_BUTTONS("LEFT_SIDE_MENU_HORIZONTAL_BUTTONS"),
    LEFT_SIDE_MENU_HORIZONTAL_BUTTONS_CLICKED("LEFT_SIDE_MENU_HORIZONTAL_BUTTONS_CLICKED"),
    LEFT_SIDE_MENU_VERTICAL_BUTTONS("LEFT_SIDE_MENU_VERTICAL_BUTTONS"),
    LEFT_SIDE_MENU_VERTICAL_BUTTONS_CLICKED("LEFT_SIDE_MENU_VERTICAL_BUTTONS_CLICKED"),
    LEFT_SIDE_MENU_VERTICAL_BUTTONS_FIRST("LEFT_SIDE_MENU_VERTICAL_BUTTONS_FIRST"),
    TABLE_BFx("TABLE_BFx"),
    BUT_IN_COLUMN_BFx("BUT_IN_COLUMN_BFx"),
    REGISTRY_BUTTONS_BFx("REGISTRY_BUTTONS_BFx"),
    RADIO_BFx("RADIO_BFx"),
    DATEPICKER_BFx("DATEPICKER_BFx"),
    TEXTFIELD_BFx("TEXTFIELD_BFx"),
    table_column_buttons_BFx("table_column_buttons_BFx"),
    wrappedHeaderColumn("wrappedHeaderColumn"),
    column_with_button_BFx("column_with_button_BFx"),
    first_child_Red("first_child_Red"),
    LABEL_COMMON("LABEL_COMMON"),
    COMBOBOX_BFx_forGrid("COMBOBOX_BFx_forGrid"),
    COMBOBOX_BFx("COMBOBOX_BFx");


    private final String id;

    private CSSclasses(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }
    

    
}
