package com.test.seminar_registration.model;

public class Form {

	private int id;
	private String nama;
	private long nim;
	private String email;
	private String jurusan;
	private String fakultas;
	private long angkatan;
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setNama (String nama) {
		this.nama = nama;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNim (long nim) {
		this.nim = nim;
	}
	
	public long getNim() {
		return nim;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setJurusan (String jurusan) {
		this.jurusan = jurusan;
	}
	
	public String getJurusan() {
		return jurusan;
	}
	
	public void setFakultas (String fakultas) {
		this.fakultas = fakultas;
	}
	
	public String getFakultas() {
		return fakultas;
	}
	
	public void setAngkatan (long angkatan) {
		this.angkatan = angkatan;
	}
	
	public long getAngkatan() {
		return angkatan;
	}
}
