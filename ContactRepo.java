package com.example.ecommerce.repo;

import com.example.ecommerce.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Message, Long> {

}
