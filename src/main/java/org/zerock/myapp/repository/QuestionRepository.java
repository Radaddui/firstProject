package org.zerock.myapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Question;
import org.zerock.myapp.domain.Users;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByFkUsers(Users users);
    List<Question> findAllByOrderByCreateDateDesc();

    @Query("SELECT MAX(q.num) FROM Question q")
    Integer findHighestQuestionNumber();

} // end interface
