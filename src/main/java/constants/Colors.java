package constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Colors {
    BROWN("#b07219", "rgba(176, 114, 25, 1)"),
    BLUE("#4F5D95","rgba(79, 93, 149, 1)"),
    YELLOW("#f1e05a", "rgba(241, 224, 90, 1)");

    private final String colorCode;
    private final String rgba;
}