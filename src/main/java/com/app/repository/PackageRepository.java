package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.PackageDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository extends JpaRepository<PackageDetails, Integer>{
    @Query("SELECT p FROM PackageDetails p WHERE p.packageId IN (SELECT pa.packageDetails.packageId FROM Partners pa WHERE pa.partnerId = :partnerId)")
    List<PackageDetails> findByPartnerId(@Param("partnerId") Integer partnerId);
}
