package org.zerock.myapp.service;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Question;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@NoArgsConstructor

@Service
public class QuestionServiceImpl implements QuestionService{

    @Setter(onMethod_ = @Autowired)
    private QuestionRepository questionRepo;

    @Setter(onMethod_ = @Autowired)
    private UsersService usersService;


    @Override
    public void addQuestion(Question question) {
        Integer highestQuestionNumber = questionRepo.findHighestQuestionNumber();

        if(highestQuestionNumber == null) {
            highestQuestionNumber = 0;
        } // if

        // 다음 questionNumber 값을 설정
        question.setQuestionNumber(highestQuestionNumber + 1);

        //게시글 저장
        questionRepo.save(question);

    } // addQuestion

    @Override
    public void questionWrite(QuestionDTO questionDTO, String LoggedInUserId) {
        Users loggedInUserId = usersService.getUserById(LoggedInUserId);

        if(loggedInUserId == null) {
            ;;
            return;
        } // if

        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());

        //로그인 된 유저 id값을 불러옴
        question.setFkUsers(loggedInUserId);

        questionRepo.save(question);

    } // questionWrite

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();

    } // getAllQuestions

    @Override
    public Question getQuestionByNum(Long num) {

        Optional<Question> result = this.questionRepo.findById(num);

        return result.orElse(null);
    } // getQuestionByNum

    @Override
    public List<Question> getFindAllByOrderByCreateDateDesc() {
        return questionRepo.findAllByOrderByCreateDateDesc();

    } // getFindAllByOrderByCreateDateDesc

    @Override
    public Integer findHighestQuestionNumber() {
        return questionRepo.findHighestQuestionNumber();

    } // findHighestQuestionNumber

} // end class
