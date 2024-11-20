package br.com.challenge.dao;

import br.com.challenge.exception.NotFoundException;
import br.com.challenge.factory.ConnectionFactory;
import br.com.challenge.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static Connection connection;

    public UserDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private User parseUser(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        return new User(id, nome, email, password);
    }

    public void create(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO T_USERS (id, name, email, password) values (seq_user.nextval, ?, ?, ?)", new String[]{"id"});
        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getInt(1));
        }
    }

    public User getById(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_USERS WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new NotFoundException("Usuário não encontrado!");
        }

        return parseUser(result);
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_USERS");
        ResultSet result = stm.executeQuery();
        List<User> users = new ArrayList<>();

        while (result.next()) {
            users.add(parseUser(result));
        }

        return users;
    }

    public void update(User user) throws SQLException , NotFoundException{
        PreparedStatement stm = connection.prepareStatement("UPDATE T_USERS SET name = ?, email = ?, password = ? WHERE id = ?");
        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setInt(4, user.getId());
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public void delete(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM T_USERS WHERE id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Usuário não encontrado");
        }
    }
}
