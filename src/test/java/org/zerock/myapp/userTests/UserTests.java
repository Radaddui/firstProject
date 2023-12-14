package org.zerock.myapp.userTests;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.repository.UsersRepository;

import java.time.LocalDate;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class UserTests {

	@Setter(onMethod_ = @Autowired)
	private UsersRepository usersRepo;

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;

//	@Disabled
	@Tag("fast")
	@Order(1)
	@Test
	@DisplayName("contextLoads")
	@Timeout(3L)
	void contextLoads() {
		log.trace("contextLoads() invoked.");

		Users aaa = new Users();
		aaa.setUserId("aaa");
		aaa.setEmail("aaa@aaa");

		String rawPassword = "123";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		aaa.setPassword(encodedPassword);
		aaa.setNickName("aaa");
		LocalDate birthDate1 = LocalDate.of(1995, 11, 11);
		aaa.setBirthdate(birthDate1);
		this.usersRepo.save(aaa);

//		==============================================================================

		Users bbb = new Users();
		bbb.setUserId("bbb");
		bbb.setEmail("bbb@bbb");

		String brawPassword = "123";
		String bencodedPassword = passwordEncoder.encode(brawPassword);
		bbb.setPassword(bencodedPassword);
		bbb.setNickName("bbb");
		LocalDate birthDate2 = LocalDate.of(1995, 11, 11);
		bbb.setBirthdate(birthDate2);
		this.usersRepo.save(bbb);

		//		==============================================================================


		Users ccc = new Users();
		ccc.setUserId("ccc");
		ccc.setEmail("ccc@ccc");

		String crawPassword = "123";
		String cencodedPassword = passwordEncoder.encode(crawPassword);
		ccc.setPassword(cencodedPassword);
		ccc.setNickName("ccc");
		LocalDate birthDate3 = LocalDate.of(1995, 11, 11);
		ccc.setBirthdate(birthDate3);
		this.usersRepo.save(ccc);

	} // contextLoads

} // end class
