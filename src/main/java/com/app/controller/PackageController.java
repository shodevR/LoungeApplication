package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;
import com.app.exceptions.PackageException;
import com.app.model.PackageDetails;
import com.app.services.PackageServices;


@RestController
@RequestMapping("/api/packages")
public class PackageController {
	
	@Autowired
	private PackageServices packageServices;

	
	    @PostMapping("/addPackage")
	    public ResponseEntity<PackageDetails> addPackages(@RequestBody PackageDetails packageDetails) throws PackageException {

			
	        PackageDetails addedPackage = packageServices.addPackageDetails(packageDetails);

	        return new ResponseEntity<>(addedPackage, HttpStatus.OK);
	    }
	@PutMapping("/updatePackage")
	public ResponseEntity<PackageDetails>updatePackages(@RequestBody PackageDetails packageDetails)throws PackageException{
		PackageDetails updatePackageDetails = packageServices.updatePackageDetails(packageDetails);
		return new ResponseEntity<PackageDetails>(updatePackageDetails,HttpStatus.ACCEPTED);
	}
	@PutMapping("/updateFlag")
	public ResponseEntity<PackageDetails> UpdateFlagEntity(@RequestParam Integer packageId)throws PackageException{

		PackageDetails packageDetails = packageServices.updatePackageFlag(packageId);
		return new ResponseEntity<PackageDetails>(packageDetails, HttpStatus.OK);
	}
	
	@DeleteMapping("/removePackage")
	public ResponseEntity<PackageDetails>deletEntity(@RequestParam Integer packageId)throws PackageException{
		PackageDetails delePackageDetails = packageServices.removePackageDetails(packageId);
		return new ResponseEntity<PackageDetails>(delePackageDetails,HttpStatus.OK);
	}
	@GetMapping("/getAllPackages")
	public ResponseEntity<List<PackageDetails>>getAllPackages() throws PackageException{
		List<PackageDetails>allPackagesDetails = packageServices.viewAllPackageDetails();
		return new ResponseEntity<List<PackageDetails>>(allPackagesDetails,HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}")
	public ResponseEntity<PackageDetails>getPackageDetails(@PathVariable("id") Integer id) throws PackageException{
		PackageDetails packageDetails = packageServices.getPackageDetailsbyId(id);
		return new ResponseEntity<PackageDetails>(packageDetails,HttpStatus.OK);
	}
	@GetMapping("/byPartner/{partnerId}")
	public ResponseEntity<List<PackageDetails>> getPackagesByPartnerId(@PathVariable Integer partnerId) throws PackageException {
		List<PackageDetails> packages = packageServices.getPackagesByPartnerId(partnerId);
		return ResponseEntity.ok(packages);
	}
}
