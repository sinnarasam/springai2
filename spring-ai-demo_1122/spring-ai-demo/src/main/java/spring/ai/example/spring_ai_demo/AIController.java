package spring.ai.example.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class AIController {
    private final ChatClient chatClient;

    AIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai")
    Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message,
            @RequestParam(value = "systemPrompt", defaultValue = "You are a helpful assistant.") String systemPrompt) {
        return Map.of(
                "completion",
                chatClient.prompt()
                        .system("너는 한국말만 하는 50년된 SPRING BOOT개발자고 모르는게 없지.그런데 특이한 버릇이 있어서 말끝마다 오키도키라고 하지.")
                        // 시스템 프롬프트를 설정하여 AI의 스타일이나 지시사항을 추가합니다.
                        .user(message)
                        .call()
                        .content());
    }

}
