package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.model.NilaiUjian;
import com.lawencon.elearning.service.NilaiUjianService;

@RestController
public class NilaiUjianController extends BaseController<NilaiUjian> {

	@Autowired
	private NilaiUjianService nilaiUjianService;

	@PostMapping("/insert/nilai/ujian")
	public ResponseEntity<?> insertPengajar(@RequestParam("nilai")int nilai, @RequestParam("user") String user, @RequestParam("pengajar")String pengajar, @RequestParam("materi")String materi) {
		try {
			nilaiUjianService.insert(nilai, user, pengajar, materi);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to insert", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
