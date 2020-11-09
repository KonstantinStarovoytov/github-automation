package dto;

import constants.Language;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Jacksonized
@Builder
@Value
public class RepositoryDto {

    String name;
    Language language;
    String description;
    LocalDate lastUpdate;
}