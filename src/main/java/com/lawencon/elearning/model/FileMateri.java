package com.lawencon.elearning.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_file_materi")
public class FileMateri {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String id;
	private String judulMateri;
	private Date endDate;
	@Lob
	private byte[] fileMateri;
	private String typeFile;
	@Column(nullable = true)
	private char activeFlag;
	@ManyToOne
	@JoinColumn(name = "header_id")
	private MateriHeader header;

	public MateriHeader getHeader() {
		return header;
	}

	public void setHeader(MateriHeader header) {
		this.header = header;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJudulMateri() {
		return judulMateri;
	}

	public void setJudulMateri(String judulMateri) {
		this.judulMateri = judulMateri;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte[] getFileMateri() {
		return fileMateri;
	}

	public void setFileMateri(byte[] fileMateri) {
		this.fileMateri = fileMateri;
	}

	public String getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	public char getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(char activeFlag) {
		this.activeFlag = activeFlag;
	}

}
