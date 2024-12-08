package When_To_Remember.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "matter_table")
public class Matter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private String difficult;
    private LocalDateTime nextReviewAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Matter() {
    }

    public Matter(UUID id, String title, String description, String difficult, LocalDateTime nextReviewAt, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficult = difficult;
        this.nextReviewAt = nextReviewAt;
        this.user = user;
    }

    public LocalDateTime nextRevisionTime(String difficult) {
        LocalDateTime actuallyHours = LocalDateTime.now();
        return switch (difficult) {
            case "VERY_HARD" -> actuallyHours.plusMinutes(10);
            case "HARD" -> actuallyHours.plusHours(1);
            case "MEDIUM" -> actuallyHours.plusDays(1);
            case "EASY" -> actuallyHours.plusDays(3);
            default -> throw new IllegalArgumentException("Invalid difficult level");
        };
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public LocalDateTime getNextReviewAt() {
        return nextReviewAt;
    }

    public void setNextReviewAt(LocalDateTime nextReviewAt) {
        this.nextReviewAt = nextReviewAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
