package smartproject.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import smartproject.project.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
    @Modifying
    @Query("SELECT u FROM User u")
    List<User> getAllUsers();
    
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email, u.password = :password WHERE u.username = :username")
    int updateUserByName(String username, String email, String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.password = :password")
    int deleteByPassword(String password);
}
