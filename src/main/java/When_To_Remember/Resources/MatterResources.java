package When_To_Remember.Resources;

import When_To_Remember.Models.Dtos.MatterDto;
import When_To_Remember.Models.Matter;
import When_To_Remember.Service.MatterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/matters")
public class MatterResources {
    private final MatterService matterService;

    public MatterResources(MatterService matterService) {
        this.matterService = matterService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<MatterDto> addMatter(@PathVariable UUID userId, @RequestBody MatterDto dto, UriComponentsBuilder componentsBuilder) {
        var addMatter = matterService.addMatter(userId, dto);

        URI uri = componentsBuilder.path("/{id}").buildAndExpand(addMatter.id()).toUri();

        return ResponseEntity.created(uri).body(addMatter);
    }
}
