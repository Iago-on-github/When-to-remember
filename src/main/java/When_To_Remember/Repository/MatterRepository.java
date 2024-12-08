package When_To_Remember.Repository;

import When_To_Remember.Models.Matter;
import When_To_Remember.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MatterRepository extends JpaRepository<Matter, UUID> {
    @Query("SELECT m FROM Matter m WHERE m.nextReviewAt <= :currentDate")
    List<Matter> findPendingMatter(@Param("currentDate") LocalDateTime currentDate);

    @Query(value = "SELECT * FROM matter_table WHERE next_review_at <= :currentDate AND user_id = :userId", nativeQuery = true)
    List<Matter> findPendingMatterByUser(@Param("currentDate") LocalDateTime currentDate, @Param("userId") UUID userId);

}
