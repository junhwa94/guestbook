package com.jjh.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@Builder
public class PageRequestDTO {

    private int page;
    private int size;

    public PageRequestDTO(){

        this.page = 1;
        this.size =10;

    }

    public Pageable getPageable(Sort sort){

        // 페이지 번호가 0부터 시작한다는 점을 감안해서 1페이지의 경우 0이 될 수 있도록 page-1
        return PageRequest.of(page -1, size, sort);

    }



}
