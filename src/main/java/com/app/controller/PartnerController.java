package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.PartnerException;
import com.app.model.Client;
import com.app.model.Partners;
import com.app.services.PartnerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/partners")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;
	
	@PostMapping("/add")
	public ResponseEntity<Partners> addPartnerEntity(@RequestBody Partners partners) throws PartnerException {
		//TODO: process POST request
		Partners addPartners = partnerService.addPartner(partners);
		
		return new ResponseEntity<Partners>(addPartners, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Partners>>getAllPartnersEntity() throws PartnerException{
		List<Partners>allPartners = partnerService.viewAllPartners();
		return new ResponseEntity<List<Partners>>(allPartners,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/")
	public ResponseEntity<Partners>deletEntity(@RequestParam Integer packageId) throws PartnerException{
		Partners removePartners = partnerService.removePartner(packageId);
		return new ResponseEntity<Partners>(removePartners, HttpStatus.OK);
	}
	@GetMapping("/getDetails")
	public ResponseEntity<Partners> getPartnerDetailsEntity(@RequestParam Integer id)throws PartnerException{
		Partners partnerDetailsPartners = partnerService.getDetails(id);
		return new ResponseEntity<Partners>(partnerDetailsPartners, HttpStatus.OK);
	}

	
}
