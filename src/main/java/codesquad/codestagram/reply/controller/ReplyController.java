package codesquad.codestagram.reply.controller;

import codesquad.codestagram.reply.dto.ReplyRequestDto;
import codesquad.codestagram.reply.dto.ReplyResponseDto;
import codesquad.codestagram.reply.service.ReplyService;
import codesquad.codestagram.user.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static codesquad.codestagram.util.SessionUtil.SESSION_USER_KEY;

@RestController
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/reply/{articleId}/create")
    public ResponseEntity<ReplyResponseDto> createReply(
            @PathVariable Long articleId,
            @RequestBody ReplyRequestDto replyRequestDto,
            HttpSession session){

         Optional<Object> loginUser =Optional.ofNullable(session.getAttribute(SESSION_USER_KEY));
        if(loginUser.isPresent()){
            User user = (User) loginUser.get();
            ReplyResponseDto replyResponseDto = replyService.createReply(replyRequestDto, user);
            return ResponseEntity.ok(replyResponseDto);
        }
        return ResponseEntity.status(401).build(); // 로그인 필요
    }
}
