package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Categories;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.domain.RecipeDTO;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.repository.CommentsRepository;
import org.zerock.myapp.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@NoArgsConstructor

@Service
public class RecipeServiceImpl implements RecipeService{

    @Setter(onMethod_ = @Autowired)
    private RecipeRepository recipeRepo;

    @Setter(onMethod_ = @Autowired)
    private UsersService usersService;

    @Setter(onMethod_ = @Autowired)
    private CategoriesService categoriesService;

    @Setter(onMethod_ = @Autowired)
    private CommentsRepository commentsRepo;




    @Override
    public void recipeWrite(RecipeDTO recipeDTO, String LoggedInUserId) {
        Users loggedInUserId =  usersService.getUserById(LoggedInUserId);

        if(loggedInUserId == null){
            ;;
            return;
        } // inner-if

        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setContent(recipeDTO.getContent());

        // 로그인 된 유저 ID값을 불러온다.
        recipe.setFkUsers(loggedInUserId);

        // 카테고리 처리
        log.info("------------recipeDTO");
        log.info(recipeDTO);
        String selectedCategoryName = recipeDTO.getCategories();
        log.info("------------selectedCategoryName");
        log.info(selectedCategoryName);
        Categories selectedCategories = categoriesService.getCategoryByName(selectedCategoryName);
        log.info("------------categoriesService");
        log.info(categoriesService);

        log.info("------------selectedCategories");
        log.info(selectedCategories);

        if(selectedCategories != null){
            recipe.setFkCategories(selectedCategories);

            // processedDate를 현재 시간으로 설정
            recipe.setProcessedDate(new Date());
            recipeRepo.save(recipe);
            log.info("--------------------------------");
            log.info(recipe);
            log.info("--------------------------------");
        } // end class




    } // recipeWrite

    @Override
    public List<Recipe> getAllRecipes() {
        return  recipeRepo.findAll();
    } // getAllRecipes

    @Override
    public List<Recipe> getAllRecipesOrderedByNumDesc() {
        return recipeRepo.findAllByOrderByNumDesc();
    } // getAllRecipesOrderedByNumDesc

    @Override
    public Page<Recipe> getPagedRecipes(Pageable pageable) {
        return recipeRepo.findAll(pageable);
    } // getPagedRecipes

    @Override
    public Recipe getRecipeByNum(Long num) {
        return recipeRepo.findByNum(num);
    } // getRecipeByNum

    @Transactional
    @Override
    public void updateViewCount(Long num) {
        recipeRepo.updateViewCount(num);
    } // updateViewCount


    @Override
    public void updateRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
    } // updateRecipe


    @Override
    public List<Recipe> searchRecipesByKeyword(String keyword) {
        return recipeRepo.findByTitleContainingIgnoreCase(keyword);

    } // searchRecipesByKeyword

    @Override
    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeRepo.findByTitleContainingIgnoreCase(title);
    } // searchRecipesByTitle

    @Override
    public List<Recipe> searchRecipesByUserId(String userId) {
        return recipeRepo.findByFkUsers_UserId(userId);
    } // searchRecipesByUserId

    @Override
    public Page<Recipe> getPagedRecipesByCategory(String category, Pageable pageable) {
        return recipeRepo.findByFkCategories_Name(category, pageable);
    }

    @Override
    public void deleteRecipe(Long recipeNum) {
        recipeRepo.deleteByNum(recipeNum);
    }

} // end class
