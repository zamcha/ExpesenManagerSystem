package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Transaction;
import com.vti.form.TransactionFilterForm;


public class TransactionSpecification {
	@SuppressWarnings({ "deprecation", "removal" })
	public static Specification<Transaction> buildWhere(String search, TransactionFilterForm filterForm) {

		Specification<Transaction> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification<Transaction> name = new CustomSpecification<>("user.fullName", search);
			where = Specification.where(name);
		}

		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification<Transaction> minId = new CustomSpecification<>("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification<Transaction> maxId = new CustomSpecification<>("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}
}
