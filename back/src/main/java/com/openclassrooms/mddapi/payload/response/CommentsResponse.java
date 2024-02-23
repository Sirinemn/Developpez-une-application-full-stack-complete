package com.openclassrooms.mddapi.payload.response;

import java.util.List;

import com.openclassrooms.mddapi.dto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentsResponse {
	List<CommentDto> comments;

}
