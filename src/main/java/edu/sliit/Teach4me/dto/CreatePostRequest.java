package edu.sliit.Teach4me.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
public class CreatePostRequest  {
    private String userId;
    private String textStatus;
    private List<String> mediaFiles;
}
