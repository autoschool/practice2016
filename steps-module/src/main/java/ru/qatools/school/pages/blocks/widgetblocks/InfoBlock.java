package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;


public class InfoBlock  extends HtmlElement {

    @Name("Титул информационной строчки")
    @FindBy(css = ".info-line__title")
    private HtmlElement infoTitle;

    @Name("Значение информационной строчки")
    @FindBy(css = ".info-line__value")
    private HtmlElement infoValue;

    @Name("Картинка информационной строчки")
    @FindBy(css  = "info-line__image")
    private HtmlElement infoImage;

    public HtmlElement getInfoTitle(){ return infoTitle; }

    public HtmlElement getInfoValue(){ return infoValue; }

    public HtmlElement getInfoImage() {return infoImage; }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
