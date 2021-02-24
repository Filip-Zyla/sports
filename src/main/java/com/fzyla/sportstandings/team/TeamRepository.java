package com.fzyla.sportstandings.team;

import com.fzyla.sportstandings.MySqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TeamRepository {

    private MySqlConnection connection;

    @Autowired
    public TeamRepository(MySqlConnection connection) {
        this.connection = connection;
    }

    public ResultSet getTeamById(long idTeam) throws SQLException {
        String sql = "select * from team where id_team = ?;";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, idTeam);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public ResultSet getLeaguesTeamBelongsTo(long idTeam) throws SQLException {
        String sql = "select * from league where id_league in (select id_league from team_in_league where id_team = ?);";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, idTeam);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public ResultSet getLast5Matches(long idTeam) throws SQLException {
        String sql = "select m.id_match, th.team_name, goals_home, goals_away, ta.team_name, date_of_match, stats from composite_match cm" +
                "    inner join `match` m on cm.id_match = m.id_match" +
                "    inner join team th on cm.id_home = th.id_team" +
                "    inner join team ta on cm.id_away = ta.id_team" +
                "   where id_home = ? or id_away = ? order by m.date_of_match limit 5;";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, idTeam);
        statement.setLong(2, idTeam);
        ResultSet rs = statement.executeQuery();
        return rs;
    }
}