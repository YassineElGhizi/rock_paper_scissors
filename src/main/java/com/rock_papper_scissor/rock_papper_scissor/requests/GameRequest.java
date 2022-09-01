package com.rock_papper_scissor.rock_papper_scissor.requests;

public class GameRequest {
    public Long user_id;
    public String user_name;
    public Integer game_score;

    public GameRequest(Long user_id, String user_name, Integer game_score) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.game_score = game_score;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getGame_score() {
        return game_score;
    }

    public void setGame_score(Integer game_score) {
        this.game_score = game_score;
    }

    @Override
    public String toString() {
        return "GameRequest{" + "user_id=" + user_id + ", user_name='" + user_name + '\'' + ", game_score=" + game_score + '}';
    }
}
