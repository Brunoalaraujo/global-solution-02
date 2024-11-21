package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.Order;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetOrderByIdView {
    public static void execute(Scanner sc, OrderDAO orderDAO) {
        try {
            System.out.print("Digite o id da ordem que você deseja buscar: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("\n------------------------ Ordem -------------------------------------");

            Order order = orderDAO.getById(id);

            System.out.println("Id: " + order.getId());
            System.out.println("Id da carteira: " + order.getWalletId());
            System.out.println("Tipo: " + order.getType());
            System.out.println("Preço: " + order.getPrice());
            System.out.println("Quantidade: " + order.getAmount());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Criado em: " + order.getCreatedAt());
            System.out.println("Atualizado em: " + order.getUpdatedAt());

            System.out.println("-------------------------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("Erro: O id precisa ser um número inteiro válido.");
            sc.nextLine();
        } catch (NotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
