package com.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Past;

public class EmotionLogDto {
	
    @Past(message = "The date can not be in future")
	private String date;
	private int feelGoodScore;
	@Max(300)
	private String happyDescription;
	@Max(300)
	private String sadDescription;

	public EmotionLogDto(String date, int feelGoodScore, String happyDescription,
			String sadDescription) {
		this.date = date;
		this.feelGoodScore = feelGoodScore;
		this.happyDescription = happyDescription;
		this.sadDescription = sadDescription;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
