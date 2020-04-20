package com.lawencon.elearning.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileSoal;

public interface FileSoalService {
	abstract FileSoal insert(MultipartFile file, String materi, String pengajar, String jenis, Date startDate,
			Date endDate, String day) throws Exception;

	abstract FileSoal findById(String id) throws Exception;

}
