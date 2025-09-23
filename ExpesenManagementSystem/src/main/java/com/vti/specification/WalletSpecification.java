package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Wallet;
import com.vti.form.WalletFilterForm;

public class WalletSpecification {
	@SuppressWarnings({ "deprecation", "removal" })
	public static Specification<Wallet> buildWhere(String search, WalletFilterForm filterForm) {

		Specification<Wallet> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification<Wallet> name = new CustomSpecification<>("owner.fullName", search);
			where = Specification.where(name);
		}

		// if there is filter by min id
		if (filterForm != null && filterForm.getMinId() != null) {
			CustomSpecification<Wallet> minId = new CustomSpecification<>("minId", filterForm.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterForm != null && filterForm.getMaxId() != null) {
			CustomSpecification<Wallet> maxId = new CustomSpecification<>("maxId", filterForm.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		return where;
	}
}
