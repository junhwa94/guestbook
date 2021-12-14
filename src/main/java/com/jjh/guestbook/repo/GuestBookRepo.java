package com.jjh.guestbook.repo;

import com.jjh.guestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// QuerydslPredicateExecuto 인터페이스 추가상속
public interface GuestBookRepo extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {

}
