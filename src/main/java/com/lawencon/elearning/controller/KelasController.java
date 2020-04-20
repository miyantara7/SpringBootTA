package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.model.KelasUsers;
import com.lawencon.elearning.service.KelasService;

@RestController
@CrossOrigin("*")
public class KelasController {
	
	@Autowired
	private KelasService kelasService;
	
	//cek kelas
		@GetMapping("/user/class")
		public ResponseEntity<?> showAllMateri(@RequestParam("uId") String userid, @RequestParam("mId") String materiId, 
				@RequestParam("pId") String pengajarId){
			boolean cek = false;
			try {
				cek = kelasService.findClass(userid,materiId,pengajarId);
//				list = materiService.findAll();
			} catch (Exception e) {
				return new ResponseEntity<>(cek, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(cek, HttpStatus.OK);
		}
		
		//insert kelas
		@PostMapping("/user/inclass")
		public ResponseEntity<?> insertClass(@RequestParam("uId") String userid, @RequestParam("mId") String materiId, 
				@RequestParam("pId") String pengajarId){
			KelasUsers kelas = new KelasUsers();
			try {
				kelas = kelasService.insertKelas(materiId, pengajarId, userid);
//				list = materiService.findAll();
			} catch (Exception e) {
				return new ResponseEntity<>(kelas, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(kelas, HttpStatus.OK);
		}
		
		@GetMapping("/pengajar/class")
		public ResponseEntity<?> classPengajar(@RequestParam("mId") String materiId, @RequestParam("pId") String pengajarId){
			List<Map<String, Object>> listClass = new ArrayList<>();
			try {
				listClass = kelasService.listKelas(materiId, pengajarId);
			} catch (Exception e) {
				return new ResponseEntity<>(listClass, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(listClass, HttpStatus.OK);
		}

}
