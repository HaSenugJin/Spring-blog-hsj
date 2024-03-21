package shop.mtcoding.blog.controller.board;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.blog.controller.util.MyDateUtil;

import java.sql.Timestamp;

@Data
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }
}
