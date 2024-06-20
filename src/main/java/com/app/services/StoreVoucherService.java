package com.app.services;

import com.app.Utils.QRCodeGenerator;
import com.app.exceptions.StoreUUIDException;
import com.app.exceptions.VoucherException;
import com.app.model.QRResponse;
import com.app.model.StoreVoucher;
import com.app.model.Voucher;
import com.app.repository.StoreVoucherRepo;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreVoucherService {
    @Autowired
    private StoreVoucherRepo storeVoucherRepo;
    @Autowired
    private QRcodeService qRcodeService;


    public String createCode(Integer id, Integer No, String expire) throws WriterException, IOException {
        for(int i =0; i<No; i++){
            String ucode = generateUniqueCode();
           StoreVoucher storeVoucher = new StoreVoucher();
           storeVoucher.setFlag(true);
            storeVoucher.setUniqueCode(ucode+id);
            storeVoucher.setVoucherid(id);
            storeVoucher.setExpire(expire);
            storeVoucherRepo.save(storeVoucher);
            /*String qr = qRcodeService.QrGenerate(ucode+id,ucode+id,Integer.toString(id));*/
        }

         return No +" of vouchers created for voucherId: "+id;
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH =8;

    public static String generateUniqueCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }

    public StoreVoucher storeVoucher(String uuid) throws StoreUUIDException {


        StoreVoucher storeVoucher1 = storeVoucherRepo.findByUniqueCode(uuid);
        if (storeVoucher1 == null) {
            throw new StoreUUIDException("StoreVoucher with uniqueCode " + uuid + " not found");
        }
        return storeVoucher1;

    }
    public StoreVoucher redeemVoucher(String uuid) throws StoreUUIDException, VoucherException {


        StoreVoucher storeVoucher1 = storeVoucherRepo.findByUniqueCode(uuid);
        if (storeVoucher1 == null) {
            throw new StoreUUIDException("StoreVoucher with uniqueCode " + uuid + " not found");
        }

        if (!storeVoucher1.isFlag()) {  // Assuming the flag is a boolean
            throw new VoucherException("StoreVoucher with uniqueCode " + uuid + " is already redeemed");
        }

        // Assuming the voucher has a field `expiryDate` which stores the expiration date as a String
        String expiryDateStr = storeVoucher1.getExpire();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate expiryDate = LocalDate.parse(expiryDateStr, formatter);
            if (LocalDate.now().isAfter(expiryDate)) {
                throw new VoucherException("StoreVoucher with uniqueCode " + uuid + " is expired");
            }
        } catch (DateTimeParseException e) {
            throw new VoucherException("StoreVoucher with uniqueCode " + uuid + " has an invalid expiry date format");
        }

        return storeVoucher1;
    }
    public StoreVoucher changeStatus(String uuid) throws StoreUUIDException, VoucherException {


        StoreVoucher storeVoucher1 = storeVoucherRepo.findByUniqueCode(uuid);
        if (storeVoucher1 == null) {
            throw new StoreUUIDException("StoreVoucher with uniqueCode " + uuid + " not found");
        }

        if (!storeVoucher1.isFlag()) {  // Assuming the flag is a boolean
            throw new VoucherException("StoreVoucher with uniqueCode " + uuid + " is already redeemed");
        }
        storeVoucher1.setFlag(false);
        storeVoucherRepo.save(storeVoucher1);
        return storeVoucher1;
    }
    public List<QRResponse> getQRImagesByVoucherid(Integer voucherid) throws WriterException, IOException {
        List<StoreVoucher> vouchers = findByVoucherId(voucherid);
        return vouchers.stream()
                .map(voucher -> {
                    try {
                        String qrImage = QRCodeGenerator.generateQRCodeImage(voucher.getUniqueCode());
                        return new QRResponse(voucher.getUniqueCode(), qrImage,voucher.isFlag(),voucher.getExpire());
                    } catch (WriterException | IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<StoreVoucher> findByVoucherIdAndFlagTrue(Integer voucherId) {
        return storeVoucherRepo.findByVoucheridAndFlagTrue(voucherId);
    }
    public List<StoreVoucher> findByVoucherId(Integer voucherId) {
        return storeVoucherRepo.findByVoucherid(voucherId);
    }


    public void updateExpireDate(Integer voucherid, String newExpireDate) {
        List<StoreVoucher> vouchers = storeVoucherRepo.findByVoucherid(voucherid);
        for (StoreVoucher voucher : vouchers) {
            voucher.setExpire(newExpireDate);
        }
        storeVoucherRepo.saveAll(vouchers);
    }


}
