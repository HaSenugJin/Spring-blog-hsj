package shop.mtcoding.blog.boardtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardNativeRepository;
import shop.mtcoding.blog.controller.board.BoardPersistRepository;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test() {
        Board board = new Board("제목5", "내용5", "ssar");

        boardPersistRepository.save(board);
        System.out.println(board);
    }
}
