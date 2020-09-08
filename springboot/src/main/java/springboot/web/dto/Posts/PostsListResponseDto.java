package springboot.web.dto.Posts;

import lombok.Getter;
import springboot.domain.posts.Posts;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String client;
    //private List<String> images;
    private String images;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.client = entity.getClient();
        this.modifiedDate = entity.getModifiedDate();
        this.images = entity.getImages();
    }
}

