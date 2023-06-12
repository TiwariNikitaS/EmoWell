package com.app.services;

import com.app.dto.EmotionLogDto;
import com.app.entity.EmotionLog;
import com.app.viewmodel.LineChartViewModel;
import com.app.viewmodel.PieChartViewModel;

public interface EmotionService {
	boolean LogEmotionData(EmotionLogDto dto);

	LineChartViewModel GetLineChartData();

	EmotionLog findByDate(String date);

	PieChartViewModel GetHappyPieChart();

	PieChartViewModel GetSadPieChart();
}
