package com.sourav.learning.controller.POJO;

import com.sourav.learning.entity.Author;
import com.sourav.learning.entity.Publisher;

import lombok.Value;

@Value
public class BookResponse {

	private Long id;
	private String title;
	private Author author;
	private Publisher publisher;

}
