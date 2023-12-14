package org.zerock.myapp.service;


import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Categories;
import org.zerock.myapp.repository.CategoriesRepository;

@Log4j2

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Setter(onMethod_ = @Autowired)
    private CategoriesRepository CategoriesRepo;

    @Override
    @Transactional
    public void createCategories() {

        Categories koreanCategory = new Categories();
        koreanCategory.setName("KoreanFood");
        this.CategoriesRepo.save(koreanCategory);

        Categories chineseCategory = new Categories();
        chineseCategory.setName("ChineseFood");
        this.CategoriesRepo.save(chineseCategory);

        Categories japanCategory = new Categories();
        japanCategory.setName("JapanFood");
        this.CategoriesRepo.save(japanCategory);

        Categories westernCategory = new Categories();
        westernCategory.setName("WesternFood");
        this.CategoriesRepo.save(westernCategory);


    } // createCategories

    @Override
    public Categories getCategoryByName(String name) {

        log.info("---------------name");
        log.info(name);
        log.info("---------------name");
        return CategoriesRepo.findByName(name);
    }
} // end class
