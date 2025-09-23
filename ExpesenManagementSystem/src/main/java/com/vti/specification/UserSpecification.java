package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.User;
import com.vti.form.UserFilterForm;


public class UserSpecification {
	@SuppressWarnings({ "deprecation", "removal" })
	public static Specification<User> buildWhere(String search, UserFilterForm filterForm) {

		Specification<User> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification<User> name = new CustomSpecification<>("email", search);
			where = Specification.where(name);
		}

		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification<User> minId = new CustomSpecification<>("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification<User> maxId = new CustomSpecification<>("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}
}

