package com.app.viewmodel;

import java.util.List;

public class PieChartViewModel {

	private List<String> Labels;
	private List<Integer> Count;
	private List<String> Colors;

	public PieChartViewModel(List<String> labels, List<Integer> count, List<String> colors) {
		super();
		Labels = labels;
		Count = count;
		Colors = colors;
	}

	public List<String> getLabels() {
		return Labels;
	}

	public void setLabels(List<String> labels) {
		Labels = labels;
	}

	public List<Integer> getCount() {
		return Count;
	}

	public void setCount(List<Integer> count) {
		Count = count;
	}

	public List<String> getColors() {
		return Colors;
	}

	public void setColors(List<String> colors) {
		Colors = colors;
	}

}
