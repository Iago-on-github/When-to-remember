package When_To_Remember.Service;

import When_To_Remember.Models.Dtos.MatterDto;
import When_To_Remember.Models.Matter;
import When_To_Remember.Models.User;
import When_To_Remember.Repository.MatterRepository;
import When_To_Remember.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MatterService {
    private final MatterRepository matterRepository;
    private final UserRepository userRepository;

    public MatterService(MatterRepository matterRepository, UserRepository userRepository) {
        this.matterRepository = matterRepository;
        this.userRepository = userRepository;
    }

    public MatterDto addMatter(UUID userId, MatterDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        Matter matter = new Matter();

        matter.setTitle(dto.title());
        matter.setDescription(dto.description());
        matter.setDifficult(dto.difficult());

        LocalDateTime nextReview = matter.nextRevisionTime(dto.difficult());
        matter.setNextReviewAt(nextReview);

        matter.setUser(user);

        matterRepository.save(matter);

        return new MatterDto(matter.getId(), matter.getTitle(), matter.getDescription(), matter.getDifficult());
    }
    public List<MatterDto> getPendingMatter() {
        List<Matter> pendingMatter = matterRepository.findPendingMatter(LocalDateTime.now());

        return pendingMatter.stream().map(pm -> new MatterDto(pm.getId(), pm.getTitle(), pm.getDescription(), pm.getDifficult())).toList();
    }

    public List<MatterDto> getPendingMatterByUser(UUID userId) {
        List<Matter> pendingMatter = matterRepository.findPendingMatterByUser(LocalDateTime.now(), userId);

        return pendingMatter.stream().map(pm -> new MatterDto(pm.getId(), pm.getTitle(), pm.getDescription(), pm.getDifficult())).toList();
    }
    // consultar assuntos pendentes
    // marcar como revisado
}
