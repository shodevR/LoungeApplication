package com.app.controller;

import com.app.Utils.QRCodeGenerator;
import com.app.exceptions.StoreUUIDException;
import com.app.exceptions.VoucherException;
import com.app.model.*;
import com.app.services.StoreVoucherService;
import com.app.services.VoucherService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/code")
public class VoucherCodeController {

    @Autowired
     private StoreVoucherService storeVoucherService;
    @Autowired
    private VoucherService voucherService;
    @GetMapping("/valid")
    public ResponseEntity<StoreVoucher> getCodeEntity(@RequestParam String code) throws StoreUUIDException {
        StoreVoucher storeVoucher = storeVoucherService.storeVoucher(code);
        return new ResponseEntity<>(storeVoucher, HttpStatus.OK);
    }

    @GetMapping("/redeem")
    public ResponseEntity<CustomModel>redeemVoucher(@RequestParam String code) throws StoreUUIDException, VoucherException {
        StoreVoucher storeVoucher1 = storeVoucherService.redeemVoucher(code);
        Integer vid = storeVoucher1.getVoucherid();
        CustomModel customModel = new CustomModel();

        Voucher voucherDetails = voucherService.getVoucher(vid);



        customModel.setClientName(voucherDetails.getClient().getClientName());
        customModel.setDiscount(voucherDetails.getClient().getDiscount());
        customModel.setAmount(voucherDetails.getPackageDetails().getAmount());
        Integer pid = voucherDetails.getPackageDetails().getPackageId();
        customModel.setPackageId(pid);
        customModel.setIssueDate(voucherDetails.getIssueDate());
        customModel.setUniqueCode(storeVoucher1.getUniqueCode());
        customModel.setVoucherId(storeVoucher1.getVoucherid());

        return new ResponseEntity<>(customModel, HttpStatus.OK);

    }
    @GetMapping("/redeemed")
    public String redeemedVoucher(@RequestParam String code) throws StoreUUIDException, VoucherException{
        StoreVoucher storeVoucher1 = storeVoucherService.redeemVoucher(code);
        StoreVoucher storeVoucher2 = storeVoucherService.changeStatus(code);
        Integer vid = storeVoucher1.getVoucherid();
        Voucher voucherDetails = voucherService.getVoucher(vid);
        Integer claim = voucherDetails.getClaimed();
        voucherDetails.setClaimed(claim+1);
        voucherService.updateVoucher(voucherDetails);
        return  "voucher redeemed";
    }
    @GetMapping("/cancel")
    public String cancelVoucher(@RequestParam String code) throws StoreUUIDException, VoucherException{
        StoreVoucher storeVoucher1 = storeVoucherService.redeemVoucher(code);
        storeVoucherService.changeStatus(code);
        Integer vid = storeVoucher1.getVoucherid();
        Voucher voucherDetails = voucherService.getVoucher(vid);
        Integer unclaimed = voucherDetails.getNoOfVouchers();
        voucherDetails.setNoOfVouchers(unclaimed-1);
        voucherService.updateVoucher(voucherDetails);
        return  "voucher cancelled";
    }
  /*  @GetMapping("/getImages/{voucherId}")
    public ResponseEntity<List<StoreVoucher>> getImagesByVoucherId(@PathVariable Integer voucherId) {
        List<StoreVoucher> storeVouchers = storeVoucherService.findByVoucherIdAndFlagTrue(voucherId);
        return new ResponseEntity<>(storeVouchers, HttpStatus.OK);
    }*/
  @GetMapping("/getQRImages/{voucherId}")
  public ResponseEntity<List<QRResponse>> getQRImagesByVoucherId(@PathVariable Integer voucherId) {
      try {
          List<QRResponse> response = storeVoucherService.getQRImagesByVoucherid(voucherId);
          return new ResponseEntity<>(response, HttpStatus.OK);
      } catch (Exception e) {
          e.printStackTrace();
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}
