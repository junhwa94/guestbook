package com.jjh.guestbook.controller;

import com.jjh.guestbook.dto.GuestBookDTO;
import com.jjh.guestbook.dto.PageRequestDTO;
import com.jjh.guestbook.repo.GuestBookRepo;
import com.jjh.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class GuestBookController {

    private final GuestBookService service; //final로 선언

    @Autowired
    GuestBookRepo gRepo;


    @GetMapping("/")
    public String index(){

        return "/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        model.addAttribute("result", service.getList(pageRequestDTO));

    }



    @GetMapping("/register")
    public void register(){
        System.out.println("글쓰기 페이지 진입");

    }

    @PostMapping("/register")
    public String registerForm(GuestBookDTO dto, RedirectAttributes redirectAttributes){

        Long gno = service.guestBookInsert(dto);

        // 단 한번만 데이터 전달하는 용도 addFlashAttribute, 브라우저에 전달되는 msg를 이용해서 화면상에서 모달창 보여주는 용도
        redirectAttributes.addFlashAttribute("msg", gno);

        // 포스트 방식 처리 후 목록페이지 이동
        return "redirect:/guestbook/list";

    }

    @PostMapping("/remove")
    public String remove(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO){

        service.remove(gno);


        return "redirect:/guestbook/list";
    }


    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        GuestBookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);

    }



    @PostMapping("/modify")
    public String modify(GuestBookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){

        // 수정해야하는 글 정보GuestBookDTO, 기존의 페이지 정보유지하기 PageRequestDTO

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";

    }

}