package shop.mtcoding.blog.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.controller.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepo;

    @Transactional
    public void save(BoardRequest.saveDTO reqDTO, User sessionUser) {
        boardJPARepo.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public void update(Integer boardId, Integer sessionUserId, BoardRequest.UpdateDTO reqDTO) {

        Board board = boardJPARepo.findById(boardId).orElseThrow(() ->
                new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        board.setTitle(board.getTitle());
        board.setContent(board.getContent());
    }

    public Board updateForm(Integer boardId, Integer sessionUserId) {

        Board board = boardJPARepo.findById(boardId).orElseThrow(() ->
                new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        return board;
    }

    @Transactional
    public void delete(Integer boardId, Integer sessionUserId) {

        Board board = boardJPARepo.findById(boardId).orElseThrow(() ->
                new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }

        boardJPARepo.deleteById(boardId);
    }

    public List<Board> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepo.findAll(sort);
    }

    public Board findByJoinUser(Integer boardId, User sessionUser) {
        Board board = boardJPARepo.findByIdJoinUser(boardId).orElseThrow(() ->
                new Exception404("게시글을 찾을 수 없습니다."));

        boolean isOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()) {
                isOwner = true;
            }
        }

        board.setIsOwner(isOwner);

        return board;
    }
}
