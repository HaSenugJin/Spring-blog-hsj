package shop.mtcoding.blog.controller.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.controller.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final HttpSession session;
    private final ReplyService replyService;

    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable Integer id, @RequestParam("boardId") Integer boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.delete(id, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.save(reqDTO, sessionUser);

        return "redirect:/board/" + reqDTO.getBoardId();
    }
}
