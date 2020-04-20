package com.lawencon.elearning.dao.impl;

import java.sql.Date;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileSoalDao;
import com.lawencon.elearning.model.FileSoal;

@Repository
public class FileSoalDaoImpl extends BaseHibernate implements FileSoalDao {

	@Override
	public FileSoal insert(FileSoal file) throws Exception {
		em.persist(file);
		return file;
	}

	@Override
	public FileSoal findByMateriPengajar(String pengajar, String materi) throws Exception {
		Query q = em.createQuery("from FileSoal f where f.materi.id = :materi and f.pengajar.pengajarId = :pengajar");
		q.setParameter("mater", materi).setParameter("pengajar", pengajar);
		return (FileSoal) q.getSingleResult();
	}

	@Override
	public FileSoal update(FileSoal file) throws Exception {
		em.merge(file);
		return file;
	}

	@Override
	public FileSoal findById(String id) throws Exception {
		Query q = em.createQuery("from FileSoal where id = :id").setParameter("id", id);
		return (FileSoal) q.getSingleResult();
	}

	@Override
	public Date findDateTask(String id) throws Exception {
		Query q = em.createNativeQuery("select end_date from tb_file_soal tfu where materi_id = ?").setParameter(1, id);
		return (Date) q.getSingleResult();
	}

}
