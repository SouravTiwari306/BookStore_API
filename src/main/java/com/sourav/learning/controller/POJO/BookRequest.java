package com.sourav.learning.controller.POJO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class BookRequest {

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{5,50}$")
	private String title;

	@NotNull
	@Positive
	private Long authorId;

	@NotNull
	@Positive
	private Long publisherId;

	private boolean deleted = false;

	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")
	private LocalDateTime createdDate;

	@Pattern(regexp = "^[a-zA-Z0-9_\\\\-\\\\s]+$")
	private String createdBy;

	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")
	private LocalDateTime updatedDate;

	@Pattern(regexp = "^[a-zA-Z0-9_\\\\-\\\\s]+$")
	private String updatedBy;

}
