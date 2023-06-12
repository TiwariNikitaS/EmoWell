package com.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class EmotionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Past(message = "The date can not be in future")
	private Date date;

	private int feelGoodScore;

	@Size(max = 300)
	private String happyDescription;

	@Size(max = 300)
	private String sadDescription;

	@Size(max = 200)
	private String happyShortDescription;

	public String getHappyShortDescription() {
		return happyShortDescription;
	}

	public void setHappyShortDescription(String happyShortDescription) {
		this.happyShortDescription = happyShortDescription;
	}

	@Size(max = 200)
	private String sadShortDescription;

	public String getSadShortDescription() {
		return sadShortDescription;
	}

	public void setSadShortDescription(String sadShortDescription) {
		this.sadShortDescription = sadShortDescription;
	}

	public EmotionLog(int id, Date date, int feelGoodScore, String happyDescription, String sadDescription) {
		super();
		this.id = id;
		this.date = date;
		this.feelGoodScore = feelGoodScore;
		this.happyDescription = happyDescription;
		this.sadDescription = sadDescription;
	}

	public EmotionLog() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getFeelGoodScore() {
		return feelGoodScore;
	}

	public void setFeelGoodScore(int feelGoodScore) {
		this.feelGoodScore = feelGoodScore;
	}

	public String getHappyDescription() {
		return happyDescription;
	}

	public void setHappyDescription(String happyDescription) {
		this.happyDescription = happyDescription;
	}

	public String getSadDescription() {
		return sadDescription;
	}

	public void setSadDescription(String sadDescription) {
		this.sadDescription = sadDescription;
	}

}
