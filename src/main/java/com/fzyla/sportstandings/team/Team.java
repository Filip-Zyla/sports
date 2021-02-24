package com.fzyla.sportstandings.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private long id;
    private String name;
    private String shortName;
    private String country;
    private String players;
    private int points;
    private int goalsScored;
    private int goalsConcede;
    private int matches;
    private String winDrawLost;
}
