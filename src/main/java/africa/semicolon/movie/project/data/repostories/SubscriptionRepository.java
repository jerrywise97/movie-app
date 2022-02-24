package africa.semicolon.movie.project.data.repostories;

import africa.semicolon.movie.project.data.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
