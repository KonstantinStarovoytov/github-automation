package core;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.MultipleBaseComponent;
import core.annotations.RootLocator;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

@UtilityClass
public class ComponentUtils {

    public static <Type extends MultipleBaseComponent> List<Type> listOf(Class<Type> componentClass) {
        return listOf($$(get(componentClass.getAnnotation(RootLocator.class).value())), componentClass);
    }

    private static <Type extends MultipleBaseComponent> List<Type> listOf(ElementsCollection elements, Class<Type> componentClass) {
        return elements.stream().map(self -> createInstance(componentClass, self)).collect(toList());
    }

    @SneakyThrows
    private static <Type extends MultipleBaseComponent> Type createInstance(Class<Type> beanClass, SelenideElement self) {
        return invokeConstructor(beanClass, self);
    }

    public static By get(String locator) {
        if (locator.startsWith("/")){
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }
}