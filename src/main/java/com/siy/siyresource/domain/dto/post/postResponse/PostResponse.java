package com.siy.siyresource.domain.dto.post.postResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PostResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class PostData {
        private Long id;
        private String category;
        private String title;
        private String writer;
        private String createdAt;
    }

    private int size;
    private int currentPage;
    private int maxPage;
    private PostData data;
}
