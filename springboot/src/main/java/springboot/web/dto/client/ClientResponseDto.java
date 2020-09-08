package springboot.web.dto.client;

import lombok.Getter;
import springboot.domain.client.Client;

@Getter
public class ClientResponseDto {

    private Long id;
    private Long age;
    private String name;
    private String job;

    public ClientResponseDto(Client entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.name = entity.getName();
        this.job = entity.getJob();
    }
}
