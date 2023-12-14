package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Categories;


@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Categories findByName(String name);

} // end interface
