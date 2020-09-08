package springboot.web.dto.Counselor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.domain.counselor.Counselor;

@Getter
@NoArgsConstructor
public class CounselorSaveRequestDto {
    private Long age;
    private String name;
    private String job;

    @Builder
    public CounselorSaveRequestDto(Long age, String name, String job){
        this.age = age;
        this.name = name;
        this.job = job;
    }
    public Counselor toEntity(){
        return Counselor.builder()
                .age(age)
                .name(name)
                .job(job)
                .build();
    }
}
