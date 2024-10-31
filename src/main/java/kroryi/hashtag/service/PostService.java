package kroryi.hashtag.service;


import kroryi.his.domain.HashTag;
import kroryi.his.domain.Post;
import kroryi.his.repository.HashTagRepository;
import kroryi.his.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional(readOnly = true)
    public Post getPostWithHashtags(Long id) {
        return postRepository.findByIdWithHashtags(id);
    }

    @Transactional
    public Post updatePostHashtags(Long postId, Set<Long> hashtagIds) {
        Post post = postRepository.findByIdWithHashtags(postId);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        // 기존 해시태그 모두 제거
        post.getHashtags().clear();

        // 새로운 해시태그 추가
        hashtagIds.forEach(hashtagId -> {
            HashTag hashTag = hashTagRepository.findById(hashtagId)
                    .orElseThrow(() -> new RuntimeException("HashTag not found"));
            post.addHashTag(hashTag);
        });

        return postRepository.save(post);
    }

    @Transactional
    public Post removeHashtagFromPost(Long postId, Long hashtagId) {
        Post post = postRepository.findByIdWithHashtags(postId);
        HashTag hashTag = hashTagRepository.findById(hashtagId)
                .orElseThrow(() -> new RuntimeException("HashTag not found"));

        post.removeHashTag(hashTag);
        return postRepository.save(post);
    }
}
