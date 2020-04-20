package com.lawencon.elearning.service;

import java.util.Date;

import com.lawencon.elearning.model.Absen;

public interface AbsenService {

	abstract Absen insert(String user, String pengajar, String materi, Date date) throws Exception;
}
