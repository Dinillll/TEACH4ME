package model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    private String id; // Mongo uses String ID

    private String userId;

    private String textStatus;

    private List<String> mediaUrls; // up to 3 images or 1 video

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

