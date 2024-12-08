package When_To_Remember.Models.Dtos;

import When_To_Remember.Models.Matter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.UUID;

public record UserDto(UUID id, String name, String email, String password,
                      @JsonIgnore
                      List<Matter> matters) {
}
