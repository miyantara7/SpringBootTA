package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.service.NilaiTugasService;

@RestController
@CrossOrigin("*")
public class NilaiTugasController {

	@Autowired
	private NilaiTugasService nilaiTugasService;

	@PostMapping("/insert/nilai/tugas")
	public ResponseEntity<?> insertPengajar(@RequestParam("nilai") int nilai, @RequestParam("user") String user,
			@RequestParam("pengajar") String pengajar, @RequestParam("materi") String materi, @RequestParam("day") String day) {
		try {
			nilaiTugasService.insert(nilai, pengajar, user, materi);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to insert", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
