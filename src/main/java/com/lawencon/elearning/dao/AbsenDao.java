package com.lawencon.elearning.dao;

import java.util.Date;

import com.lawencon.elearning.model.Absen;

public interface AbsenDao {

	abstract Absen insert(Absen absen) throws Exception;

	abstract Absen update(Absen absen) throws Exception;

	abstract void delete(String id) throws Exception;

	abstract Absen findByUserMateriPengajar(String user, String materi, String pengajar, Date date) throws Exception;

}
