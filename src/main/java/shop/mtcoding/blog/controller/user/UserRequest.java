package shop.mtcoding.blog.controller.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
