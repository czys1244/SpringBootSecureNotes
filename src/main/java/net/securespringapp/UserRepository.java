package net.securespringapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
	@Modifying
	public void updateFailedAttempts(int failAttempts, String email);
	
}
