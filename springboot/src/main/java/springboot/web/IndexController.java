package springboot.web;

        import lombok.RequiredArgsConstructor;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.core.io.ByteArrayResource;
        import org.springframework.core.io.ClassPathResource;
        import org.springframework.core.io.Resource;
        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.util.FileCopyUtils;
        import org.springframework.util.ResourceUtils;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.view.RedirectView;
        import springboot.service.*;
        import springboot.web.dto.Counselor.CounselorResponseDto;
        //mport springboot.web.dto.Gallery.GalleryDto;
        import springboot.web.dto.Gallery.GalleryDto;
        import springboot.web.dto.Posts.PostsResponseDto;
        import springboot.web.dto.client.ClientResponseDto;
        import springboot.service.S3Service;
        import javax.servlet.http.HttpSession;
        import javax.xml.parsers.ParserConfigurationException;
        import java.io.*;
        import java.nio.charset.StandardCharsets;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.*;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final Service_Result service_result = new Service_Result();
    private final PostsService postsService;
    private final CounselorService counselorService;
    private final ClientService clientService;
    private final HttpSession httpSession;
    private final S3Service s3Service;
    private final GalleryService galleryService;
    private int count=0;
    private int flag=0;
    private String text="";
    private List<String> images= new ArrayList<String>();
    //private String[] images = new String[100];

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @GetMapping("/posts/index")
    public String posts_index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "posts-index";
    }
    @GetMapping("/posts/findByClient/{client}")
    public String posts_findByClient(@PathVariable String client, Model model) {
        model.addAttribute("posts", postsService.findByClient(client));
        return "posts-findByClient";
    }
    @GetMapping("/posts/findByCounselor/{counselor}")
    public String posts_findByCounselor(@PathVariable String counselor, Model model) {
        model.addAttribute("posts", postsService.findByCounselor(counselor));
        return "posts-findByCounselor";
    }
    @GetMapping("/")
    public String root(Model model)
    {
        count = galleryService.getList().size();
        return "main";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        model.addAttribute("counselor", counselorService.findAllDesc());
        model.addAttribute("client", clientService.findAllDesc());
        //images = Collections.emptyList();
        images.clear();
        flag=0;
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
    @GetMapping("/posts/view/{id}")
    public String postsView(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-view";
    }
    @GetMapping(value = "/result",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
   public String predict(@RequestParam("image") MultipartFile image, Model model, GalleryDto galleryDto,
    @RequestParam("result") MultipartFile result) throws IOException {
        count++;
        flag=1;
        String imgPath = s3Service.upload_img(image,count);
        galleryDto.setFilePath(imgPath);
        galleryService.savePost(galleryDto);
        String txtPath = s3Service.upload_txt(result,count);
        images.add(imgPath);
        return "result_img";
    }

    @GetMapping("/main/ss1")
    public String root2(Model model) {
        return "main2";
    }

    @ResponseBody
    @RequestMapping("/reload")
    public Map<String,String> reload(Model model) throws IOException {
        List<GalleryDto> galleryDtoList = galleryService.getList();
        int count = galleryDtoList.size();
        Map<String, String> map = new HashMap<String, String>();
        String path = "https://" + s3Service.S3_DOMAIN_NAME + "/image/"+"img"+count+".jpg";
        map.put("img", path);
        String t;
            if(flag==1)
            t="true";
        else
            t="false";
        map.put("start",t);

        text = s3Service.download(count);
        map.put("txt",text);
        //String fileName = "https://capstone-naeronambul.s3.ap-northeast-2.amazonaws.com/text/text0.txt";

        return map;
    }
    @ResponseBody
    @RequestMapping("/reload_images")
    public Map<String,List<String>> reload_images(Model model) throws IOException {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("images", images);

        return map;
    }
    @GetMapping("/view/images/last/{str}")
    public String view_images(@PathVariable String str ,Model model) throws ParseException {

        String[] s_arry= str.split(",");
        Set<String> set = new HashSet<>();
        for (String t : s_arry) {
            // Add each element into the set
            set.add(t);
        }
        /*String tmp = "{ \"name\":[ {";
        for(int i=0;i<s_arry.length;i++){
            tmp+="\"img\":\""+s_arry[i]+"\"";
            if(i != s_arry.length-1)
                tmp+=",";
        }
        tmp+="} ] }";*/
        //JSONParser parser = new JSONParser();
        //Object obj = parser.parse(tmp);
        //JSONObject jsonObj = (JSONObject) obj;
        model.addAttribute("images",set);

        return "posts-images";
    }
    @GetMapping("/counselor/index")
    public String counselor_index(Model model) {
        model.addAttribute("counselor", counselorService.findAllDesc());
        return "counselor-index";
    }
    @GetMapping("/counselor/save")
    public String counselorSave() {
        return "counselor-save";
    }

    @GetMapping("/counselor/update/{id}")
    public String counselorUpdate(@PathVariable Long id, Model model) {
        CounselorResponseDto dto = counselorService.findById(id);
        model.addAttribute("counselor", dto);

        return "counselor-update";
    }
    @GetMapping("/client/index")
    public String client_index(Model model) {
        model.addAttribute("client", clientService.findAllDesc());
        return "client-index";
    }
    @GetMapping("/client/save")
    public String clientSave() {
        return "client-save";
    }

    @GetMapping("/client/update/{id}")
    public String clientUpdate(@PathVariable Long id, Model model) {
        ClientResponseDto dto = clientService.findById(id);
        model.addAttribute("client", dto);

        return "client-update";
    }
}