package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.MateriPengajarDao;
import com.lawencon.elearning.model.MateriPengajar;

@Repository
public class MateriPengajarDaoImpl extends BaseHibernate implements MateriPengajarDao {

	@Override
	public MateriPengajar insert(MateriPengajar materiPengajar) throws Exception {
		em.persist(materiPengajar);
		return materiPengajar;
	}

	@Override
	public MateriPengajar update(MateriPengajar materiPengajar) throws Exception {
		em.merge(materiPengajar);
		return materiPengajar;
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery(" from MateriPengajar where id = :id").setParameter("id", id);
		MateriPengajar mp = (MateriPengajar) q.getSingleResult();
		em.remove(mp);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MateriPengajar> findAll() throws Exception {
		Query q = em.createQuery(" from MateriPelajar");
		return q.getResultList();
	}

	@Override
	public MateriPengajar findById(String id) throws Exception {
		Query q = em.createQuery(" from MateriPengajar where id = :id").setParameter("id", id);
		MateriPengajar mp = (MateriPengajar) q.getSingleResult();
		return mp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMateri(String idMateri) throws Exception {
		Query q = em.createQuery(
				"select mp.materi.id as id , p.name as nama, kp.namaKelas from MateriPengajar mp join mp.pengajar p join mp.kelasPengajar kp where mp.materi.id =:id");
		q.setParameter("id", idMateri);
		return bMapperHibernate(q.getResultList(), "pengajar_id", "nama", "kelas");
//		return q.getResultList();
	}

	@Override
	public MateriPengajar findByMateriPengajarKelas(String materi, String pengajar, String kelas) throws Exception {
		Query q = em.createQuery(
				"from MateriPengajar m where m.kelasPengajar.id = :kelas and m.materi.id = :materi and m.pengajar.pengajarId = :pengajar")
				.setParameter("kelas", kelas).setParameter("pengajar", pengajar).setParameter("materi", materi);
		return (MateriPengajar) q.getSingleResult();
	}

}
