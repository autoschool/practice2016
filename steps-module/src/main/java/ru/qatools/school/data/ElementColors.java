package ru.qatools.school.data;

/**
 * Created by totallynotkate (Kate Kocijevska).
 */
public enum ElementColors {
    NEW_WIDGET_BUTTON("rgba(66, 66, 66, 1)"),
    NEW_WIDGET_BUTTON_HOVER("rgba(96, 96, 96, 1)"),
    DELETE_WIDGET_BUTTON("rgba(255, 255, 255, 1)"),
    DELETE_WIDGET_BUTTON_HOVER("rgba(230, 230, 230, 1)");

    private final String rgbaColor;

    ElementColors(String rgbaColor){
        this.rgbaColor = rgbaColor;
    }

    public String getRgbaColor(){
        return rgbaColor;
    }

    @Override
    public String toString(){
        return rgbaColor;
    }
}
