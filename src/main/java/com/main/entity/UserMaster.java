package com.main.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.jsf.FacesContextUtils;

import com.main.util.ConstantsUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_MASTER")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen1")
    @SequenceGenerator(name = "user_gen1", sequenceName = "user_seq1", initialValue = 1, allocationSize = 1)
	private Integer userId;
	
	@Column(name = "FULLNAME", length = 20)
    private String fullName;
	
	@Column(name = "EMAIL", length = 30)
	private String email;
	
	@Column(name="MOBILE")
	private Long mobile;
	
	@Column(name="GENDER")
	private Character gender;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="PASSWORD", length = 30)
	private String password;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name = "MODIFIED_DATE", insertable = false)
	private LocalDate modifiedDate;
	
	@Column(name = "CREATED_BY", length = 20)
	private String createdBy;
	
	@Column(name = "MODIFIED_BY", length = 20)
	private String modifiedBy;
	
	@Column(name = "STATUS")
	private Character recordStatus=ConstantsUtil.STATUS;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Character getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Character recordStatus) {
		this.recordStatus = recordStatus;
	}
}
