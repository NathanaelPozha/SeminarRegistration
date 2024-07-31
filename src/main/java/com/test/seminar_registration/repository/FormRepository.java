package com.test.seminar_registration.repository;

	import org.springframework.stereotype.Repository;
	import org.springframework.jdbc.core.JdbcTemplate;		
	import org.springframework.jdbc.core.RowMapper;
	import com.test.seminar_registration.model.Form;

	import java.util.*;

	@Repository
	public class FormRepository {

		private final JdbcTemplate jdbc;
		
		public FormRepository(JdbcTemplate jdbc) {
			this.jdbc = jdbc;
		}
		
		public void storeForm(Form form) {
			String sql = "INSERT INTO registration_data (nama, nim, email, nomor_telepon, id_line, jurusan, fakultas, angkatan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			jdbc.update(sql,
					form.getNama(),
					form.getNim(),
					form.getEmail(),
					form.getNomorTelepon(),
					form.getIdLine(),
					form.getJurusan(),
					form.getFakultas(),
					form.getAngkatan());
		}
		
		public List<Form> findAllForms(){
			String sql = "SELECT * FROM registration_data";
			
			RowMapper<Form> formRowMapper = (r, i) -> {
				Form rowObject = new Form();
				rowObject.setId(r.getInt("id"));
				rowObject.setNama(r.getString("nama"));
				rowObject.setNim(r.getLong("nim"));
				rowObject.setEmail(r.getString("email"));
				rowObject.setNomorTelepon(r.getString("nomor_telepon"));
				rowObject.setIdLine(r.getString("id_line"));
				rowObject.setJurusan(r.getString("jurusan"));
				rowObject.setFakultas(r.getString("fakultas"));
				rowObject.setAngkatan(r.getString("angkatan"));
				rowObject.setNomorKursi(r.getInt("nomor_kursi"));
				return rowObject;
			};
			
			return jdbc.query(sql, formRowMapper);
		}
		
	    public boolean findNim(long nim) {
	        String sql = "SELECT COUNT(*) FROM student_data WHERE nim = ?";
	        int count = jdbc.queryForObject(sql, Integer.class, nim);
	        return count > 0;
	    }
	    
	    public String findNameByNim(long nim) {
	        String sql = "SELECT nama FROM student_data WHERE nim = ?";
	        return jdbc.queryForObject(sql, String.class, nim);
	    }
	    
	    public boolean nimChecker(long nim) {
	        String sql = "SELECT COUNT(*) FROM registration_data WHERE nim = ?";
	        int count = jdbc.queryForObject(sql, Integer.class, nim);
	        return count > 0;
	    }
	    
	    public boolean registerChecker(String email, String nomor_telepon, String id_line) {
	    	System.out.println("Checking registration with email: " + email);
	        System.out.println("Checking registration with nomor_telepon: " + nomor_telepon);
	        System.out.println("Checking registration with id_line: " + id_line);
	    	
	        String sql = "SELECT COUNT(*) FROM registration_data WHERE email = ? OR nomor_telepon = ? OR id_line = ?";
	        
	        int count = jdbc.queryForObject(sql, Integer.class, email, nomor_telepon, id_line);
	        
	        System.out.println("Count of matching records: " + count);
	        
	        return count > 0;
	    }
	    
	    public void updateForm(Form form) {
			String sql = "UPDATE registration_data SET nama = ?, nim = ?, email = ?, nomor_telepon = ?, id_line = ?, jurusan = ?, fakultas = ?, angkatan = ?, nomor_kursi = ? WHERE id = ?";
			
			jdbc.update(sql,
					form.getNama(),
					form.getNim(),
					form.getEmail(),
					form.getNomorTelepon(),
					form.getIdLine(),
					form.getJurusan(),
					form.getFakultas(),
					form.getAngkatan(),
					form.getNomorKursi(),
					form.getId());
		}
	    
	    public void deleteFormById(int id) {
			String sql = "DELETE FROM registration_data WHERE id = ?";
			jdbc.update(sql, id);
		}
	}
