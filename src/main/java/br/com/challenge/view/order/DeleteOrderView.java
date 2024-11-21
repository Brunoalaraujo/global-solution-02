package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOrderView {
    public static void execute(Scanner sc, OrderDAO orderDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id da ordem que deseja excluir: ");
        int id = sc.nextInt();

        orderDAO.delete(id);
        System.out.println("\n---Ordem excluída com sucesso!---\n");
    }
}
