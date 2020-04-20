package com.lawencon.elearning.dao.impl;

import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.AbsenDao;
import com.lawencon.elearning.model.Absen;

@Repository
public class AbsenDaoImpl extends BaseHibernate implements AbsenDao {

	@Override
	public Absen insert(Absen absen) throws Exception {
		em.persist(absen);
		return absen;
	}

	@Override
	public Absen update(Absen absen) throws Exception {
		em.merge(absen);
		return absen;
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery("from Absen where id = :id").setParameter("id", id);
		Absen absen = (Absen) q.getSingleResult();
		em.remove(absen);
	}

	@Override
	public Absen findByUserMateriPengajar(String user, String materi, String pengajar, Date date) throws Exception {
		Query q = em.createQuery(
				" from Absen a where a.user.userId =:user and a.pengajar.pengajarId = :pengajar and a.materi.id = :materi and a.date = :date");
		q.setParameter("user", user).setParameter("pengajar", pengajar).setParameter("materi", materi).setParameter("date", date);
		return (Absen) q.getSingleResult();
	}

}
