package com.lawencon.elearning.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.model.Absen;
import com.lawencon.elearning.service.AbsenService;

@RestController
public class AbsenController {

	@Autowired
	private AbsenService absenService;

	@PostMapping("/insert/kehadiran")
	public ResponseEntity<?> insert(@RequestParam("idUser") String user, @RequestParam("idPengajar") String pengajar,
			@RequestParam("idMateri") String materi, @RequestParam("date") String date) {
		Absen absen = new Absen();
		try {
			Date dateFix = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
			absen = absenService.insert(user, pengajar, materi, dateFix);
			return new ResponseEntity<>(absen, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Absen", HttpStatus.BAD_REQUEST);
		}
	}

}
