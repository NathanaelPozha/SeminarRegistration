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
			String sql = "INSERT INTO registration_form (nama, nim, email, jurusan, fakultas, angkatan) VALUES (?, ?, ?, ?, ?, ?)";
			
			jdbc.update(sql,
					form.getNama(),
					form.getNim(),
					form.getEmail(),
					form.getJurusan(),
					form.getFakultas(),
					form.getAngkatan());
		}
		
		public List<Form> findAllForms(){
			String sql = "SELECT * FROM registration_form";
			
			RowMapper<Form> formRowMapper = (r, i) -> {
				Form rowObject = new Form();
				rowObject.setId(r.getInt("id"));
				rowObject.setNama(r.getString("nama"));
				rowObject.setNim(r.getLong("nim"));
				rowObject.setEmail(r.getString("email"));
				rowObject.setJurusan(r.getString("jurusan"));
				rowObject.setFakultas(r.getString("fakultas"));
				rowObject.setAngkatan(r.getLong("angkatan"));
				return rowObject;
			};
			
			return jdbc.query(sql, formRowMapper);
		}
	}
