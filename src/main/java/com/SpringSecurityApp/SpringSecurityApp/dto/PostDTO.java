package com.SpringSecurityApp.SpringSecurityApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    @NotNull(message = "title is Required here")
    @Size(min=3,max=100,message = "Number of characters in Name should be in the range of 3-100")
    private String title;
    private  String description;
}
