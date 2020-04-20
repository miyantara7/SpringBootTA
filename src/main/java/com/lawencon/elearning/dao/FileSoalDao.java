package com.lawencon.elearning.dao;

import java.sql.Date;

import com.lawencon.elearning.model.FileSoal;

public interface FileSoalDao {
	abstract FileSoal insert(FileSoal file) throws Exception;

	abstract FileSoal findByMateriPengajar(String pengajar, String materi) throws Exception;

	abstract FileSoal update(FileSoal file) throws Exception;

	abstract FileSoal findById(String id) throws Exception;

	abstract Date findDateTask(String id) throws Exception;
}
