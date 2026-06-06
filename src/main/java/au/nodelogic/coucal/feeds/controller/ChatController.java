package au.nodelogic.coucal.feeds.controller;

import au.nodelogic.coucal.feeds.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String showChat() {
        return "chat/index";
    }

    @PostMapping("/chat/message")
    public String handleMessage(@RequestParam String message) {
        return "chat/message :: chat-message(message='" + message + "', response='" +
            chatService.generateResponse(message) + "')";
    }
}
