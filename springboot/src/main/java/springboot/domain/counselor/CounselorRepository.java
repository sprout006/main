package springboot.domain.counselor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    @Query("SELECT p FROM Counselor p ORDER BY p.id DESC")
    List<Counselor> findAllDesc();
}