package br.com.challenge.dao;

import br.com.challenge.exception.EntidadeException;
import br.com.challenge.factory.ConnectionFactory;
import br.com.challenge.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {


    private static Connection conexao;

    public UsersDAO() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void create(Users user) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO T_USERS (id, name, email, password) values (?, ?, ?, ?)");
        stm.setInt(1, user.getId());
        stm.setString(2, user.getName());
        stm.setString(3, user.getEmail());
        stm.setString(4, user.getPassword());
        stm.executeUpdate();
    }

    public static void closeConnection() throws SQLException {
        conexao.close();
    }

    public Users searchId (int id) throws SQLException, EntidadeException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_USERS WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new EntidadeException("Usuário não encontrado!");
        }

        return parseUser(result);
    }

    public Users parseUser(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        return new Users(id, nome, email, password);
    }

    public List<Users> list() throws SQLException{
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_USERS");
        ResultSet result = stm.executeQuery();
        List<Users> lista = new ArrayList<>();

        while (result.next()) {
            lista.add(parseUser(result));
        }

        return lista;
    }

    public void update(Users user) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("UPDATE T_USERS SET name = ?, email = ?, password = ? WHERE id = ?");
        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setInt(4, user.getId());
        stm.executeUpdate();

    }

    public void delete(int id) throws SQLException, EntidadeException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM T_USERS WHERE id = ?");
        stm.setInt(1, id);
        int linha = stm.executeUpdate();

        if (linha == 0){
            throw new EntidadeException("Usuário nao encontrado");
        }
    }
}
