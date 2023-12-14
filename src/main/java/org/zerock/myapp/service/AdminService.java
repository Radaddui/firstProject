package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Recipe;

import java.util.List;

public interface AdminService {
    public abstract List<Recipe> getAllRecipes();
    public abstract List<Recipe> getAllRecipesOrderedByRecipeNumberDesc();

} // end interface