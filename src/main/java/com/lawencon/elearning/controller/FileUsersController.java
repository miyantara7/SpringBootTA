package com.lawencon.elearning.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lawencon.elearning.model.FileUsers;
import com.lawencon.elearning.service.FileUsersService;

@RestController
public class FileUsersController {

	@Autowired
	private FileUsersService fileUjianService;

	@PostMapping("/upload/ujian")
	public ResponseEntity<?> uploadTask(@RequestParam("file") MultipartFile file,
			@RequestParam("idMateri") String idMateri, @RequestParam("idPengajar") String idPengajar,
			@RequestParam("idUser") String idUser, @RequestParam("jenis") String jenis,
			@RequestParam("date") String date) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
			fileUjianService.insert(file, idMateri, idPengajar, idUser, jenis, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
				.path(fileName).path("/db").toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}

	@GetMapping("download/ujian")
	public ResponseEntity<?> downloadTask(@RequestParam("id") String id) {
		FileUsers fileUjian = new FileUsers();
		try {
			fileUjian = fileUjianService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUjian.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUjian.getFileName() + "\"")
				.body(fileUjian.getFile());

	}
	
	//Baru tambahan
	@PostMapping("insert/tugas")
	public ResponseEntity<?> insertTask(@RequestParam("id") String id) {
		String pesan = null;
		try {
			pesan = fileUjianService.insertTask(id);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	
	}
}
