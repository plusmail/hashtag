package kroryi.hashtag.controller;

import kroryi.his.domain.Post;
import kroryi.his.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostWithHashtags(id));
    }

    @PutMapping("/{id}/hashtags")
    public ResponseEntity<Post> updatePostHashtags(
            @PathVariable Long id,
            @RequestBody Set<Long> hashtagIds) {
        return ResponseEntity.ok(postService.updatePostHashtags(id, hashtagIds));
    }

    @DeleteMapping("/{postId}/hashtags/{hashtagId}")
    public ResponseEntity<Post> removeHashtagFromPost(
            @PathVariable Long postId,
            @PathVariable Long hashtagId) {
        return ResponseEntity.ok(postService.removeHashtagFromPost(postId, hashtagId));
    }
}