package org.zerock.myapp.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.repository.RecipeRepository;
import org.zerock.myapp.repository.UsersRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Setter(onMethod_ = @Autowired)
    private RecipeRepository recipeRepo;

    @Setter(onMethod_ = @Autowired)
    private UsersRepository userRepo;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    } // getAllRecipes

    @Override
    public List<Recipe> getAllRecipesOrderedByRecipeNumberDesc() {
        return this.recipeRepo.findAll(Sort.by(Sort.Direction.DESC,"num"));
    } // getAllRecipesOrderedByRecipeNumberDesc


} // end class
