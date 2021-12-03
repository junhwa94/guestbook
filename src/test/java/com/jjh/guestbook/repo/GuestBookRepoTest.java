package com.jjh.guestbook.repo;

import com.jjh.guestbook.dto.GuestBookDTO;
import com.jjh.guestbook.entity.GuestBook;
import com.jjh.guestbook.service.GuestBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepoTest {

    @Autowired
    private GuestBookRepo guestBookRepo;

    @Autowired
    private GuestBookService guestBookService;

    /* 더미데이터 삽입 테스트(300개) */
//   @Test
    public void insertGuestBook(){
        IntStream.rangeClosed(1,300).forEach(i -> {

            GuestBook guestBook = GuestBook.builder()
                    .title("테스트 제목" + i)
                    .content("테스트 내용입니다." + i)
                    .writer("user" + i)
                    .build();
            guestBookRepo.save(guestBook);

        });

    }
    /* 300번 제목, 글 수정 테스트 */
//    @Test
    public void updateGuestBook(){
        Optional<GuestBook> result = guestBookRepo.findById(300L);

        if(result.isPresent()){

            GuestBook guestBook = result.get();

            guestBook.changeTitle("300번 제목 수정 완료");
            guestBook.changeContent("300번 내용 수정 테스트 완료했습니다.");

            guestBookRepo.save(guestBook);

        }

    }

    /* 301번 제목, 글 삽입 테스트 guestBookDTO를 guestBook entity로 변환 테스트*/
//    @Test
    public void insertDTO(){

        GuestBookDTO guestBookDTO = GuestBookDTO.builder()
                .title("DTO제목")
                .content("DTO내용")
                .writer("DTOuser")
                .build();

        guestBookService.guestBookInsert(guestBookDTO);


    }
}
