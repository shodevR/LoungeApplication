package com.app.repository;

import com.app.model.StoreVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreVoucherRepo extends JpaRepository<StoreVoucher, Integer> {
    StoreVoucher findByUniqueCode(String uniqueCode);
    List<StoreVoucher> findByVoucheridAndFlagTrue(Integer voucherid);
    List<StoreVoucher> findByVoucherid(Integer voucherid);
}
