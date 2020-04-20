package com.lawencon.elearning.dao;

import java.sql.Date;

import com.lawencon.elearning.model.FileUsers;

public interface FileUsersDao {

	abstract FileUsers insert(FileUsers file) throws Exception;
	abstract FileUsers findByUserMateriPengajar(String user, String pengajar, String materi) throws Exception;
	abstract FileUsers update(FileUsers file) throws Exception;
	abstract FileUsers findById(String id) throws Exception;
	
	abstract Date findDateTask(String id) throws Exception;
}
