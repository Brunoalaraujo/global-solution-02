package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.User;
import br.com.challenge.model.Wallet;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUserView {
    public static void execute(Scanner sc, UserDAO userDAO) throws SQLException, NotFoundException {
        System.out.print("Digite seu id: ");
        int id = sc.nextInt();

        User existingUser = userDAO.getById(id);

        if (existingUser == null) {
            throw new NotFoundException();
        }

        System.out.print("Digite seu nome: ");
        String name = sc.next();
        System.out.print("Digite seu email: ");
        String email = sc.next();
        System.out.print("Digite seu senha: ");
        String password = sc.next();

        User user = new User(id, name, email, password);

        userDAO.update(user);

        System.out.println("\n---Usuario atualizado com sucesso!\n");
    }
}
