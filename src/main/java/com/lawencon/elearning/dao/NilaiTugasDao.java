package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.NilaiTugas;

public interface NilaiTugasDao {
	
	abstract NilaiTugas insert(NilaiTugas nilai) throws Exception;
	abstract NilaiTugas findByUserPengajarTopic(String user, String pengajar, String materi) throws Exception;
	abstract NilaiTugas findById(String id) throws Exception;
	abstract NilaiTugas update(NilaiTugas nilai) throws Exception;

}
