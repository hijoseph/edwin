package com.hyun.edwin.entity;

import com.hyun.edwin.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id") // 외래키 설정,  Article Entity의 기본키(id)와 매핑
    private Article article;       // 해당 댓글의 부모 게시글

    @Column
    private String  nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto commentDto, Article articleEntity){
        if(commentDto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패 ! 댓글의 아이디가 없어야 합니다.");
        if(commentDto.getArticleId() != articleEntity.getId())
            throw new IllegalArgumentException("댓글 생성 실패 ! 게시글의 아이디가 잘못되었습니다.");

        return new Comment(
                commentDto.getId(),
                articleEntity,
                commentDto.getNickname(),
                commentDto.getBody()
        );
    }

    public void patch(CommentDto commentDto){
        if(this.id != commentDto.getId())
            throw new IllegalArgumentException("댓글 수정 실패 ! 잘못된 아이다가 입력되었습니다.");

        if(commentDto.getNickname() != null)
            this.nickname = commentDto.getNickname();

        if(commentDto.getBody() != null)
            this.body = commentDto.getBody();
    }
}
