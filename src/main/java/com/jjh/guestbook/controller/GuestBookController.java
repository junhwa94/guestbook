package com.jjh.guestbook.controller;

import com.jjh.guestbook.dto.PageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjh.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class GuestBookController {
@Autowired
  GuestBookService service;


    @GetMapping("/")
    public String index(){

        return "/guestbook/list";
    }

    @GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model){
    	System.out.println("1111111111111111111111");
	  model.addAttribute("result", service.getList(pageRequestDTO));


	  }
}