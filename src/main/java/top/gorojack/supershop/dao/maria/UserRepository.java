package top.gorojack.supershop.dao.maria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByUsernameAndPassword(String username, String password);

    public User getUserByUsername(String username);
}
