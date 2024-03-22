package shop.mtcoding.blog.controller.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class saveDTO {
        private String title;
        private String content;
        private String username;

        public Board toEntity() {
            return new Board(title, content, username);
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
    }
}
