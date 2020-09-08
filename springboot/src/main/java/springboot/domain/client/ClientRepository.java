package springboot.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.domain.counselor.Counselor;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT p FROM Client p ORDER BY p.id DESC")
    List<Client> findAllDesc();
}