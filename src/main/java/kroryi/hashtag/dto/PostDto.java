package kroryi.hashtag.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Set<HashTagDto> hashtags;
    private LocalDateTime createdAt;
}
