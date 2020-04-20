package com.lawencon.elearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.dao.FileMateriDao;
import com.lawencon.elearning.dao.MateriHeaderDao;
import com.lawencon.elearning.dao.MateriPengajarDao;
import com.lawencon.elearning.model.FileMateri;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.MateriHeader;
import com.lawencon.elearning.model.MateriPengajar;

import javassist.expr.NewArray;

@Service
@Transactional
public class FileMateriServiceImpl implements FileMateriService {

	@Autowired
	@Qualifier("materi_repo_hibernate")
	private FileMateriDao fileMateriService;

	@Autowired
	private MateriHeaderDao headerService;

	@Autowired
	private MateriPengajarDao materiPengajarService;

	@Override
	public FileMateri insertMateri(MultipartFile file, String topic, String pId, String cId, String name, String hari,
			String tanggal, String waktu, String kelas) throws Exception {
		FileMateri materi = new FileMateri();
		materi.setJudulMateri(name);
		materi.setTypeFile(file.getContentType());
		materi.setFileMateri(file.getBytes());
		materi.setActiveFlag('Y');
		MateriPengajar mp = null;
		MateriHeader mHeader = null;
		MateriHeader mHeader2 = new MateriHeader();
		Materi cat = new Materi();
		List<MateriHeader> list = new ArrayList<MateriHeader>();
		try {
			list = headerService.findByTopicCategoryTrainer(topic, cId, pId, kelas);
			System.out.println(list.get(0).getHeaderId());
		} catch (Exception e) {
		
		}
		if (list.size() == 0) {
			cat.setId(cId);
			mHeader2.setTopic(topic.toUpperCase());
			mHeader2.setHari(hari.toUpperCase());
			mHeader2.setTanggal(tanggal);
			mHeader2.setWaktu(waktu);
			try {
				mp = materiPengajarService.findByMateriPengajarKelas(cId, pId, kelas);
			} catch (Exception e) {
			}
			mHeader2.setMateriPengajar(mp);
			materi.setHeader(headerService.insertHeader(mHeader2));
			return fileMateriService.insertMateri(materi);
		} else {
			mHeader2.setHeaderId(list.get(0).getHeaderId());
			materi.setHeader(mHeader2);
			return fileMateriService.insertMateri(materi);
		}
	}

	@Override
	public List<Map<String, Object>> findMateri(String category, String pengajar, String topik, String id)
			throws Exception {
		return fileMateriService.findMateri(category, pengajar, topik, id);
	}

	@Override
	public List<Map<String, Object>> findByCategory(String category) throws Exception {
		return fileMateriService.findByCategory(category);
	}

	@Override
	public List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception {
		return fileMateriService.findByCategoryAndTrainer(category, trainer);
	}

	@Override
	public List<Map<String, Object>> findJumMateri(String category, String pengajar, String topik) throws Exception {
		return fileMateriService.findJumMateri(category, pengajar, topik);
	}

	@Override
	public List<String> findTopic(String materiId, String pengajarId) throws Exception {
		LocalDate currentDate = LocalDate.now();
		LocalTime timeNow = LocalTime.now();
		DayOfWeek dayNow = currentDate.getDayOfWeek();
		String tgl = String.valueOf(currentDate);
		String hari = String.valueOf(dayNow);
		String waktu = String.valueOf(timeNow);
		return fileMateriService.findTopic(materiId, pengajarId, hari.toUpperCase(), tgl, waktu);
	}

}
