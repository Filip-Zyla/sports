package com.fzyla.sportstandings.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private long idMatch;
    private String teamHome;
    private String teamAway;
    private int goalsHome;
    private int goalsAway;
    private String date;
    private String stats;
}
