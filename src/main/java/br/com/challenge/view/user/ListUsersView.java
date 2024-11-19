package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.model.User;

import java.sql.SQLException;
import java.util.List;

public class ListUsersView {
    public static void execute(UserDAO userDAO) throws SQLException {
        List<User> list = userDAO.getAll();

        System.out.println("------------------------Lista de usuários-------------------------------------");

        if (list.isEmpty()) {
            System.out.println("Lista vazia");
            System.out.println("-------------------------------------------------------------------------------------");
        }

        for (User user : list) {
            System.out.println("Id: " + user.getId() + "\nNome: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPassword: " + user.getPassword());
            System.out.println("-------------------------------------------------------------------------------------");
        }

    }
}
