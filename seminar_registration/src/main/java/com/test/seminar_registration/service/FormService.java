package com.test.seminar_registration.service;

import org.springframework.stereotype.Service;
import com.test.seminar_registration.repository.FormRepository;
import com.test.seminar_registration.model.Form;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormService {

	private List<Form> forms = new ArrayList<>();
	private FormRepository formRepository;
	
	public FormService(FormRepository formRepository) {
		this.formRepository = formRepository;
	}
	
	public void addForm(Form f) {
		forms.add(f);
		formRepository.storeForm(f);
	}
	
	public List<Form> findAll(){
		return formRepository.findAllForms();
	}
	
	public String findName(long nim){
		return formRepository.findNameByNim(nim);
	}
	
	public boolean formNimChecker(long nim) {
        return formRepository.nimChecker(nim);
    }
	
	public boolean formRegisterChecker(String nama, long nim, String email) {
        return formRepository.registerChecker(nama, nim, email);
    }

}
