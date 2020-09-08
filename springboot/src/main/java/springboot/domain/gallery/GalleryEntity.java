package springboot.domain.gallery;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "gallery")
public class GalleryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    private String imgFullPath;

    @Builder
    public GalleryEntity(Long id, String filePath,String imgFullPath) {
        this.id = id;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}
