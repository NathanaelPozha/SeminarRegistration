package com.test.seminar_registration.signinprocessor;

import com.test.seminar_registration.repository.FormRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SignInProcessor {
	
	private final FormRepository formRepository;
	private long nim;
	
	public SignInProcessor(FormRepository formRepository) {
		this.formRepository = formRepository;
	}
	
	
	public boolean signIn() {
		return formRepository.findNim(this.getNim());
	}
	
	public void setNim (long nim) {
		this.nim = nim;
	}
	
	public long getNim() {
		return nim;
	}
}
