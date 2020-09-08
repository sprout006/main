package springboot.web.dto.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.domain.posts.Posts;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private String client;
   //private List<String> images;
    private String images;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String client, String images){
        this.title = title;
        this.content = content;
        this.author = author;
        this.client = client;
        this.images = images;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .client(client)
                .images(images)
                .build();
    }
}
