package com.vti.specification;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomSpecification<T> implements Specification<T> {

    // Các field sẽ dùng LIKE thay vì EQUAL
    private static final Set<String> LIKE_FIELDS = Set.of("name","email", "user.fullName", "owner.fullName");

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<?> path = resolvePath(root, field);

        // LIKE cho các field text
        if (isLikeField(field)) {
            return cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
        }

        // minId
        if (field.equalsIgnoreCase("minId")) {
            return cb.greaterThanOrEqualTo(root.get("id"), (Long)value);
        }

        // maxId
        if (field.equalsIgnoreCase("maxId")) {
            return cb.lessThanOrEqualTo(root.get("id"), (Long)value);
        }

        // Mặc định: so sánh bằng
        return null;
    }

    // Hỗ trợ nested field: "user.fullName" -> root.get("user").get("fullName")
    private Path<?> resolvePath(Root<T> root, String field) {
        String[] parts = field.split("\\.");
        Path<?> path = root;
        for (String part : parts) {
            path = path.get(part);
        }
        return path;
    }

    private boolean isLikeField(String field) {
        return LIKE_FIELDS.stream().anyMatch(f -> f.equalsIgnoreCase(field));
    }

}