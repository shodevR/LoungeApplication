package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Voucher;

public interface VoucherRepo extends JpaRepository<Voucher, Integer>{
	

}
