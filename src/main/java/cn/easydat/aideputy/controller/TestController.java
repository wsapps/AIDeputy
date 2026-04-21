package cn.easydat.aideputy.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class TestController {

	private final ChatClient chatClient;
	
	public TestController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping(value = "/stream", produces = "text/event-stream;charset=UTF-8")
	public Flux<String> streamChat(@RequestParam String prompt) {
		return chatClient.prompt(prompt).stream().content();
	}
	
	@GetMapping(value = "/chat")
	public String chat(@RequestParam String prompt) {
		return chatClient.prompt(prompt).call().content();
	}

}
