package shop.mtcoding.blog.controller.reply;

import lombok.Data;
import shop.mtcoding.blog.controller.board.Board;
import shop.mtcoding.blog.controller.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public Reply toEntity(User sessionUser, Board board) {
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}
