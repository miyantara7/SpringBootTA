package com.lawencon.elearning.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.NilaiUjianDao;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.NilaiUjian;
import com.lawencon.elearning.model.Pengajar;
import com.lawencon.elearning.model.Users;
@Service
@Transactional
public class NilaiUjianServiceImpl implements NilaiUjianService {
	
	@Autowired
	private NilaiUjianDao nilaiUjianService;

	@Override
	public NilaiUjian insert(int nilai, String user, String pengajar, String materi) throws Exception {
		NilaiUjian cekNilai = null;
		NilaiUjian nilaiUjian = new NilaiUjian();
		Pengajar p = new Pengajar();
		Materi m = new Materi();
		Users u = new Users();
		try {
			cekNilai = nilaiUjianService.findByUserPengajarMateri(user, materi, pengajar);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (cekNilai != null) {
			cekNilai.setNilaiUjian(nilai);
			return nilaiUjianService.update(cekNilai);
		}
		p.setPengajarId(pengajar);
		m.setId(materi);
		u.setUserId(user);
		nilaiUjian.setMateri(m);
		nilaiUjian.setPengajar(p);
		nilaiUjian.setUser(u);
		return nilaiUjianService.insert(nilaiUjian);
	}

}
