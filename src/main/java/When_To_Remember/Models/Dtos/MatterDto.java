package When_To_Remember.Models.Dtos;

import java.util.UUID;

public record MatterDto(UUID id, String title, String description, String difficult) {
}
