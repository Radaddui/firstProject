package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Comments;
import org.zerock.myapp.domain.Recipe;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByFkRecipe(Recipe recipe);

    Optional<Comments> findById(Long commentId);

    @Query("SELECT c FROM Comments c JOIN FETCH c.fkUsers WHERE c.fkRecipe = :recipe")
    List<Comments> findCommentsWithUsersByRecipe(@Param("recipe") Recipe recipe);
    @Modifying
    @Query("UPDATE Comments c SET c.comments = :comment, c.reportProcessed = current_timestamp WHERE c.num = :commentId")
    void updateComment(@Param("commentId") Long commentId, @Param("comment") String comment);

    @Modifying
    @Query("DELETE FROM Comments c WHERE c.num = :commentId")
    void deleteCommentById(@Param("commentId") Long commentId);

    // 내용에서 키워드를 포함하는 댓글 검색 메서드
    List<Comments> findByContentContainingIgnoreCase(String comments);

} // end interface
