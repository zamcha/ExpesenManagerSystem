package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Category;
import com.vti.form.CategoryFilterForm;

public class CategorySpecification {
	@SuppressWarnings({ "deprecation", "removal" })
	public static Specification<Category> buildWhere(String search, CategoryFilterForm filterForm) {

		Specification<Category> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification<Category> name = new CustomSpecification<>("name", search);
			where = Specification.where(name);
		}

		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification<Category> minId = new CustomSpecification<>("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification<Category> maxId = new CustomSpecification<>("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}

}
