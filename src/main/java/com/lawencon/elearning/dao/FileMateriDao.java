package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;
import com.lawencon.elearning.model.FileMateri;

public interface FileMateriDao {

	abstract FileMateri insertMateri(FileMateri materi) throws Exception;

	abstract List<Map<String, Object>> findMateri(String category, String pengajar, String topik, String id)
			throws Exception;

	abstract List<Map<String, Object>> findByCategory(String category) throws Exception;

	abstract List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception;

//	List<Materi> findAllActive(String topik) throws Exception;
	abstract List<FileMateri> findAll() throws Exception;

	abstract List<String> findTopic(String materiId, String pengajarId) throws Exception;

	abstract List<Map<String, Object>> findJumMateri(String materi, String pengajar, String topik) throws Exception;

	abstract List<String> findTopic(String materiId, String pengajarId, String hari, String tgl, String waktu)
			throws Exception;
}
