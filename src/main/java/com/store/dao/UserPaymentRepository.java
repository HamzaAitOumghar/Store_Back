package com.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entities.UserPayment;

public interface UserPaymentRepository  extends JpaRepository<UserPayment, Long>{

}
