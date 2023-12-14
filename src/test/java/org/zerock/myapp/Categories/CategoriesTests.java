package org.zerock.myapp.Categories;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.Categories;
import org.zerock.myapp.repository.CategoriesRepository;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class CategoriesTests {

	@Setter(onMethod_ = @Autowired)
	private CategoriesRepository categoriesRepo;

//	@Disabled
	@Tag("fast")
	@Order(1)
	@Test
	@DisplayName("contextLoads")
	@Timeout(3L)
	void contextLoads() {
		log.trace("contextLoads() invoked.");

		Categories koreanCategory = new Categories();
		koreanCategory.setName("KoreanFood");
		this.categoriesRepo.save(koreanCategory);

		Categories chineseCategory = new Categories();
		chineseCategory.setName("ChineseFood");
		this.categoriesRepo.save(chineseCategory);

		Categories japanCategory = new Categories();
		japanCategory.setName("JapanFood");
		this.categoriesRepo.save(japanCategory);

		Categories westernCategory = new Categories();
		westernCategory.setName("WesternFood");
		this.categoriesRepo.save(westernCategory);

	} // contextLoads

} // end class
