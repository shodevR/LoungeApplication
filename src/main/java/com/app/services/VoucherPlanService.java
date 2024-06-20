package com.app.services;

import java.util.List;

import com.app.exceptions.VoucherPlanException;
import com.app.model.VoucherPlan;

public interface VoucherPlanService {
	public VoucherPlan addVoucherPlan (VoucherPlan voucherPlan) throws VoucherPlanException;
	public List<VoucherPlan> list ()throws VoucherPlanException;
	public VoucherPlan deleteVoucherPlan(Integer id) throws VoucherPlanException;
	public VoucherPlan updateVoucherPlan (VoucherPlan voucherPlan) throws VoucherPlanException;
	public VoucherPlan getById(Integer id) throws VoucherPlanException;

}
