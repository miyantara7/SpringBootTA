package com.lawencon.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_materi_pengajar")
public class MateriPengajar {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	@ManyToOne
	@JoinColumn(name = "pengajar_id")
	private Pengajar pengajar;
	@ManyToOne
	@JoinColumn(name = "materi_id")
	private Materi materi;
	@ManyToOne
	@JoinColumn(name = "kelas_id")
	private KelasPengajar kelasPengajar;
	private String jam;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Materi getMateri() {
		return materi;
	}

	public void setMateri(Materi materi) {
		this.materi = materi;
	}

	public Pengajar getPengajar() {
		return pengajar;
	}

	public void setPengajar(Pengajar pengajar) {
		this.pengajar = pengajar;
	}

	public KelasPengajar getKelasPengajar() {
		return kelasPengajar;
	}

	public void setKelasPengajar(KelasPengajar kelasPengajar) {
		this.kelasPengajar = kelasPengajar;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

}
