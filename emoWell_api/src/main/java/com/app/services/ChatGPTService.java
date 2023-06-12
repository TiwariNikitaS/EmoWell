package com.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

@Component
public class ChatGPTService {

	@Value("${api.key}")
	private String apiKey;

	public HashMap<String, String> GetShortDescription(String happyText, String sadText) {

		HashMap<String, String> result = new HashMap<>();

		OpenAiService service = new OpenAiService(apiKey);

		final List<ChatMessage> messages = new ArrayList<>();
		final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),
				"I will give you things which made me happy or sad with format, H - {Happy description} S - {Sad description}. You have to analyze the description and give me the actual action or thing which made me happy or sad. example :- 1. H - {I went for the walk with my dog, it was very good feeling. I felt like someone is with me}, H|Dog Walking. 2. H - {I wathced ramayan today, it gave me immense peace and pleasure}, H|Watching Ramayan. 3. S - {Today I ate my food with delay, it was very cold and I didnt enjoyed it. my mood went off}, S|Not eating food on time. 4. H - {I ate icecream} S - {Today there was a celebration in neighbours house but they were playing very loud music, which disturbed me a lot}, H|Eating Icecream S|Loud noise. Answer only with your response nothing else. prefix responses with H| for happy and S| for sad and the response for happy and sad should be precise and not more than 7 words. H - {"
						+ happyText + "} S - { " + sadText + "} ?");
		messages.add(systemMessage);
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo")
				.messages(messages).n(1).maxTokens(50).logitBias(new HashMap<>()).build();

		String response = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage()
				.getContent();

		response = response.replaceAll("\\n", "");
		if (happyText != null && !happyText.isBlank()) {
			if (sadText == null || sadText.isBlank()) {
				result.put("H", response.replaceAll("H\\|", "").trim());
			} else {
				String[] parts = response.split("S\\|");
				result.put("H", parts[0].replaceAll("H\\|", "").trim());
				result.put("S", parts[1].replaceAll("S\\|", "").trim());
			}
		} else {
			result.put("S", response.replaceAll("S\\|", "").trim());
		}

		return result;
	}

	public HashMap<String, Integer> GetCategories(String combinedCategories) {

		OpenAiService service = new OpenAiService(apiKey);

		final List<ChatMessage> messages = new ArrayList<>();
		final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),
				"I am giving you these actions or things, you need to group these into appropriate categories and give me category name and count."
						+ combinedCategories
						+ ". give response in format category,count. separate each category with pipe in single text .Example response :- Religious Activities,1|Physical Activities,3|Fitness Activities,3. Dont give any explaination just give category and count text in response in one line");
		messages.add(systemMessage);
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo")
				.messages(messages).n(1).maxTokens(50).logitBias(new HashMap<>()).build();

		String response = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage()
				.getContent();

		response = response.replaceAll("\\n", "").trim();
		String[] categories = response.split("\\|");
		HashMap<String, Integer> catCounts = new HashMap<>();
		for (String category : categories) {
			String[] catCount = category.split(",");
			catCounts.put(catCount[0].trim(), Integer.valueOf(catCount[1].trim().replaceAll("\\D+", "")));
		}
		return catCounts;
	}

}
