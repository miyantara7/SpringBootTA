package com.lawencon.elearning.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.NilaiUjianDao;
import com.lawencon.elearning.model.NilaiUjian;
@Repository
public class NilaiUjianDaoImpl extends BaseHibernate implements NilaiUjianDao {

	@Override
	public NilaiUjian insert(NilaiUjian nilai) throws Exception {
		em.persist(nilai);
		return nilai;
	}

	@Override
	public NilaiUjian update(NilaiUjian nilai) throws Exception {
		em.merge(nilai);
		return nilai;
	}

	@Override
	public NilaiUjian findByUserPengajarMateri(String user, String materi, String pengajar) throws Exception {
		Query q = em.createQuery(
				"from NilaiUjian n where n.user.userId = :user and n.materi.id = :materi and n.pengajar.pengajarId = :pengajar");
		q.setParameter("user", user).setParameter("pengajar", pengajar).setParameter("materi", materi);
		return (NilaiUjian) q.getSingleResult();
	}

}
