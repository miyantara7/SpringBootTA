package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.NilaiUjian;

public interface NilaiUjianDao {

	abstract NilaiUjian insert(NilaiUjian nilai) throws Exception;
	abstract NilaiUjian update(NilaiUjian nilai) throws Exception;
	abstract NilaiUjian findByUserPengajarMateri(String user, String materi, String pengajar) throws Exception;
}
