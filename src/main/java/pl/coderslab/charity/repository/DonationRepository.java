package pl.coderslab.charity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.Donation;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAll();
    @Query(value = "select sum(quantity) from donation", nativeQuery = true)
    Integer sumOfQuantity();
    @Query(value = "select count(category_id) from donation_category", nativeQuery = true)
    Integer countOfCategory();
}
