package com.lawencon.elearning.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.KelasPengajarDao;
import com.lawencon.elearning.model.KelasPengajar;

@Repository
public class KelasPengajarDaoImpl extends BaseHibernate implements KelasPengajarDao {

	@Override
	public KelasPengajar insert(KelasPengajar kelas) throws Exception {
		em.persist(kelas);
		return kelas;
	}

	@Override
	public KelasPengajar update(KelasPengajar kelas) throws Exception {
		em.merge(kelas);
		return kelas;
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery("from KelasPengajar where id = :id").setParameter("id", id);
		KelasPengajar kelas = (KelasPengajar) q.getSingleResult();
		em.remove(kelas);
	}

	@Override
	public KelasPengajar findById(String id) throws Exception {
		Query q = em.createQuery("from KelasPengajar where id = :id").setParameter("id", id);
		return (KelasPengajar) q.getSingleResult();
	}

}
