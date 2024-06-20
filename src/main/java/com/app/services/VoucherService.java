package com.app.services;

import java.io.IOException;
import java.util.List;


import com.app.exceptions.VoucherException;
import com.app.model.Voucher;
import com.google.zxing.WriterException;

public interface VoucherService {
	public List<Voucher>viewAllVouchers() throws VoucherException;
	public Voucher addVoucher(Voucher voucher) throws VoucherException, WriterException, IOException;
	public Voucher updateVoucher(Voucher voucher) throws VoucherException;
	public Voucher removeVoucher(Integer voucheId) throws VoucherException;
	public Voucher getVoucher (Integer voucherId) throws VoucherException;

}
