package com.myblog7.controller;

import com.myblog7.entity.Comment;
import com.myblog7.payload.CommentDto;
import com.myblog7.payload.CommentDto1;
import com.myblog7.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    @ResponseBody
    public ResponseEntity<?> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto saveComment=commentService.createComment(postId, commentDto);
        CommentDto1 com=new CommentDto1();
        com.setName(saveComment.getName());
        com.setEmail(saveComment.getEmail());
        com.setBody(saveComment.getBody());
        return new ResponseEntity<>(com, HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    @ResponseBody
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //http://localhost:8080/api/posts/2/comments/1
    @GetMapping("/posts/{postId}/comments/{commentId}")
    @ResponseBody
    public CommentDto getCommentsById(@PathVariable(value = "postId") Long postId,
                                          @PathVariable(value = "commentId")Long commentId){
        return commentService.getCommentsById(postId,commentId);
    }

    //http://localhost:8080/api/comments
    @GetMapping("/comments")
    @ResponseBody
    public List<CommentDto> getAllCommentsById(){
        return commentService.getAllCommentsById();
    }

    //http://localhost:8080/api/posts/2/comments/1
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                          @PathVariable(value = "commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);


    }
}
