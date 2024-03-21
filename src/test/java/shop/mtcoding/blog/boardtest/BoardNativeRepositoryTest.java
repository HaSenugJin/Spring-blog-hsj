package shop.mtcoding.blog.boardtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardNativeRepository;

import java.util.List;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findAll_test() {

        List<Board> boardList = boardNativeRepository.findAll();

        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }


    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardNativeRepository.findById(id);

        System.out.println("findById_test/getUsername : " + board.getUsername());

    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardNativeRepository.deleteById(id);

        List<Board> boardList = boardNativeRepository.findAll();
        Assertions.assertThat(boardList.size()).isEqualTo(3);
    }
}
