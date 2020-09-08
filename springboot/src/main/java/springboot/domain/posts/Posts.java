package springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    @Column(length = 500, nullable = false)
    private String author;
    @Column(length = 500, nullable = false)
    private String client;
    private String images;

    @Builder
    public Posts(String title, String content, String author, String client, String images){
        this.title = title;
        this.content = content;
        this.author = author;
        this.client = client;
        this.images = images;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
