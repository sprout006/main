package springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.service.CounselorService;
import springboot.web.dto.Counselor.CounselorListResponseDto;
import springboot.web.dto.Counselor.CounselorResponseDto;
import springboot.web.dto.Counselor.CounselorSaveRequestDto;
import springboot.web.dto.Counselor.CounselorUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CounselorApiController {
    private final CounselorService counselorService;

    @PostMapping("api/v1/counselor")
    public Long save(@RequestBody CounselorSaveRequestDto counselorSaveRequestDto){
        return counselorService.save(counselorSaveRequestDto);
    }
    @PutMapping("/api/v1/counselor/{id}")
    public Long update(@PathVariable Long id, @RequestBody CounselorUpdateRequestDto requestDto) {
        return counselorService.update(id, requestDto);
    }

    @GetMapping("/api/v1/counselor/{id}")
    public CounselorResponseDto findById(@PathVariable Long id) {
        return counselorService.findById(id);
    }

    @GetMapping("/api/v1/counselor/list")
    public List<CounselorListResponseDto> findAll() {
        return counselorService.findAllDesc();
    }

    @DeleteMapping("/api/v1/counselor/{id}")
    public Long delete(@PathVariable Long id) {
        counselorService.delete(id);
        return id;
    }
}
