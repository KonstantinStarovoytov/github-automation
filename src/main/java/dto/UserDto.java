package dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Value
public class UserDto {

    String name;
    String login;
    String password;
    List<RepositoryDto> repositories;
}