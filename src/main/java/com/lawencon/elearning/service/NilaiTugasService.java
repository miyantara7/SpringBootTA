package com.lawencon.elearning.service;

import com.lawencon.elearning.model.NilaiTugas;

public interface NilaiTugasService {

	abstract NilaiTugas insert(int nilai, String pengajar, String user, String materi) throws Exception;
}
