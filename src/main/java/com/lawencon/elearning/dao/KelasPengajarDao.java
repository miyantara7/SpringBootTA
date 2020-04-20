package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.KelasPengajar;

public interface KelasPengajarDao {
	abstract KelasPengajar insert(KelasPengajar kelas) throws Exception;

	abstract KelasPengajar update(KelasPengajar kelas) throws Exception;

	abstract void delete(String id) throws Exception;

	abstract KelasPengajar findById(String id) throws Exception;
}
