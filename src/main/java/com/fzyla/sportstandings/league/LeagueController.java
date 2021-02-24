package com.fzyla.sportstandings.league;

import com.fzyla.sportstandings.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/leagues")
public class LeagueController {

    private LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping
    public String getAllLeagues(Model model) throws SQLException {
        List<League> leagues = leagueService.getAllLeagues();
        model.addAttribute("leagues", leagues);
        model.addAttribute("newLeague", new League());
        return "leagues";
    }

    @GetMapping("/{id}")
    public String getLeagueByIdWithTeams(@PathVariable long id, Model model) throws SQLException {
        League response = leagueService.getLeagueById(id);
        List<Team> teams = leagueService.getTeamsInLeague(id);
        model.addAttribute("leagueById", response);
        model.addAttribute("teams", teams);
        return "league-view";
    }

    @PostMapping
    public String postNewLeague(@ModelAttribute League league, Model model) throws SQLException {
        model.addAttribute("newLeague", league);
        long response = leagueService.postNewLeague(league.getName(), league.getCountry());
        return "redirect:leagues";
    }

    @PostMapping("/{id}")
    public String deleteLeague(@PathVariable long id, Model model) throws SQLException {
        model.addAttribute("idDelete", id);
        leagueService.deleteById(id);
        return "redirect:";
    }
}
