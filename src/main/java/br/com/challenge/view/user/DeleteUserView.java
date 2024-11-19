package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUserView {
    public static void execute(Scanner sc, UserDAO userDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id do usuário que deseja excluir: ");
        int id = sc.nextInt();

        userDAO.delete(id);
        System.out.println("\n---Usuário excluído com sucesso!---\n");
    }
}
