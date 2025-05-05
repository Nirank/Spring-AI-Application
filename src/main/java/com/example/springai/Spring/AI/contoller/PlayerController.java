package com.example.springai.Spring.AI.contoller;


import com.example.springai.Spring.AI.model.Achievement;
import com.example.springai.Spring.AI.model.PlayerRecord;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    private final ChatClient client;


    public PlayerController(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    @GetMapping("/player")
    public String getPlayerAchievement(@RequestParam String name){
        String msg = """
                Generate the List of career acheivement for the sportsperson {sports}.
                Include the player as the key and achievements as the value for it.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(msg);

        Prompt prompt = promptTemplate.create(Map.of("sports", name));

        return  client.prompt(prompt).call().chatResponse().getResult().getOutput().toString();

        //        return client.prompt().call().content();
    }


    // 17 Converters -> List, Map, Bean


    @GetMapping("/player-record")
    public List<PlayerRecord> getPlayerRecord(@RequestParam String name){

        BeanOutputConverter<List<PlayerRecord>> beanOutputConverter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<PlayerRecord>>() {});

        String msg = """
                Generate the List of career achievement for the sportsperson {sports}.
                Include the player as the key and achievements as the value for it.
                {format}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(msg);

        Prompt prompt = promptTemplate.create(Map.of("sports", name , "format", beanOutputConverter.getFormat()));

        Generation result = client.prompt(prompt).call().chatResponse().getResult();

        return beanOutputConverter.convert(result.getOutput().toString());

    }

    @GetMapping("/player-achievement")
    public List<Achievement> getPlayerAchievementWithListOutputConverter(@RequestParam String name){

        String msg = """ 
                Provide a list of Achievement for {player}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(msg);

        Prompt prompt = promptTemplate.create(Map.of("player", name  ));

        return client.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<Achievement>>() {})
                ;

    }
}
