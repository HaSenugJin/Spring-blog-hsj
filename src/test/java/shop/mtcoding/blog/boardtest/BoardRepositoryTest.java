package shop.mtcoding.blog.boardtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardRepository;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

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
}
