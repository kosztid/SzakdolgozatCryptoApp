package com.example.crypto.communities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CommunityConfig {
    @Bean
    CommandLineRunner commandLineRunnerCommunity(CommunityRepository repository) {
        return args -> {
            if(repository.findAll().stream().count() == 0) {
                List<Message> messages = new ArrayList<Message>();
                List<String> members = new ArrayList<String>();

                Community community= new Community("oDQXaUubGgQictY5vVjn", "Bitcoin Community", messages, members);
                Community community2= new Community("E96A71FF-0ED6-459A-9C4C-2110DBE965D7", "TesztCommunity", messages, members);
                Community community3= new Community("AB78B2E3-4CE1-401C-9187-824387846365", "Trading History", messages, members);
                repository.saveAll(List.of(community, community2, community3));
            }
        };
    }
}