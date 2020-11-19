package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static utils.ResourceReader.getProperty;

@UtilityClass
public class TestConfiguration {

    public static final String BASE_URL;
    public static final boolean IS_REMOTE;
    public static final String DRIVER_TYPE;
    public static final String DOWNLOAD_DIRECTORY;
    public static final String REMOTE_CONNECTION_URL;

    public static final int COMMAND_TIMEOUT;
    public static final int IMPLICIT_TIMEOUT;
    public static final int PAGE_LOAD_TIMEOUT;
    public static final int POLLING_INTERVAL;

    static {
        final var CONTEXT = getResourceFileContent();

        BASE_URL = getProperty(CONTEXT, "/common/baseUrl");
        IS_REMOTE = getProperty(CONTEXT, "/common/isRemote");
        DRIVER_TYPE = getProperty(CONTEXT, "/common/driverType");
        DOWNLOAD_DIRECTORY = getProperty(CONTEXT, "/common/downloadDirectory");
        REMOTE_CONNECTION_URL = getProperty(CONTEXT, "/common/remoteConnectionUrl");

        COMMAND_TIMEOUT = getProperty(CONTEXT, "/timeouts/timeoutCommand");
        IMPLICIT_TIMEOUT = getProperty(CONTEXT, "/timeouts/timeoutImplicit");
        PAGE_LOAD_TIMEOUT = getProperty(CONTEXT, "/timeouts/timeoutPageLoad");
        POLLING_INTERVAL = getProperty(CONTEXT, "/timeouts/pollingInterval");
    }

    private static JsonNode getResourceFileContent() {
        var settingsProfile = System.getProperty("profile") == null ? "settings.local.json" :
                "settings." + System.getProperty("profile") + ".json";
        try (var reader = TestConfiguration.class.getClassLoader().getResourceAsStream(settingsProfile)) {
            final var context = IOUtils.toString(requireNonNull(reader), UTF_8.name());
            return new ObjectMapper().readTree(context);
        } catch (IOException exception) {
            throw new UncheckedIOException(format("Reading of resource file '%s' was failed", settingsProfile), exception);
        }
    }
}
