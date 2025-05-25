package com.tfg.tfg;

import com.tfg.tfg.repository.SeedRepository;
import com.tfg.tfg.repository.UserRepository;
import com.tfg.tfg.services.SeedService;
import com.tfg.tfg.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
class TfgApplicationTests {

	@MockitoBean
	private UserRepository users;

	@MockitoBean
	private SeedRepository seeds;   // ← ¡añádelo!

	@Autowired
	private UserService userService;

	@Autowired
	private SeedService seedService;

	@Test
	void contextLoads() {
		// ya tienes mocks para users y para seeds,
		// aquí puedes hacer when(...).thenReturn(...) sin problema.
	}
}
