package com.bitwise.hibernatelazyinitializationexception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostCommentDTO {

    private Long id;

    private String review;

    private String title;
}
