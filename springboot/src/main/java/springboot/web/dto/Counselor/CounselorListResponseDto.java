package springboot.web.dto.Counselor;

import lombok.Getter;
import springboot.domain.counselor.Counselor;
import springboot.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
public class CounselorListResponseDto {
    private Long id;
    private Long age;
    private String name;
    private String job;
    private LocalDateTime modifiedDate;

    public CounselorListResponseDto(Counselor entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.name = entity.getName();
        this.job = entity.getJob();
        this.modifiedDate = entity.getModifiedDate();
    }
}
