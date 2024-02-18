package com.openclassrooms.mddapi.payload.response;

import java.util.List;

import com.openclassrooms.mddapi.models.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TopicsResponse {

	List<Topic> topics;


}
