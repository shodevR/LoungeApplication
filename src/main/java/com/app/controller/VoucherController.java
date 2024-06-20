package com.app.controller;

import java.io.IOException;
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

import com.app.exceptions.PackageException;
import com.app.exceptions.VoucherException;
import com.app.model.PackageDetails;
import com.app.model.Voucher;

import com.app.services.VoucherService;
import com.google.zxing.WriterException;
@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

	@Autowired
	private VoucherService voucherService;
	
	@PostMapping("/addvoucher")
	public ResponseEntity<Voucher> addVoucherEntity(@RequestBody Voucher voucher )throws VoucherException, WriterException, IOException{
		voucher.setClaimed(0);
		Voucher addVoucher = voucherService.addVoucher(voucher);
		
		
		return new ResponseEntity<Voucher>(addVoucher,HttpStatus.CREATED);
		
	}

	
	@GetMapping("/getAll")
	public ResponseEntity<List<Voucher>>getAllvoucher()throws VoucherException {
	List<Voucher>vouchers = voucherService.viewAllVouchers();
	return new ResponseEntity<List<Voucher>>(vouchers,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Voucher> voucherUpdatEntity(@RequestBody Voucher voucher)throws VoucherException {
		//TODO: process PUT request
		Voucher existingVoucher = voucherService.updateVoucher(voucher);
		return new ResponseEntity<Voucher>(existingVoucher,HttpStatus.OK);
		
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Voucher>deletevoucher(@RequestParam Integer voucherID) throws VoucherException{
		Voucher deleteVoucher = voucherService.removeVoucher(voucherID);
		return new ResponseEntity<Voucher>(deleteVoucher, HttpStatus.OK);
	}
	@GetMapping("voucherdetails/{id}")
	public ResponseEntity<Voucher>getVoucherDetails(@PathVariable("id") Integer id) throws VoucherException{
		Voucher voucher = voucherService.getVoucher(id);
		return new ResponseEntity<Voucher>(voucher,HttpStatus.OK);
	}
}
