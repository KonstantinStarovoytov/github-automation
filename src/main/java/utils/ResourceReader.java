package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import dto.UserDto;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;

@UtilityClass
@Slf4j
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

        return mapper.readValue(json, UserDto.class)
                .toBuilder()
                .login(System.getProperty("login"))
                .password(System.getProperty("password"))
                .build();
    }

    public  <Type extends Serializable & Comparable<Type>> Type getProperty(@NonNull JsonNode context, @NonNull String jsonPath) {
        String key = jsonPath.replace("/", ".").substring(1);
        final Optional<String> property = ofNullable(System.getProperty(key));
        property.ifPresent(value -> log
                .info(String.format("Using variable passed from environment %s=%s", key, value)));

        if (context.at(jsonPath).isBoolean()) {
            return (Type) property.map(Boolean::parseBoolean).orElseGet(context.at(jsonPath)::asBoolean);
        } else if (context.at(jsonPath).isLong()) {
            return (Type) property.map(Long::parseLong).orElseGet(context.at(jsonPath)::asLong);
        } else if (context.at(jsonPath).isInt()) {
            return (Type) property.map(Integer::parseInt).orElseGet(context.at(jsonPath)::asInt);
        } else {
            return (Type) property.orElseGet(context.at(jsonPath)::asText);
        }
    }
}
