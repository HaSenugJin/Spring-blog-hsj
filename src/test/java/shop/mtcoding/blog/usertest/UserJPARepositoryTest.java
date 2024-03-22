package shop.mtcoding.blog.usertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.text.StrTokenizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.controller.user.User;
import shop.mtcoding.blog.controller.user.UserJPARepository;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepo;

    @Test
    public void save_test() {
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        userJPARepo.save(user);
    }

    @Test
    public void findById_test() {
        int id = 1;

        Optional<User> userOp = userJPARepo.findById(id);

        if (userOp.isPresent()) {
            User user = userOp.get();
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void findAll_test() {
        userJPARepo.findAll();
    }

    @Test
    public void findAllOrderBy_test() {
        userJPARepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<User> userPage = userJPARepo.findAll(pageable);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPage);
        System.out.println(json);

    }
    
    @Test
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";

        // when
        userJPARepo.findByUsernameAndPassword(username, password);
        
        // then
    }
}
