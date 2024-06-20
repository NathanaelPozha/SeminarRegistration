package com.test.seminar_registration.controller;

import com.test.seminar_registration.model.Form;
import com.test.seminar_registration.service.FormService;
import com.test.seminar_registration.loginprocessor.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FormController {
	private final FormService formService;
	private final LoginProcessor loginProcessor;
	
	public FormController(FormService formService, LoginProcessor loginProcessor) {
		this.formService = formService;
		this.loginProcessor = loginProcessor;
	}
	
	@GetMapping ("/register")
	public String showForms(Model model){
		return "register.html";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "login.html";
	}
	
	@GetMapping ("/view")
	public String viewForms(Model model){
		var forms = formService.findAll();
		model.addAttribute("forms", forms);
		
		return "view.html";
	}
	
	@PostMapping ("/register")
	public String addForms(
		@RequestParam String nama,
		@RequestParam long nim,
		@RequestParam String email,
		@RequestParam String jurusan,
		@RequestParam String fakultas,
		@RequestParam long angkatan,
		Model model
	) {
		Form f = new Form();
		f.setNama(nama);
		f.setNim(nim);
		f.setEmail(email);
		f.setJurusan(jurusan);
		f.setFakultas(fakultas);
		f.setAngkatan(angkatan);
		formService.addForm(f);
		
		var forms = formService.findAll();
		model.addAttribute("forms", forms);
		
		return "register.html";
	}
	
	@PostMapping("/login")
	public String loginPost(
		@RequestParam String username,
		@RequestParam String password,
		Model model
		)
	{
		loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
		boolean loggedIn = loginProcessor.login();
		
		if (loggedIn) {
			model.addAttribute("message", "You are now logged in.");
			return "redirect:/view";
		}
			model.addAttribute("message", "Login failed!");
			return "login.html";
	}
}

