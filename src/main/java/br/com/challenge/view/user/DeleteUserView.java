package br.com.challenge.view.user;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.exception.EntidadeException;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUserView {
    public static void execute(Scanner sc, UsersDAO usersDAO) throws SQLException, EntidadeException {
        System.out.println("Digite o Id do usuário que deseja excluir: ");
        int id = sc.nextInt();

        usersDAO.delete(id);
        System.out.println("Usuário excluido com sucesso!");
    }
}
