package com.app.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.EmotionLogDto;
import com.app.entity.EmotionLog;
import com.app.repository.EmotionLogRepository;
import com.app.viewmodel.LineChartViewModel;
import com.app.viewmodel.PieChartViewModel;

@Service
public class EmotionServiceImpl implements EmotionService {

	@Autowired
	private EmotionLogRepository emotionLogRepository;
	@Autowired
	private ChatGPTService chatGptService;

	public EmotionServiceImpl() {

	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat LineChartDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public boolean LogEmotionData(EmotionLogDto dto) {
		try {
			EmotionLog entity = findByDate(dto.getDate());
			if (entity == null) {
				entity = new EmotionLog();
			}
			entity.setDate(format.parse(dto.getDate()));
			entity.setFeelGoodScore(dto.getFeelGoodScore());
			entity.setSadDescription(dto.getSadDescription());
			entity.setHappyDescription(dto.getHappyDescription());

			// if both description are empty or null directly save it without chatGpt API
			if ((dto.getHappyDescription() == null || dto.getHappyDescription().isBlank())
					&& (dto.getSadDescription() == null || dto.getSadDescription().isBlank())) {
				emotionLogRepository.save(entity);
			} else {
				HashMap<String, String> chatResponse = chatGptService.GetShortDescription(dto.getHappyDescription(),
						dto.getSadDescription());
				entity.setHappyShortDescription(chatResponse.get("H"));
				entity.setSadShortDescription(chatResponse.get("S"));
				emotionLogRepository.save(entity);
			}

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public LineChartViewModel GetLineChartData() {
		try {
			List<EmotionLog> logs = emotionLogRepository.findByOrderByDateAsc();

			List<String> dates = logs.stream().map(o -> LineChartDateFormat.format(o.getDate()))
					.collect(Collectors.toList());

			List<Integer> scores = logs.stream().map(o -> o.getFeelGoodScore()).collect(Collectors.toList());

			return new LineChartViewModel(dates, scores);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public EmotionLog findByDate(String date) {
		try {

			EmotionLog obj = emotionLogRepository.findByDate(format.parse(date));
			if (obj == null) {
				System.out.println("No data found for the data" + date);
			}
			return obj;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PieChartViewModel GetHappyPieChart() {

		List<String> happinessContributers = emotionLogRepository.findAll().stream()
				.filter(e -> e.getHappyShortDescription() != null && !e.getHappyShortDescription().isBlank())
				.map(e -> e.getHappyShortDescription()).collect(Collectors.toList());

		var categories = chatGptService.GetCategories(ConvertToPrompString(happinessContributers));
		var topTenCategories = categories.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
				.limit(10).collect(Collectors.toList());

		var colors = generateUniqueColors(topTenCategories.size());
		var labels = topTenCategories.stream().map(c -> c.getKey()).collect(Collectors.toList());
		var values = topTenCategories.stream().map(c -> c.getValue()).collect(Collectors.toList());

		System.out.println(topTenCategories);
		return new PieChartViewModel(labels, values, colors);

	}

	@Override
	public PieChartViewModel GetSadPieChart() {
		// TODO Auto-generated method stub
		List<String> unhappinessContributers = emotionLogRepository.findAll().stream()
				.filter(e -> e.getSadShortDescription() != null && !e.getSadShortDescription().isBlank())
				.map(e -> e.getSadShortDescription()).collect(Collectors.toList());

		var categories = chatGptService.GetCategories(ConvertToPrompString(unhappinessContributers));
		var topTenCategories = categories.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
				.limit(10).collect(Collectors.toList());

		var colors = generateUniqueColors(topTenCategories.size());
		var labels = topTenCategories.stream().map(c -> c.getKey()).collect(Collectors.toList());
		var values = topTenCategories.stream().map(c -> c.getValue()).collect(Collectors.toList());

		return new PieChartViewModel(labels, values, colors);
	}

	private String ConvertToPrompString(List<String> happinessContributers) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < happinessContributers.size(); i++) {
			sb.append(i + 1).append(".").append(happinessContributers.get(i));
			if (i < happinessContributers.size() - 1) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	private static List<String> generateUniqueColors(int count) {
		List<String> colors = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < count; i++) {
			int red = random.nextInt(256);
			int green = random.nextInt(256);
			int blue = random.nextInt(256);
			String color = "rgb(" + red + ", " + green + ", " + blue + ")";
			colors.add(color);
		}

		return colors;
	}

}
