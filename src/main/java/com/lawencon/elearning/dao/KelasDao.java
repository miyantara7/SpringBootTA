package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.KelasUsers;

public interface KelasDao {
	abstract KelasUsers findClass(String mId, String pId, String uId) throws Exception;

	abstract KelasUsers insertKelas(KelasUsers kelas) throws Exception;
	
	abstract List<Map<String, Object>> listKelas (String mId, String pId) throws Exception;
}
