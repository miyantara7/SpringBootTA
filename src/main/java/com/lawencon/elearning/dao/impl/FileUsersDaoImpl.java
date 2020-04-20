package com.lawencon.elearning.dao.impl;

import java.sql.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.FileUsersDao;
import com.lawencon.elearning.model.FileUsers;
@Repository
public class FileUsersDaoImpl extends BaseHibernate implements FileUsersDao {

	@Override
	public FileUsers insert(FileUsers file) throws Exception {
		em.persist(file);
		return file;
	}

	@Override
	public FileUsers findByUserMateriPengajar(String user, String pengajar, String materi) throws Exception {
		Query q = em.createQuery("from FileUsers f where f.materi.id = :materi and f.pengajar.pengajarId = :pengajar and f.user.userId = :user");
		q.setParameter("mater", materi).setParameter("pengajar", pengajar).setParameter("user", user);
		return (FileUsers) q.getSingleResult();
	}

	@Override
	public FileUsers update(FileUsers file) throws Exception {
		em.merge(file);
		return file;
	}

	@Override
	public FileUsers findById(String id) throws Exception {
		Query q = em.createQuery("from FileUsers where id = :id").setParameter("id", id);
		return (FileUsers) q.getSingleResult();
	}

	@Override
	public Date findDateTask(String id) throws Exception {
		Query q = em.createNativeQuery("select end_date from tb_file_users tfu where materi_id = ?")
				.setParameter(1, id);
		return (Date) q.getSingleResult();
	}

}
