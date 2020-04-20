package com.lawencon.elearning.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.dao.FileUsersDao;
import com.lawencon.elearning.model.FileUsers;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.Pengajar;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class FileUsersServiceImpl implements FileUsersService {

	@Autowired
	private FileUsersDao fileUjianService;

	@Override
	public FileUsers insert(MultipartFile file, String materi, String pengajar, String user, String jenis, Date date)
			throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileUsers fileUjian = null;
		FileUsers fileNew = new FileUsers();
		Materi m = new Materi();
		Pengajar p = new Pengajar();
		Users u = new Users();
		fileNew.setFile(file.getBytes());
		fileNew.setFileName(fileName);
		fileNew.setFileType(file.getContentType());
		try {
			fileUjian = fileUjianService.findByUserMateriPengajar(user, pengajar, materi);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (fileUjian != null) {
			fileUjian.setFile(file.getBytes());
			fileUjian.setFileType(file.getContentType());
			fileUjian.setFileName(fileName);
			return fileUjianService.update(fileUjian);
		}
		if (jenis.equalsIgnoreCase(JenisFile.TUGAS.name())) {
			fileNew.setJenis(JenisFile.TUGAS.name());
		}
		if (jenis.equalsIgnoreCase(JenisFile.UJIAN.name())) {
			fileNew.setJenis(JenisFile.UJIAN.name());
		}
		fileNew.setDate(date);
		p.setPengajarId(pengajar);
		m.setId(materi);
		u.setId(user);
		fileNew.setPengajar(p);
		fileNew.setMateri(m);
		fileNew.setUser(u);
		return fileUjianService.insert(fileNew);

	}

	@Override
	public FileUsers findByUserMateriPengajar(String user, String pengajar, String materi) throws Exception {
		return fileUjianService.findByUserMateriPengajar(user, pengajar, materi);
	}

	@Override
	public FileUsers update(FileUsers file) throws Exception {
		return fileUjianService.update(file);
	}

	@Override
	public FileUsers findById(String id) throws Exception {
		return fileUjianService.findById(id);
	}

	@Override
	public String insertTask(String id) throws Exception {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (date.compareTo(fileUjianService.findDateTask(id)) > 0) {
			return "Anda telat mengirim tugas !";
		} else if (date.compareTo(fileUjianService.findDateTask(id)) < 0) {
			return "Berhasil !";
		} else {
			System.out.println("How to get here?");
		}
		return "";
	}

}
