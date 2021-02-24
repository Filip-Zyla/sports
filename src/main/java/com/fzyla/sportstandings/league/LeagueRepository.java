package com.fzyla.sportstandings.league;

import com.fzyla.sportstandings.MySqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LeagueRepository {

    private MySqlConnection connection;

    @Autowired
    public LeagueRepository(MySqlConnection connection){
        this.connection = connection;
    }

    public ResultSet getAllLeagues() throws SQLException {
        String sql = "select * from league;";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public ResultSet getLeagueById(long idLeague) throws SQLException {
        String sql = "select * from league where id_league = ?;";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, idLeague);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public ResultSet getTeamsInLeague(long idLeague) throws SQLException {
        String sql = "select * from team where id_team in (select id_team from team_in_league where id_league = ?);";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, idLeague);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public int postNewLeague(String name, String country) throws SQLException {
        String sql = "insert  into league(league_name, country) values (?, ?);";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, country);
        int res = statement.executeUpdate();
        return res;
    }

    public int deleteById(long id) throws SQLException {
        String sql = "delete  from league where id_league = ?;";
        PreparedStatement statement = connection.getConnection().prepareStatement(sql);
        statement.setLong(1, id);
        int res = statement.executeUpdate();
        return res;
    }

}
