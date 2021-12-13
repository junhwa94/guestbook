package com.jjh.guestbook.service;

import com.jjh.guestbook.dto.GuestBookDTO;
import com.jjh.guestbook.dto.PageRequestDTO;
import com.jjh.guestbook.dto.PageResultDTO;
import com.jjh.guestbook.entity.GuestBook;

public interface GuestBookService {

    // 방명록 등록
    Long guestBookInsert(GuestBookDTO dto);

    // 방명록 페이징
    PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    default GuestBook dtoToEntity(GuestBookDTO dto){

        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;

    }

    default GuestBookDTO entityToDto(GuestBook entity){

        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;

    }

    // 방명록 조회
    GuestBookDTO read(Long gno);

    // 방명록 삭제
    void remove(Long gno);

    // 방명록 수정
    void modify(GuestBookDTO dto);




}
