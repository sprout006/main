package springboot.web.dto.Counselor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CounselorUpdateRequestDto {
    private String name;
    private String job;
    private Long age;

    @Builder
    public CounselorUpdateRequestDto(Long age, String name, String job) {
        this.age = age;
        this.name = name;
        this.job = job;
    }
}