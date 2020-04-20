package com.lawencon.elearning.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileUsers;

public interface FileUsersService {

	abstract FileUsers insert(MultipartFile file, String materi, String pengajar, String user, String jenis,
			Date date) throws Exception;

	abstract FileUsers findByUserMateriPengajar(String user, String pengajar, String materi) throws Exception;

	abstract FileUsers update(FileUsers file) throws Exception;

	abstract FileUsers findById(String id) throws Exception;
	
	abstract String insertTask(String id) throws Exception;

}
