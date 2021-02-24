package com.fzyla.sportstandings.league;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {
    private long id;
    private String name;
    private String country;
}