package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmotionLogDto;
import com.app.entity.EmotionLog;
import com.app.services.EmotionService;
import com.app.viewmodel.LineChartViewModel;
import com.app.viewmodel.PieChartViewModel;

@RestController
@RequestMapping("/Emotion")
@CrossOrigin
public class EmotionLogController {

	@Autowired
	private EmotionService emotionService;

	@PostMapping("/add")
	public boolean addEntry(@RequestBody EmotionLogDto entry) {
		var result = emotionService.LogEmotionData(entry);
		return result;
	}

	@GetMapping("/lineChart")
	public LineChartViewModel getLineChartData() {
		var result = emotionService.GetLineChartData();
		return result;
	}

	@GetMapping("/findByDate")
	public EmotionLog getLogByDate(@RequestParam String date) {
		var result = emotionService.findByDate(date);
		return result;
	}

	@GetMapping("/HappyPieChart")
	public PieChartViewModel getHappyPieChartData() {
		var result = emotionService.GetHappyPieChart();
		return result;
	}

	@GetMapping("/SadPieChart")
	public PieChartViewModel getSadPieChartData() {
		var result = emotionService.GetSadPieChart();
		return result;
	}
}
