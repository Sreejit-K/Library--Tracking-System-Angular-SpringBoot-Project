package com.beehyv.demo.Dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="FineTable")
public class Fine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fineId;
	
	@Column(columnDefinition = "integer default 5")
	@NotNull
	private Integer fineForFirstDay ;
	
    @Column(columnDefinition = "integer default 10") 
	@NotNull
	private Integer fineAfterFirstDay ;
	
	@Column(columnDefinition = "integer default 15") 
	@NotNull
	private Integer fineAfterFifthDay ;
	
	private Integer myvalue;
	
	
	public Fine() {
		super();
	}


	public Fine(Integer fineId, @NotNull Integer fineForFirstDay, @NotNull Integer fineAfterFirstDay,
			@NotNull Integer fineAfterFifthDay, Integer myvalue) {
		super();
		this.fineId = fineId;
		this.fineForFirstDay = fineForFirstDay;
		this.fineAfterFirstDay = fineAfterFirstDay;
		this.fineAfterFifthDay = fineAfterFifthDay;
		this.myvalue = myvalue;
	}


	public Integer getFineId() {
		return fineId;
	}


	public void setFineId(Integer fineId) {
		this.fineId = fineId;
	}


	public Integer getFineForFirstDay() {
		return fineForFirstDay;
	}


	public void setFineForFirstDay(Integer fineForFirstDay) {
		this.fineForFirstDay = fineForFirstDay;
	}


	public Integer getFineAfterFirstDay() {
		return fineAfterFirstDay;
	}


	public void setFineAfterFirstDay(Integer fineAfterFirstDay) {
		this.fineAfterFirstDay = fineAfterFirstDay;
	}


	public Integer getFineAfterFifthDay() {
		return fineAfterFifthDay;
	}


	public void setFineAfterFifthDay(Integer fineAfterFifthDay) {
		this.fineAfterFifthDay = fineAfterFifthDay;
	}


	public Integer getMyvalue() {
		return myvalue;
	}


	public void setMyvalue(Integer myvalue) {
		this.myvalue = myvalue;
	}


	@Override
	public String toString() {
		return "Fine [fineId=" + fineId + ", fineForFirstDay=" + fineForFirstDay + ", fineAfterFirstDay="
				+ fineAfterFirstDay + ", fineAfterFifthDay=" + fineAfterFifthDay + ", myvalue=" + myvalue + "]";
	}


	



	
}
