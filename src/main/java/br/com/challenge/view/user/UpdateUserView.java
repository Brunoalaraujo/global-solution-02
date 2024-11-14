package br.com.challenge.view.user;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.model.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUserView {
    public static void execute(Scanner sc, UsersDAO usersDAO)throws SQLException {
        System.out.println("Digite seu id: ");
        int id = sc.nextInt();
        System.out.println("Digite seu nome: ");
        String nome = sc.next() + sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.next() + sc.nextLine();
        System.out.println("Digite seu senha: ");
        String senha = sc.next() + sc.nextLine();

        Users users = new Users(id, nome, email, senha);

        usersDAO.update(users);

        System.out.println("Usuario atualizado com sucesso!");
    }
}
