package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUserView {
    public static void execute(Scanner sc, UserDAO userDAO) throws SQLException, NotFoundException {
        System.out.print("Digite seu id: ");
        int id = sc.nextInt();
        System.out.print("Digite seu nome: ");
        String nome = sc.next();
        System.out.print("Digite seu email: ");
        String email = sc.next();
        System.out.print("Digite seu senha: ");
        String senha = sc.next();

        User user = new User(id, nome, email, senha);

        userDAO.update(user);

        System.out.println("\n---Usuario atualizado com sucesso!\n");
    }
}
