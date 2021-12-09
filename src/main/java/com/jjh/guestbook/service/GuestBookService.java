package com.jjh.guestbook.service;

import com.jjh.guestbook.dto.GuestBookDTO;
import com.jjh.guestbook.dto.PageRequestDTO;
import com.jjh.guestbook.dto.PageResultDTO;
import com.jjh.guestbook.entity.GuestBook;

public interface GuestBookService {

    Long guestBookInsert(GuestBookDTO dto);

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

    GuestBookDTO read(Long gno);



}
