package codesquad.codestagram.reply.service;

import codesquad.codestagram.article.domain.Article;
import codesquad.codestagram.article.repository.ArticleRepository;
import codesquad.codestagram.reply.dto.ReplyRequestDto;
import codesquad.codestagram.reply.dto.ReplyResponseDto;
import codesquad.codestagram.reply.entity.Reply;
import codesquad.codestagram.reply.repository.ReplyRepository;
import codesquad.codestagram.user.domain.User;
import codesquad.codestagram.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ReplyService(ReplyRepository replyRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    //댓글 생성
    @Transactional
    public ReplyResponseDto createReply(ReplyRequestDto replyRequestDto, User user){
        Article article = articleRepository.findArticleByArticleId(replyRequestDto.articleId());

        Reply reply = new Reply(replyRequestDto.content(), article, user);
        Reply savedReply = replyRepository.save(reply);

        return new ReplyResponseDto(savedReply);
    }

    //댓글 리스트 가져오기
    public List<ReplyResponseDto> getRepliesByArticle(Long articleId){

        List<ReplyResponseDto> list = new ArrayList<>();

        for(Reply reply : replyRepository.findAll()){
            list.add(new ReplyResponseDto(reply));
        }

        return list;
    }
}
