package shop.mtcoding.blog.boardtest;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardRepository;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardRepository.findById(id);
        System.out.println(board.getUser().getUsername());
    }

    @Test
    public void findByIdJoinUser_test(){
        // given
        int id = 1;

        // when
        Board board = boardRepository.findByIdJoinUser(id);
        System.out.println(board.getUser().getUsername());

        // then
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        // then
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id);

        // then
        System.out.println(boardRepository.findAll().size());
    }

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String title = "새제목";
        String content = "새내용";

        // when
        boardRepository.updateById(id, title, content);
        em.flush();

        // then
        System.out.println(boardRepository.findById(id));
    }
}
