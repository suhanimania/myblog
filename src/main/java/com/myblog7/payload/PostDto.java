package com.myblog7.payload;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Post title should be atleast 2 charachters")
    private String title;

    @NotEmpty
    @Size(min = 4,message = "Post description should be atleast 4 charachters")
    private String description;

    @NotEmpty
    @Size(min = 5,message = "Post content should be atleast 5 charachters")
    private String content;
}
