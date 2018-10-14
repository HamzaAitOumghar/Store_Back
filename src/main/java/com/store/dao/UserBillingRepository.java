package com.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entities.UserBilling;

public interface UserBillingRepository extends JpaRepository<UserBilling,Long> {

}
