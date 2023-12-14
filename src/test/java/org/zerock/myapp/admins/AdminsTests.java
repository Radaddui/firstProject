package org.zerock.myapp.admins;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.domain.Admins;
import org.zerock.myapp.domain.Role;
import org.zerock.myapp.repository.AdminsRepository;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class AdminsTests {

	@Setter(onMethod_ = @Autowired)
	private AdminsRepository adminsRepo;

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;

//	@Disabled
	@Tag("fast")
	@Order(1)
	@Test
	@DisplayName("1. CreateAdmins")
	@Timeout(3L)
	void createAdmins() {
		log.trace("createAdmins() invoked.");

		Admins admins = new Admins();
		admins.setId("admin11");

		String password = "admin1234";
		String encodedPassword = passwordEncoder.encode(password);
		admins.setPassword(encodedPassword);

		admins.setNickName("admin01");
		admins.setRole(Role.ROLE_ADMIN);
		// admin 이 입력받아야 생성될 요소
		// 1. id || 2. password || 3.

		adminsRepo.save(admins);
		log.info("Done.");

	} // createAdmins


	//	@Disabled
	@Tag("fast")
	@Order(2)
	@Test
	@DisplayName("1. CreateAdmins2")
	@Timeout(3L)
	void createAdmins2() {
		log.trace("createAdmins2() invoked.");

		Admins admins = new Admins();
		admins.setId("admin22");

		String password = "admin1234";
		String encodedPassword = passwordEncoder.encode(password);
		admins.setPassword(encodedPassword);

		admins.setNickName("admin02");

		// admin 이 입력받아야 생성될 요소
		// 1. id || 2. password || 3.

		adminsRepo.save(admins);
		log.info("Done.");

	} // createAdmins2

/*	//	@Disabled
	@Tag("fast")
	@Order(1)
	@Test
	@DisplayName("1. CreateAdmins2")
	@Timeout(3L)
	void createAdmins2() {
		log.trace("createAdmins2() invoked.");

		AdminsDTO admin = new AdminsDTO();
		admin.setId("admin22");

		String password = "admin1234";
		String encodedPassword = this.passwordEncoder.encode(password);

		admin.setPassword(encodedPassword);
		admin.setNickName("admin02");

		this.adminsRepo.save(admin); // repository 는 엔티티와 관계를 맺기때문에 일반적인 dto로는 받을 수 없다.
	} // createAdmins2*/

} // end class
