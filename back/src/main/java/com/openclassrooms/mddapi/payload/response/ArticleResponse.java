package com.openclassrooms.mddapi.payload.response;

import java.util.Set;

import com.openclassrooms.mddapi.dto.ArticleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
	Set<ArticleDto> articles;

}
