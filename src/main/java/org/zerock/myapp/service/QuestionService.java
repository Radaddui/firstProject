package org.zerock.myapp.service;


import org.zerock.myapp.domain.Question;
import org.zerock.myapp.domain.QuestionDTO;

import java.util.List;

public interface QuestionService {

    public abstract void addQuestion(Question question);

    public  abstract void questionWrite(QuestionDTO questionDTO, String LoggedInUserId);

    public abstract List<Question> getAllQuestions();

    public abstract Question getQuestionByNum(Long num);

    public abstract List<Question> getFindAllByOrderByCreateDateDesc();

    public abstract Integer findHighestQuestionNumber();

} // end interface
