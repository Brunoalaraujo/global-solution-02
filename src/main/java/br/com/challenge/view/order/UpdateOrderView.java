package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.Order;
import br.com.challenge.model.OrderStatus;
import br.com.challenge.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateOrderView {
    public static void execute(Scanner sc, OrderDAO orderDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id da ordem que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Order existingOrder = orderDAO.getById(id);

        if (existingOrder == null) {
            throw new NotFoundException();
        }

        System.out.print("Digite o novo status (OPEN, IN_PROGRESS, COMPLETED, CANCELED): ");
        String status = sc.nextLine();

        existingOrder.setStatus(Enum.valueOf(OrderStatus.class, status.toUpperCase()));
        existingOrder.setUpdatedAt(java.time.LocalDateTime.now());

        orderDAO.update(existingOrder);

        System.out.println("\n---Ordem atualizada com sucesso!---\n");
    }
}
