package com.test.seminar_registration.model;

public class Form {

	private int id;
	private String nama;
	private long nim;
	private String email;
	private String nomor_telepon;
	private String id_line;
	private String jurusan;
	private String fakultas;
	private String angkatan;
	private int nomor_kursi;
	
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
	
	public void setNomorTelepon (String nomor_telepon) {
		this.nomor_telepon = nomor_telepon;
	}
	
	public String getNomorTelepon() {
		return nomor_telepon;
	}
	
	public void setIdLine (String id_line) {
		this.id_line = id_line;
	}
	
	public String getIdLine() {
		return id_line;
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
	
	public void setAngkatan (String angkatan) {
		this.angkatan = angkatan;
	}
	
	public String getAngkatan() {
		return angkatan;
	}
	
	public void setNomorKursi(int nomor_kursi) {
	        this.nomor_kursi = nomor_kursi;
	}
    
	public int getNomorKursi() {
	        return nomor_kursi;
	}
}
