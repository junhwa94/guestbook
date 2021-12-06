package com.jjh.guestbook.service;


import com.jjh.guestbook.dto.GuestBookDTO;
import com.jjh.guestbook.dto.PageRequestDTO;
import com.jjh.guestbook.dto.PageResultDTO;
import com.jjh.guestbook.entity.GuestBook;
import com.jjh.guestbook.repo.GuestBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor // 의존성 자동 주입
@Service
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepo repo;



    @Override
    public Long guestBookInsert(GuestBookDTO dto){

        GuestBook entity = dtoToEntity(dto);

        repo.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = repo.findAll(pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));


        return new PageResultDTO<>(result, fn);
    }


}
