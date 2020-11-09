package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static constants.Colors.*;

@AllArgsConstructor
@Getter
public enum Language {

    JAVA("Java", BROWN),
    JAVA_SCRIPT("JavaScript", YELLOW),
    PHP("PHP", BLUE);

    private final String name;
    private final Colors color;
}