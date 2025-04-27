package edu.sliit.Teach4me.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatePostRequest  {
    private String userId;
    private String textStatus;
    private List<String> mediaFiles;
}
