package springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.domain.counselor.Counselor;
import springboot.domain.counselor.CounselorRepository;
import springboot.domain.posts.Posts;
import springboot.web.dto.Counselor.CounselorListResponseDto;
import springboot.web.dto.Counselor.CounselorResponseDto;
import springboot.web.dto.Counselor.CounselorSaveRequestDto;
import springboot.web.dto.Counselor.CounselorUpdateRequestDto;
import springboot.web.dto.Posts.PostsListResponseDto;
import springboot.web.dto.Posts.PostsResponseDto;
import springboot.web.dto.Posts.PostsUpdateRequestDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CounselorService {
    private final CounselorRepository counselorRepository;

    @Transactional
    public Long save(CounselorSaveRequestDto counselorSaveRequestDto){
        return counselorRepository.save(counselorSaveRequestDto.toEntity()).getId();
    }
    @org.springframework.transaction.annotation.Transactional
    public Long update(Long id, CounselorUpdateRequestDto requestDto) {
        Counselor counselor = counselorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        counselor.update(requestDto.getAge(),requestDto.getName(),requestDto.getJob());
        return id;
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public CounselorResponseDto findById(Long id) {
        Counselor entity = counselorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new CounselorResponseDto(entity);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<CounselorListResponseDto> findAllDesc() {
        return counselorRepository.findAllDesc().stream()
                .map(CounselorListResponseDto::new)
                .collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional
    public void delete (Long id) {
        Counselor counselor = counselorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        counselorRepository.delete(counselor);
    }
}
