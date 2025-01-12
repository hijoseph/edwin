package com.hyun.edwin.controller;

import com.hyun.edwin.dto.CommentDto;
import com.hyun.edwin.entity.Article;
import com.hyun.edwin.service.ArticleService;
import com.hyun.edwin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        List<CommentDto> commentAllDto = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(commentAllDto);
    }


    // 2. 댓글 생성
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){
        /* 1. service */
        // Select article by articleId
        Article articleEntity = articleService.selectArticle(articleId);

        // Create Comment in this article
        CommentDto createDto = commentService.create(articleEntity, dto);

        /* 2. response return */
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 3. 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable Long commentId, @RequestBody CommentDto dto){
        // 1. 서비스에 위임
        CommentDto updateDto = commentService.update(commentId, dto);

        // 2. 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 4. 댓글 삭제
    @DeleteMapping("/comments/{deleteId}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long deleteId){
        // 1. 서비스에 위임
        CommentDto deleteDto = commentService.delete(deleteId);

        // 2. 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}
