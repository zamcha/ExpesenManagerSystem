package com.vti.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryFilterForm {

	private Integer minId;

	private Integer maxId;
}
