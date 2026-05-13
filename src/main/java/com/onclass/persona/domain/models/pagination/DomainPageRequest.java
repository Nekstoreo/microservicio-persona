package com.onclass.persona.domain.models.pagination;

public record DomainPageRequest(
        int page,
        int size,
        String sortBy,
        String sortDirection
) {
}
