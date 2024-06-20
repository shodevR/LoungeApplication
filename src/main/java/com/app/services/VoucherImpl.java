package com.app.services;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.PackageException;
import com.app.exceptions.VoucherException;
import com.app.model.PackageDetails;
import com.app.model.Voucher;

import com.app.repository.VoucherRepo;
import com.google.zxing.WriterException;

@Service
public class VoucherImpl implements VoucherService{

	@Autowired
	private QRcodeService qRcodeService;
	@Autowired
	private VoucherRepo eRepo;
	@Autowired
	private StoreVoucherService storeVoucherService;
	@Override
	public List<Voucher> viewAllVouchers() throws VoucherException {
		List<Voucher>vouchers = eRepo.findAll();
		if (vouchers.size()>0) {
			return vouchers;
		}else {
			throw new VoucherException("No vouchers Found");
		}	
	}

	@Override
	public Voucher addVoucher(Voucher voucher) throws VoucherException, WriterException, IOException {

		voucher.setClaimed(0);



		Voucher addVoucher = eRepo.save(voucher);
		storeVoucherService.createCode(addVoucher.getVoucherId(), addVoucher.getNoOfVouchers(),addVoucher.getValidDate());
		return addVoucher;
	}
	public void updateExpireDate(Integer voucherid,String newExpireDate) {
		storeVoucherService.updateExpireDate(voucherid, newExpireDate);
	}

	@Override
	public Voucher updateVoucher(Voucher voucher) throws VoucherException {
		Optional<Voucher>optional = eRepo.findById(voucher.getVoucherId());
		if (optional.isPresent()) {
			storeVoucherService.updateExpireDate(voucher.getVoucherId(), voucher.getValidDate());
			return eRepo.save(voucher);
			
		}else {
			throw new VoucherException("Invalid Details");
		}
	}

	@Override
	public Voucher removeVoucher(Integer voucheId) throws VoucherException {
		Voucher existVoucher = eRepo.findById(voucheId).orElseThrow(()-> new VoucherException("Voucher does not exist wit the id : "+ voucheId));	
		eRepo.delete(existVoucher);
		return existVoucher;
	}

	@Override
	public Voucher getVoucher(Integer voucherId) throws VoucherException {
		// TODO Auto-generated method stub
		Voucher voucher = eRepo.findById(voucherId).orElseThrow(()->new VoucherException("Voucher not found"));
		return voucher;
	}

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int CODE_LENGTH = 10;

	public static String generateUniqueCode() {
		SecureRandom random = new SecureRandom();
		StringBuilder code = new StringBuilder();

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			code.append(CHARACTERS.charAt(randomIndex));
		}

		return code.toString();
	}
	

}
