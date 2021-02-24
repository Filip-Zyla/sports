package com.fzyla.sportstandings.league;

import com.fzyla.sportstandings.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {

    private LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<League> getAllLeagues() throws SQLException {
        List<League> leagues = new ArrayList<>();
        ResultSet rs = leagueRepository.getAllLeagues();
        while (rs.next()) {
            League l = new League();
            l.setId(rs.getInt("id_league"));
            l.setName(rs.getString("league_name"));
            l.setCountry(rs.getString("country"));
            leagues.add(l);
        }
        return leagues;
    }

    public League getLeagueById(long idLeague) throws SQLException {
        ResultSet rs = leagueRepository.getLeagueById(idLeague);
        if (rs.next()){
            League l = new League();
            l.setId(rs.getInt("id_league"));
            l.setName(rs.getString("league_name"));
            l.setCountry(rs.getString("country"));
            return l;
        }
        return new League();
    }

    public List<Team> getTeamsInLeague(long idLeague) throws SQLException {
        ResultSet rs = leagueRepository.getTeamsInLeague(idLeague);
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team t = new Team();

            t.setId(rs.getLong("id_team"));
            t.setName(rs.getString("team_name"));
            t.setShortName(rs.getString("team_short"));
            t.setCountry(rs.getString("country"));
            t.setPlayers(rs.getString("players"));
            t.setPoints(rs.getInt("points"));
            t.setGoalsScored(rs.getInt("goals_scored"));
            t.setGoalsConcede(rs.getInt("goals_concede"));
            t.setMatches(rs.getInt("matches"));
            t.setWinDrawLost(rs.getString("win_draw_lost"));

            teams.add(t);
        }
        return teams;
    }

    public int postNewLeague(String name, String country) throws SQLException {
        int res = leagueRepository.postNewLeague(name, country);
        return res;
    }

    public int deleteById(long id) throws SQLException {
        return leagueRepository.deleteById(id);
    }
}
