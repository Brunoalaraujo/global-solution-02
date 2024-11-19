package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateUserView {
    public static void execute(Scanner sc, UserDAO userDAO) throws SQLException {
        System.out.print("Digite seu nome: ");
        String name = sc.next();
        System.out.print("Digite seu email: ");
        String email = sc.next();
        System.out.print("Digite seu senha: ");
        String password = sc.next();

        User user = new User(name, email, password);
        userDAO.create(user);

        System.out.println("\n---Usuário criado com sucesso!---\n");
    }
}
