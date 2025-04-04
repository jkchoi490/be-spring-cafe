package codesquad.codestagram.reply.dto;

import codesquad.codestagram.reply.entity.Reply;
import codesquad.codestagram.user.domain.User;

public record ReplyResponseDto(
        Long replyId,
        String content,

        User writer,
        Long articleId,
        String createdDate,
        String modifiedDate

) {
    //엔티티 -> DTO 변환을 위한 생성자
    public ReplyResponseDto(Reply reply){
        this(reply.getReplyId(), reply.getContent(),
                reply.getWriter(), reply.getArticle().getArticleId(), reply.getCreatedDate(), reply.getModifiedDate());
    }


}
