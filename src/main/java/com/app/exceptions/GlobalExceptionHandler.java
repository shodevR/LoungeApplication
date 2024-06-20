package com.app.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.model.VoucherPlan;



@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails>methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me, WebRequest we){
		
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error", HttpStatus.NON_AUTHORITATIVE_INFORMATION, me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
		
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(PackageException.class)
	public ResponseEntity<MyErrorDetails>PackageExceptionHandler (PackageException ae, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ae.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InventoryException.class)
	public ResponseEntity<MyErrorDetails>InventoryExceptionHandler(InventoryException ie, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
			
	return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<MyErrorDetails>EmployeeExceptionHandler(EmployeeException ee, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ee.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VoucherException.class)
	public ResponseEntity<MyErrorDetails>voucherExceptionHandler(VoucherException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(VoucherPlanException.class)
	public ResponseEntity<MyErrorDetails>voucherPlanExceptionHandler(VoucherPlanException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<MyErrorDetails>ticketExceptionEntity(TicketException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails>userExceptionEntity(UserException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<MyErrorDetails>clientExceptionEntity(ClientException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PartnerException.class)
	public ResponseEntity<MyErrorDetails>partnerExceptionEntity(PartnerException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(StoreUUIDException.class)
	public ResponseEntity<MyErrorDetails>storeUUidExceptionEntity(StoreUUIDException ve, WebRequest req){
		MyErrorDetails err= new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ve.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
}
