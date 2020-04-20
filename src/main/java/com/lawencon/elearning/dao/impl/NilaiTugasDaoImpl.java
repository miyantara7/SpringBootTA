package com.lawencon.elearning.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.NilaiTugasDao;
import com.lawencon.elearning.model.NilaiTugas;

@Repository
public class NilaiTugasDaoImpl extends BaseHibernate implements NilaiTugasDao {

	@Override
	public NilaiTugas insert(NilaiTugas nilai) throws Exception {
		em.persist(nilai);
		return nilai;
	}

	@Override
	public NilaiTugas findByUserPengajarTopic(String user, String pengajar, String materi) throws Exception {
		Query q = em.createQuery(
				"from NilaiTugas n where n.user.userId = :user and n.pengajar.pengajarId = :pengajar and n.materi.id = :materi");
		q.setParameter("user", user).setParameter("pengajar", pengajar).setParameter("materi", materi);
		return (NilaiTugas) q.getSingleResult();
	}

	@Override
	public NilaiTugas findById(String id) throws Exception {
		Query q = em.createQuery("from NilaiTugas where id = :id").setParameter("id", id);
		return (NilaiTugas) q.getSingleResult();
	}

	@Override
	public NilaiTugas update(NilaiTugas nilai) throws Exception {
		em.merge(nilai);
		return nilai;
	}

}
