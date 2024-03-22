package shop.mtcoding.blog.usertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.controller.user.User;
import shop.mtcoding.blog.controller.user.UserRepository;
import shop.mtcoding.blog.controller.user.UserRequest;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_test(){
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();

        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");
        User user = userRepository.findByUsernameAndPassword(reqDTO);
    }
}
