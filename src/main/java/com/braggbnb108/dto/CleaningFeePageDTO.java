package com.braggbnb108.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CleaningFeePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<CleaningFeeDTO> cleaningFees;
}





