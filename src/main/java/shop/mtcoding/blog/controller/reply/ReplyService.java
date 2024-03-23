package shop.mtcoding.blog.controller.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.board.BoardJPARepository;
import shop.mtcoding.blog.controller.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyJPARepository replyJPARepo;
    private final BoardJPARepository boardJPARepo;

    @Transactional
    public void delete(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepo.findById(replyId).orElseThrow(() ->
                new Exception404("없는 댓글을 삭제할 수 없습니다."));

        if(sessionUserId != reply.getUser().getId()) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }

        replyJPARepo.deleteById(replyId);
    }

    @Transactional
    public void save(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepo.findById(reqDTO.getBoardId()).orElseThrow(() ->
                new Exception404("없는 게시글에 댓글을 작성할 수 없습니다."));

        Reply reply = reqDTO.toEntity(sessionUser, board);
        replyJPARepo.save(reply);
    }
}
