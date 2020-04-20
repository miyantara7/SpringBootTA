package com.lawencon.elearning.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lawencon.elearning.model.FileSoal;
import com.lawencon.elearning.service.FileSoalService;

@RestController
@CrossOrigin("*")
public class FileSoalController {

	@Autowired
	private FileSoalService fileSoalService;

	@PostMapping("upload/soal")
	public ResponseEntity<?> uploadTask(@RequestParam("file") MultipartFile file,
			@RequestParam("idMateri") String idMateri, @RequestParam("idPengajar") String idPengajar,
			@RequestParam("jenis") String jenis, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("day") String day) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(startDate);
			Date end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(endDate);
			fileSoalService.insert(file, idMateri, idPengajar, jenis, start, end, day);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
				.path(fileName).path("/db").toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}

	@GetMapping("download/soal")
	public ResponseEntity<?> downloadTask(@RequestParam("id") String id) {
		FileSoal file = new FileSoal();
		try {
			file = fileSoalService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
				.body(file.getFile());

	}
}
