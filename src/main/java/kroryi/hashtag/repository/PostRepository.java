package kroryi.hashtag.repository;

import kroryi.his.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.hashtags WHERE p.id = :id")
    Post findByIdWithHashtags(@Param("id") Long id);
}
