package springboot.web.dto.Counselor;

import lombok.Getter;
import springboot.domain.counselor.Counselor;

@Getter
public class CounselorResponseDto {

    private Long id;
    private Long age;
    private String name;
    private String job;

    public CounselorResponseDto(Counselor entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.name = entity.getName();
        this.job = entity.getJob();
    }
}
