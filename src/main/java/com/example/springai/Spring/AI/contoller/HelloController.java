package com.example.springai.Spring.AI.contoller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final ChatClient client;

    public HelloController(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    @GetMapping("/api/")
    public String prompt(@RequestParam String msg){
        return client.prompt(msg).call().content();
    }

    //14   API with promptTemplate and Prompt
    // prompt template with SpringTemplate

    // 15  ->> Roles in Prompts
    @GetMapping("/sport")
    public String getSportDetails(@RequestParam String name){
        String msg = """
                List the details of the sports %s 
                along with their Rules and Regulation.
                Show the details in the readable format.
                """;

        String systemMessage = """
                You are a smart Virtual Assistant.
                Your task is to give the deatails about the Sport.
                If someone ask about something else and you do not know the answer
                Just say that you do not know the answer.
                """;

        UserMessage userMessage = new UserMessage(String.format(msg,name));

        SystemMessage systemMessage1 = new SystemMessage(systemMessage);

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage1 ));

        return client
                .prompt(prompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .toString();
//        return client.prompt(msg).call().content();
    }



    //16 API with prompt data
}
