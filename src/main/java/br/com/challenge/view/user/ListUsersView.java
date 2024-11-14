package br.com.challenge.view.user;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.model.Users;

import java.sql.SQLException;
import java.util.List;

public class ListUsersView {
    public static void execute(UsersDAO usersDAO) throws SQLException {
        List<Users> list = usersDAO.list();

        for (Users user : list) {
            System.out.println("");
            System.out.println("Id: " + user.getId() + "\nNome: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPassword: " + user.getPassword());
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("");
        }

    }
}
