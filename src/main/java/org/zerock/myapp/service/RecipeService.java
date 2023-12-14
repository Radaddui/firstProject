package org.zerock.myapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.domain.RecipeDTO;

import java.util.List;

public interface RecipeService {

    public abstract void recipeWrite(RecipeDTO recipeDTO, String LoggedInUserId);
    public abstract List<Recipe> getAllRecipes();
    public abstract List<Recipe> getAllRecipesOrderedByNumDesc();
    public abstract Page<Recipe> getPagedRecipes(Pageable pageable);
    public abstract Recipe getRecipeByNum(Long num);
    public abstract void updateViewCount(Long num);
    public abstract void updateRecipe(Recipe recipe);

//    키워드로 레시피 검색 메서드
    List<Recipe> searchRecipesByKeyword(String keyword);

//    제목으로 검색 메서드
    List<Recipe> searchRecipesByTitle(String title);

//    유저 아이디로 검색
    List<Recipe> searchRecipesByUserId(String userId);

// 카테고리별 페이징 처리
    Page<Recipe> getPagedRecipesByCategory(String category, Pageable pageable);

    void deleteRecipe(Long recipeNum);

} // end interface