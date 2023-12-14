package org.zerock.myapp.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Comments;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.repository.CommentsRepository;

import java.util.List;

@Log4j2

@Service
public class CommentsServiceImpl implements CommentsService{

    @Setter(onMethod_ = @Autowired)
    private CommentsRepository commentsRepo;


    @Override
    public List<Comments> getCommentsByRecipe(Recipe recipe) {
        return commentsRepo.findByFkRecipe(recipe);
    }  // getCommentsByRecipe


    @Override
    public void addComment(Comments comment) {
        commentsRepo.save(comment);
    } // addComment

    @Override
    public List<Comments> getCommentsWithUsersByRecipe(Recipe recipe) {
        return commentsRepo.findCommentsWithUsersByRecipe(recipe);
    } // getCommentsWithUsersByRecipe

    @Transactional
    @Override
    public void updateComment(Long commentId, String commentContent) {
        commentsRepo.updateComment(commentId, commentContent);
    } // updateCommnet


    @Override
    public Comments getCommentById(Long commentId) {
        return commentsRepo.findById(commentId).orElse(null);
    } // getCommentById

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        log.trace("deleteComment({}) invoked.", commentId);

        commentsRepo.deleteCommentById(commentId);
    } // deleteComment

    @Override
    public List<Comments> searchCommentsByKeyword(String keyword) {

        return commentsRepo.findByContentContainingIgnoreCase(keyword);

    } // searchCommentsByKeyword


} // CommentsServiceImpl
