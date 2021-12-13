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

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor // 의존성 자동 주입
@Service
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepo repo;

    // 방명록 등록
    @Override
    public Long guestBookInsert(GuestBookDTO dto){

        GuestBook entity = dtoToEntity(dto);

        repo.save(entity);

        return entity.getGno();
    }

    // 방명록 페이징
    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = repo.findAll(pageable);

        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));


        return new PageResultDTO<>(result, fn);
    }

    // 방명록 조회
    @Override
    public GuestBookDTO read(Long gno) {

        Optional<GuestBook> result = repo.findById(gno);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    // 방명록 삭제
    @Override
    public void remove(Long gno) {

        repo.deleteById(gno);
    }

    // 방명록 수정
    @Override
    public void modify(GuestBookDTO dto) {
        // 업데이트 하는 항목은 제목과 내용
        Optional<GuestBook> result = repo.findById(dto.getGno());
        if(result.isPresent()) {

            GuestBook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repo.save(entity);
        }
    }
}
