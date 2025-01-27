package com.pos.demo.controllers;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docs")
public class DocsController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public JSONObject findAll() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("GET /songs", "Get all songs");
        jsonObject.put("GET /songs/{id}", "Get song by id");
        jsonObject.put("POST /songs", "Create new song");
        jsonObject.put("DELETE /songs/{id}", "Delete song by id");

        jsonObject.put("GET /artists", "Get all artists");
        jsonObject.put("GET /artists/{id}", "Get artist by id");
        jsonObject.put("GET /artists/{id}/songs", "Get all songs for an artist");
        jsonObject.put("POST /artists", "Create new artist");
        jsonObject.put("DELETE /artists/{id}", "Delete artist by id");

        jsonObject.put("GET /playlists", "Get all playlists");
        jsonObject.put("POST /playlists/songs?playlistId=?1&songId=?2", "Add song with given id in the playlist with given id");
        jsonObject.put("DELETE /playlists/songs?playlistId=?1&songId=?2", "Remove song with given id in the playlist with given id");

        return jsonObject;
    }
}
