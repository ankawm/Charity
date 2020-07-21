package pl.coderslab.charity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.Donation;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    Donation findById(long id);
    List<Donation> findAll();
}
