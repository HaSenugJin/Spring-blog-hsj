package shop.mtcoding.blog.controller.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findById(Integer id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());

        return (User) query.getSingleResult();
    }

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public User UpdateById(Integer id, UserRequest.UpdateDTO reqDTO) {
        User user = findById(id);
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }

}
