package com.fzyla.sportstandings.team;

import com.fzyla.sportstandings.league.League;
import com.fzyla.sportstandings.match.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public Team getTeamById(long idTeam) throws SQLException {
        ResultSet rs = teamRepository.getTeamById(idTeam);
        if (rs.next()) {
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

            return t;
        }
        return new Team();
    }

    public League getLeaguesTeamBelongsTo(long idTeam) throws SQLException {
        ResultSet rs = teamRepository.getLeaguesTeamBelongsTo(idTeam);
        if (rs.next()){
            League l = new League();
            l.setId(rs.getInt("id_league"));
            l.setName(rs.getString("league_name"));
            l.setCountry(rs.getString("country"));
            return l;
        }
        return new League();
    }

    public List<Match> getLast5Matches(long idTeam) throws SQLException {
        ResultSet rs = teamRepository.getLast5Matches(idTeam);
        List<Match> matches = new ArrayList<>();
        while (rs.next()) {
            Match m = new Match();
            m.setIdMatch(rs.getLong("m.id_match"));
            m.setTeamHome(rs.getString("th.team_name"));
            m.setTeamAway(rs.getString("ta.team_name"));
            m.setGoalsHome(rs.getInt("goals_home"));
            m.setGoalsAway(rs.getInt("goals_away"));
            m.setDate(rs.getString("date_of_match"));
            matches.add(m);
        }
        return matches;
    }
}













