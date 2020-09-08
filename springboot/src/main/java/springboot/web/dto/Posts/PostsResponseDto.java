package springboot.web.dto.Posts;

import lombok.Getter;
import springboot.domain.posts.Posts;

import java.util.List;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String client;
   // private List<String> images;
    private String images;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.client = entity.getClient();
        this.images = entity.getImages();
    }
}
