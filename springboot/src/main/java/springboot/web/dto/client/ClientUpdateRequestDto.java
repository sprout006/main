package springboot.web.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientUpdateRequestDto {
    private String name;
    private String job;
    private Long age;

    @Builder
    public ClientUpdateRequestDto(Long age, String name, String job) {
        this.age = age;
        this.name = name;
        this.job = job;
    }
}