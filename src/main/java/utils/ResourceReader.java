package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import dto.UserDto;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class ResourceReader {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @SneakyThrows
    public static UserDto getDefaultUser(){
        var url = getResource("user.json");
        var json = Resources.toString(url, UTF_8);
        return mapper.readValue(json, UserDto.class);
    }
}
