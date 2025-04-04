package codesquad.codestagram.reply.dto;

import codesquad.codestagram.article.domain.Article;
import codesquad.codestagram.reply.entity.Reply;
import codesquad.codestagram.user.domain.User;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ReplyRequestDto(String content, Long articleId) {


}
