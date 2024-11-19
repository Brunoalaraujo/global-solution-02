package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class GetUserByIdView {
    public static void execute(Scanner sc, UserDAO userDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id do usuário que você deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("\n------------------------Usuário-------------------------------------");

        User user = userDAO.getById(id);
        System.out.println("Id: " + user.getId() + "\nNome: " + user.getName() + "\nEmail: " + user.getEmail() + "\nPassword: " + user.getPassword());
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
