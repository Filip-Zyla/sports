package com.fzyla.sportstandings.team;

import com.fzyla.sportstandings.league.League;
import com.fzyla.sportstandings.match.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/get/{idTeam}")
    public String getTeamById(@PathVariable long idTeam, Model model) throws SQLException {
        Team response = teamService.getTeamById(idTeam);
        model.addAttribute("team", response);
        return "team-view";
    }

    @GetMapping("/get/league/{idTeam}")
    public String getLeaguesTeamBelongsTo(@PathVariable long idTeam, Model model) throws SQLException {
        League response = teamService.getLeaguesTeamBelongsTo(idTeam);
        model.addAttribute("leagueTeamBelongs", response);
        return "team-view";
    }

    @ModelAttribute("matches")
    public List<Match> getLast5Matches(@PathVariable long idTeam, Model model) throws SQLException {
        return teamService.getLast5Matches(idTeam);
    }
}