package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.VoucherPlanException;
import com.app.model.VoucherPlan;
import com.app.services.VoucherPlanService;

@RestController
@RequestMapping("/voucherplan")
public class VoucherPlanController {
	
	@Autowired
	private VoucherPlanService voucherPlanService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<VoucherPlan>>getAllPlans()throws VoucherPlanException{
		List<VoucherPlan>voucherPlans = voucherPlanService.list();
		return new ResponseEntity<List<VoucherPlan>>(voucherPlans, HttpStatus.ACCEPTED);
	}
}
