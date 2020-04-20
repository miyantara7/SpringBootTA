package com.lawencon.elearning.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.NilaiTugasDao;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.NilaiTugas;
import com.lawencon.elearning.model.Pengajar;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class NilaiTugasServiceImpl implements NilaiTugasService {

	@Autowired
	private NilaiTugasDao nilaiTugasService;

	@Override
	public NilaiTugas insert(int nilai, String pengajar, String user, String materi) throws Exception {
		NilaiTugas cekNilai = null;
		NilaiTugas nilaiTugas = new NilaiTugas();
		try {
			cekNilai = nilaiTugasService.findByUserPengajarTopic(user, pengajar, materi);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (cekNilai != null) {
			cekNilai.setNilai(nilai);
			return nilaiTugasService.update(cekNilai);
		}
		nilaiTugas.setNilai(nilai);
		Pengajar p = new Pengajar();
		Materi m = new Materi();
		Users u = new Users();
		p.setPengajarId(pengajar);
		m.setId(materi);
		u.setUserId(user);
		nilaiTugas.setMateri(m);
		nilaiTugas.setPengajar(p);
		nilaiTugas.setUser(u);
		return nilaiTugasService.insert(nilaiTugas);
	}

}
