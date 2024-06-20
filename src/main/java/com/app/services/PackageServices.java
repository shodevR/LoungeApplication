package com.app.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.PackageException;
import com.app.model.PackageDetails;

public interface PackageServices {
	public PackageDetails addPackageDetails (PackageDetails pd)throws PackageException;
	public PackageDetails updatePackageDetails(PackageDetails pd)throws PackageException;
	public PackageDetails removePackageDetails(Integer packageId)throws PackageException;
	public List<PackageDetails> viewAllPackageDetails()throws PackageException;
	public PackageDetails getPackageDetailsbyId(Integer id) throws PackageException;
	public PackageDetails updatePackageFlag(Integer packageId) throws PackageException;
	public List<PackageDetails> getPackagesByPartnerId(Integer partnerId) throws PackageException;
	
}
