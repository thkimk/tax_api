package com.hanwha.tax.apiserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
public class PageableDto<T> {

    private final List<T> list;

    private final PageInfo page;

    public PageableDto(List<T> list, Page<Object> pageable) {
        this.list = list;
        this.page = new PageableDto.PageInfo(
                pageable.getNumber() + 1,
                pageable.hasNext() ? pageable.nextPageable().getPageNumber() : null,
                pageable.getTotalPages(),
                pageable.getTotalElements());
    }

    @Getter
    @AllArgsConstructor
    public static class PageInfo {
        private int currentPage;
        @Nullable
        private Integer nextPage;
        private int totalPage;
        private long totalItems;
    }
}