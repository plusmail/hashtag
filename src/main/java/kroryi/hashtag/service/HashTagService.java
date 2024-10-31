package kroryi.hashtag.service;

import jakarta.transaction.Transactional;
import kroryi.his.domain.HashTag;
import kroryi.his.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public List<HashTag> getAllHashTags() {
        return hashTagRepository.findAll();
    }

    @Transactional
    public HashTag createHashTag(String name) {
        HashTag hashTag = new HashTag();
        hashTag.setName(name);
        return hashTagRepository.save(hashTag);
    }

    @Transactional
    public HashTag updateHashTag(Long id, String name) {
        HashTag hashTag = hashTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HashTag not found"));
        hashTag.setName(name);
        return hashTagRepository.save(hashTag);
    }

    @Transactional
    public void deleteHashTag(Long id) {
        hashTagRepository.deleteById(id);
    }
}
