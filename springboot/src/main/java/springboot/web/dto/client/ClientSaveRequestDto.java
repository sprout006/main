package springboot.web.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.domain.client.Client;

@Getter
@NoArgsConstructor
public class ClientSaveRequestDto {
    private Long age;
    private String name;
    private String job;

    @Builder
    public ClientSaveRequestDto(Long age, String name, String job){
        this.age = age;
        this.name = name;
        this.job = job;
    }
    public Client toEntity(){
        return Client.builder()
                .age(age)
                .name(name)
                .job(job)
                .build();
    }
}
