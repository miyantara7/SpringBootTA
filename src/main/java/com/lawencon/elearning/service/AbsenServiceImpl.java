package com.lawencon.elearning.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.AbsenDao;
import com.lawencon.elearning.model.Absen;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.Pengajar;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class AbsenServiceImpl implements AbsenService {

	@Autowired
	private AbsenDao absenService;

	@Override
	public Absen insert(String user, String pengajar, String materi, Date date) throws Exception {
		Absen absen = null;
		Absen ab = new Absen();
		try {
			absen = absenService.findByUserMateriPengajar(user, materi, pengajar, date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (absen != null) {
			throw new Exception("Anda Sudah Absen !");
		}
		ab.setDate(date);
		Materi m = new Materi();
		m.setId(materi);
		Pengajar p = new Pengajar();
		p.setPengajarId(pengajar);
		Users u = new Users();
		u.setId(user);
		ab.setMateri(m);
		ab.setPengajar(p);
		ab.setUser(u);
		return absenService.insert(ab);
	}

}
