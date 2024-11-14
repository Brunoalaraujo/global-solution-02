package br.com.challenge.view.user;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.exception.EntidadeException;
import br.com.challenge.model.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class GetUserIdView {
    public static void execute(Scanner sc, UsersDAO usersDAO) throws SQLException, EntidadeException {
        System.out.println("Digite o Id do usuário que você deseja buscar: ");
        int id = sc.nextInt();

        Users user = usersDAO.searchId(id);
        System.out.println("");
        System.out.println("Id: " + user.getId() + "\nNome: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPassword: " + user.getPassword());
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("");
    }
}
