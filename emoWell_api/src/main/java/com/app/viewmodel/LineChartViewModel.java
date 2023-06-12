package com.app.viewmodel;

import java.util.List;

public class LineChartViewModel {
	private List<String> Dates;
	private List<Integer> FeelGoodScores;
	
	public LineChartViewModel(List<String> dates, List<Integer> feelGoodScores) {
		super();
		Dates = dates;
		FeelGoodScores = feelGoodScores;
	}

	
	public List<String> getDates() {
		return Dates;
	}
	public void setDates(List<String> dates) {
		Dates = dates;
	}
	public List<Integer> getFeelGoodScores() {
		return FeelGoodScores;
	}
	public void setFeelGoodScores(List<Integer> feelGoodScores) {
		FeelGoodScores = feelGoodScores;
	}
}
