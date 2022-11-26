package com.example.crypto.communities;

import com.example.crypto.users.User;
import com.example.crypto.users.actionModels.FavfolioUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/communities")
public class CommunityController {
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public List<Community> getCommunities() {
        return communityService.getCommunities();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCommunity(@RequestBody final Community model) {
        communityService.addCommunity(model);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@PathVariable("id") String id, @RequestBody final Message model) {
        communityService.sendMessage(id, model);
    }
}
