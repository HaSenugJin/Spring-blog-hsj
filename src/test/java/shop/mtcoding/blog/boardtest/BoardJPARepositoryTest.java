package shop.mtcoding.blog.boardtest;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardJPARepository;
import shop.mtcoding.blog.controller.user.User;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepo;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        User user = User.builder().id(1).build();
        Board board = Board.builder()
                .title("타이틀")
                .content("내용")
                .user(user)
                .build();

        // when
        boardJPARepo.save(board);

        // then
        System.out.println(board.getId());
    }

    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<Board> board = boardJPARepo.findById(id);

        if (board.isPresent()) {
            Board board1 = board.get();
            System.out.println(board1.getTitle());
        }

        // then
    }

    @Test
    public void findByIdJoinUser_test(){
        // given
        Integer id = 1;

        // when
        boardJPARepo.findByIdJoinUser(id);

        // then
    }

    @Test
    public void findAll_orderBy_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // when
        List<Board> boardList = boardJPARepo.findAll(sort);

        // then
        System.out.println(boardList);
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardJPARepo.deleteById(id);
        em.flush();

        // then
    }
}
