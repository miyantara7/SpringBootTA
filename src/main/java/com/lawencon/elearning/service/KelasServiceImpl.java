package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.KelasDao;
import com.lawencon.elearning.model.KelasUsers;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.Pengajar;
import com.lawencon.elearning.model.Users;
@Service
@Transactional
public class KelasServiceImpl implements KelasService {
	@Autowired
	private KelasDao kelasService;

	@Override
	public boolean findClass(String uId,String mId, String pId) throws Exception {
		KelasUsers kelas = null;
		try {
			kelas = kelasService.findClass(mId, pId, uId);
		} catch (Exception e) {
		}
		if (kelas == null) {
			return true;
		} else {
			return false;
		}

	}

	// Kelas
	@Override
	public KelasUsers insertKelas(String mId, String pId, String uId) throws Exception {
		KelasUsers kelas = new KelasUsers();
		Materi materi = new Materi();
		Users user = new Users();
		Pengajar pengajar = new Pengajar();
		pengajar.setPengajarId(pId);
		materi.setId(mId);
		user.setId(uId);
		kelas.setMateri(materi);
		kelas.setUsers(user);
		kelas.setPengajar(pengajar);
		return kelasService.insertKelas(kelas);
	}

	@Override
	public List<Map<String, Object>> listKelas(String mId, String pId) throws Exception {
		return kelasService.listKelas(mId, pId);
	}
	
	

}
