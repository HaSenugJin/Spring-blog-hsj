package shop.mtcoding.blog.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    // 의존성 주입
    private final UserJPARepository userJAPRepo;

    @Transactional
    public void join(UserRequest.JoinDTO reqDTO) {
        Optional<User> user = userJAPRepo.findByUsername(reqDTO.getUsername());

        if (user.isPresent()) {
            throw new Exception400("중복된 유저네임입니다.");
        }

        userJAPRepo.save(reqDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO reqDTO) {
        User user = userJAPRepo.findByUsernameAndPassword(
                reqDTO.getUsername(),
                reqDTO.getPassword()).
                orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return user;
    }

    public User updateForm(Integer id) {
        return userJAPRepo.findById(id).
                orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
    }

    @Transactional
    public User update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User user = userJAPRepo.findById(id).orElseThrow
                (() -> new Exception404("회원정보를 찾을 수 없습니다."));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(user.getEmail());
        userJAPRepo.save(user);

        return user;
    }
}
