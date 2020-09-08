package springboot.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class Service_Result {

    public void save_img(MultipartFile image,int count) throws IOException {
        String imgName = "img"+count+".jpg";
        //File upl = new File("/Users/jungwook/IdeaProjects/capstone_project/src/main/resources/images", imgName);
        //File upl = new File("./src/main/resources/static/images", imgName);
        File upl = new File("/Users/jungwook/Desktop/store/images/", imgName);
        upl.createNewFile();
        FileOutputStream fout = new FileOutputStream(upl);
        fout.write(image.getBytes());
        fout.close();
    }
    public void save_txt(MultipartFile text, int count) throws IOException {
        String textName = "text"+count+".txt";
        File upl = new File("./src/main/resources/static/text", textName);
        upl.createNewFile();
        FileOutputStream fout = new FileOutputStream(upl);
        fout.write(text.getBytes());
        fout.close();
    }
}
