package pages;


import components.Header;
import core.annotations.Lazywired;
import core.annotations.PageObject;
import lombok.Getter;

@PageObject
@Getter
public class MainPage extends BasePage{

    @Lazywired
    public Header header;

}