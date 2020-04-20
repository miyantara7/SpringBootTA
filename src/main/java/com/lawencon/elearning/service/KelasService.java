package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.KelasUsers;

public interface KelasService {
	abstract boolean findClass(String uId,String mId,String pId) throws Exception;
	abstract KelasUsers insertKelas(String mId, String pId, String uId) throws Exception;
	abstract List<Map<String, Object>> listKelas (String mId, String pId) throws Exception;
}
