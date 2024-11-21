package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.model.Order;

import java.sql.SQLException;
import java.util.List;

public class ListOrdersView {
    public static void execute(OrderDAO orderDAO) throws SQLException {
        List<Order> list = orderDAO.getAll();

        System.out.println("------------------------ Lista de Ordens -------------------------------------");

        if (list.isEmpty()) {
            System.out.println("Lista vazia");
            System.out.println("-------------------------------------------------------------------------------------");
            return;
        }

        for (Order order : list) {
            System.out.println("Id: " + order.getId());
            System.out.println("Id da carteira: " + order.getWalletId());
            System.out.println("Tipo: " + order.getType());
            System.out.println("Preço: " + order.getPrice());
            System.out.println("Quantidade: " + order.getAmount());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Criado em: " + order.getCreatedAt());
            System.out.println("Atualizado em: " + (order.getUpdatedAt() != null ? order.getUpdatedAt() : "N/A"));
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }
}
