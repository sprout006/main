package springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.domain.gallery.GalleryEntity;
import springboot.domain.gallery.GalleryRepository;
import springboot.web.dto.Gallery.GalleryDto;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GalleryService {
    private GalleryRepository galleryRepository;
    private S3Service s3Service;

    public void savePost(GalleryDto galleryDto) {
        galleryRepository.save(galleryDto.toEntity());
    }
    public List<GalleryDto> getList() {
        List<GalleryEntity> galleryEntityList = galleryRepository.findAll();
        List<GalleryDto> galleryDtoList = new ArrayList<>();

        for (GalleryEntity galleryEntity : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(galleryEntity));
        }

        return galleryDtoList;
    }

    private GalleryDto convertEntityToDto(GalleryEntity galleryEntity) {
        return GalleryDto.builder()
                .id(galleryEntity.getId())
                .filePath(galleryEntity.getFilePath())
                .imgFullPath("https://" + s3Service.S3_DOMAIN_NAME + "/" + galleryEntity.getFilePath())
                .build();
    }
}