package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileMateriDao;
import com.lawencon.elearning.model.FileMateri;

@Repository("materi_repo_hibernate")
public class FileMateriDaoImpl extends BaseHibernate implements FileMateriDao {

	@Override
	public FileMateri insertMateri(FileMateri materi) throws Exception {
		em.persist(materi);
		return materi;
	}

	// download materi perubahan
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMateri(String category, String pengajar, String topik, String id)
			throws Exception {
		Query q = em.createQuery(
				"select fm.judulMateri, fm.fileMateri, fm.typeFile from FileMateri fm join fm.header th where th.topic =: tParam and th.materi.id =: mParam and th.pengajar.pengajarId =: pParam and fm.id =: idParam");
		q.setParameter("tParam", topik);
		q.setParameter("mParam", category);
		q.setParameter("pParam", pengajar);
		q.setParameter("idParam", id);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "judul", "file", "type");
	}

	// view judulmateri
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findJumMateri(String materi, String pengajar, String topik) throws Exception {
		Query q = em.createQuery(
				"select fm.judulMateri, th.topic, th.materi.id, th.pengajar.pengajarId, fm.id from FileMateri fm join fm.header th where fm.header.headerId =: tParam and th.materi.id =: mParam and th.pengajar.pengajarId =: pParam");
		q.setParameter("tParam", topik);
		q.setParameter("mParam", materi);
		q.setParameter("pParam", pengajar);
		return bMapperHibernate(q.getResultList(), "judul_materi", "topic", "materi_id", "pengajar_id", "id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByCategory(String category) throws Exception {
		Query q = em.createQuery(
				"select m.judulMateri as judul, m.fileMateri as file, m.typeFile as type from FileMateri m left join m.materi as c where c.id = :category");
		q.setParameter("category", category);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "judul", "file", "type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception {
		Query q = em.createQuery(
				"select m.judulMateri as judul, m.fileMateri as file, m.typeFile as type from FileMateri m left join m.materi as c "
						+ "left outer join m.pengajar as trainer where c.id =: category  and trainer.id =: pengajar ");
		q.setParameter("category", category);
		q.setParameter("pengajar", trainer);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "judul", "file", "type");
	}

	// view topic
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopic(String materiId, String pengajarId) throws Exception {
		Query q = em
				.createNativeQuery(
						"select headerid,topic, hari from tb_header_materi where materi_id = ? and pengajar_id = ?")
				.setParameter(1, materiId).setParameter(2, pengajarId);
		return bMapperHibernate(q.getResultList(), "headerid", "topic", "hari");
	}

//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Materi> findAllActive(String topik) throws Exception {
//		Query q = em
//				.createNativeQuery(
//						"select judul_materi,id,day, week from tb_materi where  and activeflag = ?")
//				.setParameter(1, week).setParameter(2, day).setParameter(3, ActiveFlag.ACTIVE.name());
//		return bMapperHibernate(q.getResultList(), "judul_materi", "id", "day", "week");
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileMateri> findAll() throws Exception {
		Query q = em.createQuery(" from MateriHeader");
		return q.getResultList();
	}

	// view topic
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopic(String materiId, String pengajarId, String hari, String tgl, String waktu)
			throws Exception {
		Query q = em.createNativeQuery(
				"select headerid,topic, hari from tb_header_materi where materi_id = ? and pengajar_id = ? and hari = ? and tanggal = ? and jam <= ?")
				.setParameter(1, materiId).setParameter(2, pengajarId).setParameter(3, hari).setParameter(4, tgl)
				.setParameter(5, waktu);
		return bMapperHibernate(q.getResultList(), "headerid", "topic", "hari");
	}
}
