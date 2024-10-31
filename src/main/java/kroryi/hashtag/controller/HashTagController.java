package kroryi.hashtag.controller;

import kroryi.his.domain.HashTag;
import kroryi.his.dto.HashTagDto;
import kroryi.his.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 개발 환경에서만 사용. 실제 운영 환경에서는 구체적인 도메인 지정 필요
public class HashTagController {

    private final HashTagService hashTagService;

    @GetMapping
    public ResponseEntity<List<HashTag>> getAllHashTags() {
        return ResponseEntity.ok(hashTagService.getAllHashTags());
    }

    @PostMapping
    public ResponseEntity<HashTag> createHashTag(@RequestBody HashTagDto dto) {
        return ResponseEntity.ok(hashTagService.createHashTag(dto.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashTag> updateHashTag(
            @PathVariable Long id,
            @RequestBody HashTagDto dto) {
        return ResponseEntity.ok(hashTagService.updateHashTag(id, dto.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHashTag(@PathVariable Long id) {
        hashTagService.deleteHashTag(id);
        return ResponseEntity.ok().build();
    }
}