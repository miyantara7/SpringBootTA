package com.lawencon.elearning.service;

import com.lawencon.elearning.model.NilaiUjian;

public interface NilaiUjianService {
	abstract NilaiUjian insert(int nilai, String user, String pengajar, String materi) throws Exception;
}
