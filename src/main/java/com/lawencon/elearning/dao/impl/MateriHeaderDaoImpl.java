package com.lawencon.elearning.dao.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.MateriHeaderDao;
import com.lawencon.elearning.model.MateriHeader;

@Repository
public class MateriHeaderDaoImpl extends BaseHibernate implements MateriHeaderDao {

	// TAMBAHIN JOIN
	@Override
	public List<MateriHeader> findByTopicCategoryTrainer(String topik, String category, String trainer, String kelas)
			throws Exception {
		System.out.println(topik);
//		Query q = em.createQuery("select mh.headerId from MateriHeader mh left join mh.materiPengajar mp where mh.topic =: tParam and mp.pengajar.pengajarId =: pParam and mp.materi.id =: cParam and mp.kelasPengajar.id = :kelas")
//				.setParameter("tParam", topik)
//				.setParameter("pParam", trainer)
//				.setParameter("cParam", category).setParameter("kelas", kelas);
		Query q = em.createNativeQuery(
				"select * from tb_header_materi mh join tb_materi_pengajar as mp on mh.materipengajar_id = mp.id where mh.topic = ? and mp.materi_id = ? and mp.pengajar_id = ? and mp.kelas_id = ?")
				.setParameter(1, topik).setParameter(2, category).setParameter(3, trainer).setParameter(4, kelas);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MateriHeader> findAll() throws Exception {
		Query q = em.createQuery(" from MateriHeader");
		return q.getResultList();
	}

	@Override
	public MateriHeader insertHeader(MateriHeader materiHeader) throws Exception {
		em.persist(materiHeader);
		return materiHeader;
	}

	@Override
	public void deleteHeader(String id) throws Exception {
		Query q = em.createQuery(" from MateriHeader where id = :id").setParameter("id", id);
		MateriHeader h = (MateriHeader) q.getSingleResult();
		em.remove(h);

	}

}
