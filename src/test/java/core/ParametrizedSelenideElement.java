package core;

import com.codeborne.selenide.SelenideElement;
import lombok.NonNull;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

/**
 * Wrapper around {@link SelenideElement} with ability to pass parameters to locator
 */
@FunctionalInterface
public interface ParametrizedSelenideElement {

    /**
     * Method to pass parameters to SelenideElement locator
     * Will inject parameters instead of '%s','%d' and ect. format symbols like {@link String#format(String, Object...)}
     * ATTENTION! This method doesn't start any search yet!
     * @param parameters that will be injected instead of format symbols
     * @return SelenideElement
     * @see String#format(String, Object...)
     */
    SelenideElement params(@NonNull Object... parameters);

    /**
     * Locates the first element matching given CSS selector after injecting params via {@link #params(Object...)}
     * @param cssSelector any CSS selector like "input[name='%s']" or "#messages .new_message%d"
     * @return ParametrizedSelenideElement
     */

    static ParametrizedSelenideElement $p(String cssSelector) {
        return parameter -> $(format(cssSelector, parameter));
    }

    /**
     * Locates the first element matching given XPATH expression after injecting params via {@link #params(Object...)}
     * @param xpathExpression any XPATH expression //*[@id='%s'] //E[contains(@A, '%s')]
     * @return ParametrizedSelenideElement
     */
    static ParametrizedSelenideElement $px(String xpathExpression) {
        return parameter -> $x(format(xpathExpression, parameter));
    }
}