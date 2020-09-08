package springboot.web.dto.client;

import lombok.Getter;
import springboot.domain.client.Client;

import java.time.LocalDateTime;

@Getter
public class ClientListResponseDto {
    private Long id;
    private Long age;
    private String name;
    private String job;
    private LocalDateTime modifiedDate;

    public ClientListResponseDto(Client entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.name = entity.getName();
        this.job = entity.getJob();
        this.modifiedDate = entity.getModifiedDate();
    }
}
