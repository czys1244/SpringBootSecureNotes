package net.securespringapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repo;

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("krzys@gmail.com");
		user.setPassword("password");
		user.setFirstName("krzys");
		user.setLastName("kowalski");

		User savedUser = repo.save(user);

		User existUser = entityManager.find(User.class, savedUser.getId());

		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	}

	@Test
	public void testFindByEmail() {
		User user = new User();
		user.setEmail("name@gmail.com");
		user.setPassword("password");
		user.setFirstName("jan");
		user.setLastName("kowalski");
		User savedUser = repo.save(user);

		String email = "name@gmail.com";
		User user2 = repo.findByEmail(email);

		assertThat(user2.getEmail()).isEqualTo(email);
	}
}
