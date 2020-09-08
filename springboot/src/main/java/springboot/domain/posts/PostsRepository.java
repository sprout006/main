package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p FROM Posts p WHERE client=:client")
    List<Posts> findByClient(@Param("client") String client);

    @Query("SELECT p FROM Posts p WHERE author=:counselor")
    List<Posts> findByCounselor(@Param("counselor") String counselor);

    //@Modifying
    //@Query(value="UPDATE Posts u SET u.images = :images WHERE u.id = :id")
    //int update(@Param("images") List<String> images, @Param("id") Long id);
}