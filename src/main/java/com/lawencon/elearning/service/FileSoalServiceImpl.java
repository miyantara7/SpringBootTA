package com.lawencon.elearning.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.dao.FileSoalDao;
import com.lawencon.elearning.model.FileSoal;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.Pengajar;

@Service
@Transactional
public class FileSoalServiceImpl implements FileSoalService {

	@Autowired
	private FileSoalDao fileSoalService;

	@Override
	public FileSoal insert(MultipartFile file, String materi, String pengajar, String jenis, Date startDate,
			Date endDate, String day) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileSoal fileSoal = null;
		FileSoal fileNew = new FileSoal();
		Materi m = new Materi();
		Pengajar p = new Pengajar();
		fileNew.setFile(file.getBytes());
		fileNew.setFileName(fileName);
		fileNew.setFileType(file.getContentType());
		try {
			fileSoal = fileSoalService.findByMateriPengajar(pengajar, materi);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (fileSoal != null) {
			fileSoal.setFile(file.getBytes());
			fileSoal.setFileType(file.getContentType());
			fileSoal.setFileName(fileName);
			return fileSoalService.update(fileSoal);
		}
		if (jenis.equalsIgnoreCase(JenisFile.TUGAS.name())) {
			fileNew.setJenis(JenisFile.TUGAS.name());
		}
		if (jenis.equalsIgnoreCase(JenisFile.UJIAN.name())) {
			fileNew.setJenis(JenisFile.UJIAN.name());
		}
		fileNew.setStartDate(startDate);
		fileNew.setEndDate(endDate);
		p.setPengajarId(pengajar);
		m.setId(materi);
		fileNew.setDay(day.toUpperCase());
		fileNew.setPengajar(p);
		fileNew.setMateri(m);
		return fileSoalService.insert(fileNew);
	}

	@Override
	public FileSoal findById(String id) throws Exception {
		return fileSoalService.findById(id);
	}

}
