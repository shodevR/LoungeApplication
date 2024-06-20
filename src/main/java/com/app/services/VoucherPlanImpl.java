package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.VoucherPlanException;
import com.app.model.VoucherPlan;
import com.app.repository.VoucherPlanRepo;

@Service
public class VoucherPlanImpl implements VoucherPlanService{

	@Autowired
	private VoucherPlanRepo vRepo;

	@Override
	public VoucherPlan addVoucherPlan(VoucherPlan voucherPlan) throws VoucherPlanException {
		VoucherPlan addVoucherPlan = vRepo.save(voucherPlan);
		
		return addVoucherPlan;
	}

	@Override
	public List<VoucherPlan> list() throws VoucherPlanException {
		List<VoucherPlan>voucherPlans = vRepo.findAll();
		
		return voucherPlans;
		
	}

	@Override
	public VoucherPlan deleteVoucherPlan(Integer id) throws VoucherPlanException {
		// TODO Auto-generated method stub
		VoucherPlan existVoucherplan = vRepo.findById(id).orElseThrow(()-> new VoucherPlanException("Voucher Plan does not exist wit the id : "+ id));	
		vRepo.delete(existVoucherplan);
		return existVoucherplan;
		
	}

	@Override
	public VoucherPlan updateVoucherPlan(VoucherPlan voucherPlan) throws VoucherPlanException {
		Optional<VoucherPlan>optional = vRepo.findById(voucherPlan.getVoucherPlanId());
		if (optional.isPresent()) {
			return vRepo.save(voucherPlan);
			
		}else {
			throw new VoucherPlanException("Invalid Details");
		}
	}

	@Override
	public VoucherPlan getById(Integer id) throws VoucherPlanException {
		VoucherPlan voucherPlan = vRepo.findById(id).orElseThrow(()->new VoucherPlanException("Voucher Plan not found"));
		return voucherPlan;
	}
	
	
	

}
