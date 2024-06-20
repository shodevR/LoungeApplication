package com.app.controller;

import com.app.exceptions.TicketException;
import com.app.model.Ticket;
import com.app.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.exceptions.VoucherException;

import com.app.model.Voucher;

import com.app.services.VoucherService;



@Controller
public class HomeController {

	@Autowired
	private TicketService ticketService;
	@GetMapping("/home")
	public String home(Model model) {
		// You can add attributes to the model if needed
		return "ticket"; // This corresponds to home.html
	}
	@Autowired
	private VoucherService voucherService;


	@GetMapping("/packages")
	public String index() {
		return "Package/index";
	}

	@GetMapping("/packages/list")
	public String packagelsit() {
		return "Package/list";
	}

	@GetMapping("/file")
	public String file() {
		return "File/index";
	}

	@GetMapping("/voucher")
	public String voucher() {
		return "voucher/index";
	}

	@GetMapping("temp/ticket") public String ticket3() { return "Ticket/ticket"; }
	@GetMapping("/login")
	public String login() {
		return "login"; // This corresponds to login.html
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	@GetMapping("/ticket") 	
	public String ticket(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "Walkin";
	}

	@GetMapping("/voucher/update/update-voucher/{id}")
	public String updateVoucherPage() {
		// Here you can fetch the voucher details with the given ID from the database
		// and pass it to the view
		// For simplicity, let's assume you have a service class to fetch voucher
		// details
		// Replace "YourService" with your actual service class
		/* Voucher voucher2 = voucherService.getVoucher(id); */

		return "voucher/index";
	}
	@GetMapping("create-client")
	public String createClient(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "create-client";
	}

	@GetMapping("/voucher/list")
	public String lsit(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "voucher/list";
	}


	@GetMapping("/voucherlist")
	public String vList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "VoucherList";
	}
	@GetMapping("/client")
	public String cList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "clientList";
	}
	@GetMapping("/register")
	public String register(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "register";
	}

	@GetMapping("/create-package")
	public String createPackage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "create-package";
	}

	@GetMapping("/create-voucher")
	public String createVoucher(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "create-voucher";
	}
	@GetMapping("/create-partner")
	public String createpartner(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "create-partner";
	}
	@GetMapping("/customer-list")
	public String customerList(){
		return "customer-list1";
	}
	@GetMapping("/customer/details/{id}")
	public String customerDetails(@PathVariable("id") Integer ticketId) {
		// Assuming you have a service to fetch customer details by ID

		return "ticketDetails"; // This should correspond to your customer details view
	}
	@GetMapping("/test")
	public String test(){
		return "Test";
	}


	@GetMapping("/qrid")
	public String QRID(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); // Get the username

		// Pass the username to the HTML template
		model.addAttribute("username", username);
		return "QRbyId";
	}
	@GetMapping("/qrid2/{id}")
	public String coupanDetails(@PathVariable("id") Integer voucherid) {
		// Assuming you have a service to fetch customer details by ID

		return "QRbyId2"; // This should correspond to your customer details view
	}
	@GetMapping("/updateVoucher/{id}")
	public String updateVoucher(@PathVariable("id") Integer voucherid) {
		// Assuming you have a service to fetch customer details by ID

		return "update-voucher"; // This should correspond to your customer details view
	}
}
