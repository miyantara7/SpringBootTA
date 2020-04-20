package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileMateri;

public interface FileMateriService {
	abstract FileMateri insertMateri(MultipartFile file, String topic, String pId, String cId, String name, String hari,
			String tanggal, String waktu, String kelas) throws Exception;

	abstract List<Map<String, Object>> findMateri(String category, String pengajar, String topik, String id)
			throws Exception;

	abstract List<Map<String, Object>> findByCategory(String category) throws Exception;

	abstract List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception;

	abstract List<String> findTopic(String materiId, String pengajarId) throws Exception;

	abstract List<Map<String, Object>> findJumMateri(String category, String pengajar, String topik) throws Exception;
}
