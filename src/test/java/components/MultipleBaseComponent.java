package components;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class MultipleBaseComponent {

    private final SelenideElement self;

    public SelenideElement self() {
        return self;
    }
}