package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.KelasDao;
import com.lawencon.elearning.model.KelasUsers;
@Repository
public class KelasDaoImpl extends BaseHibernate implements KelasDao{
	@Override
	public KelasUsers findClass(String mId,String pId,String uId) throws Exception {
		Query q = em.createQuery("from KelasUsers k where k.pengajar.pengajarId =: pParam and k.materi.id =: mParam and k.users.userId =: uParam")
				.setParameter("pParam", pId)
				.setParameter("mParam", mId)
				.setParameter("uParam", uId);
		return (KelasUsers) q.getSingleResult();
	}


	@Override
	public KelasUsers insertKelas(KelasUsers kelas) throws Exception {
		em.persist(kelas);
		return kelas;
	}


	@Override
	public List<Map<String, Object>> listKelas(String mId, String pId) throws Exception {
		Query q = em.createNativeQuery("select tp.id , tp.namakelas from tb_materi_pengajar tmp "
				+ "join tb_class_pengajar tp on tmp.kelas_id  = tp.id where tmp.pengajar_id = ? and tmp.materi_id = ?")
				.setParameter(1, pId)
				.setParameter(2, mId);
		return bMapperHibernate(q.getResultList(), "kelas_id", "nama_kelas");
	}
}
