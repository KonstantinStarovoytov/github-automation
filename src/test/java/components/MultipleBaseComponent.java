package components;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MultipleBaseComponent {

    private final SelenideElement self;

    public SelenideElement self() {
        return self;
    }
}