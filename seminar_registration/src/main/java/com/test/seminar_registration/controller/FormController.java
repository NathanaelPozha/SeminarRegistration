package com.test.seminar_registration.controller;

import com.test.seminar_registration.model.Form;
import com.test.seminar_registration.service.FormService;
import com.test.seminar_registration.signinprocessor.SignInProcessor;
import com.test.seminar_registration.loginprocessor.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes({"nama", "nim"})
public class FormController {
	private final FormService formService;
	private final LoginProcessor loginProcessor;
	private final SignInProcessor signinProcessor;
	
	public FormController(FormService formService, LoginProcessor loginProcessor, SignInProcessor signinProcessor) {
		this.formService = formService;
		this.loginProcessor = loginProcessor;
		this.signinProcessor = signinProcessor;
	}
	
	@GetMapping ("/register")
	public String showForms(Model model, @ModelAttribute("nama") String nama, @ModelAttribute("nim") long nim){
		
		model.addAttribute("nama", nama);
		model.addAttribute("nim", nim);
		return "register.html";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "login.html";
	}
	
	@GetMapping("/signin")
	public String signinGet() {
		return "signin.html";
	}
	
	@GetMapping ("/view")
	public String viewForms(Model model){
		var forms = formService.findAll();
		model.addAttribute("forms", forms);
		
		return "view.html";
	}
	
	@PostMapping ("/register")
	public String addForms(
		@ModelAttribute("nama") String nama,
		@ModelAttribute("nim") long nim,
		@RequestParam String email,
		@RequestParam String jurusan,
		@RequestParam String fakultas,
		@RequestParam String angkatan,
		Model model,
		SessionStatus sessionStatus
	) {if (formService.formRegisterChecker(nama, nim, email)) {
        model.addAttribute("error", "Form with this Name, Student Id, or Email already exists.");
        return "register.html";
    } else {
		Form f = new Form();
		f.setNama(nama);
		f.setNim(nim);
		f.setEmail(email);
		f.setFakultas(fakultas);
		f.setJurusan(jurusan);
		f.setAngkatan(angkatan);
		formService.addForm(f);
		
		sessionStatus.setComplete();
		var forms = formService.findAll();
		model.addAttribute("forms", forms);
		
		model.addAttribute("message", "Your have been registered");
		return "signin.html";
		}
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
	
	@PostMapping("/signin")
	public String signinPost(
		@RequestParam long nim,
		Model model
		)
	{
		signinProcessor.setNim(nim);
		String nama = formService.findName(nim);
		boolean signIn = signinProcessor.signIn();
		
		if (signIn) {
			if (!formService.formNimChecker(nim)) {
			model.addAttribute("nama", nama);
			model.addAttribute("nim", nim);
			model.addAttribute("message", "You are now signed in.");
			return "redirect:/register";
			} else {
				model.addAttribute("message", "Your student id have been registered before.");
				return "signin.html";
			}
		}
			model.addAttribute("message", "SignIn failed!");
			return "signin.html";
	}
}

