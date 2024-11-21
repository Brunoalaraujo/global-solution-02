package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class OrderView {
    private static final Scanner sc = new Scanner(System.in);

    public static void execute() throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        int choice;

        try {
            do {
                menu();
                choice = readChoice();
                handleMenuOption(choice, orderDAO);
            } while (choice != 0);
        } finally {
            orderDAO.closeConnection();
        }
    }

    private static void menu() {
        System.out.println("\nEscolha uma opção: ");
        System.out.println("1. Criar ordem");
        System.out.println("2. Listar ordens");
        System.out.println("3. Listar ordem pelo id");
        System.out.println("4. Atualizar ordem");
        System.out.println("5. Apagar ordem");
        System.out.println("0. Voltar p/ o menu inicial");
    }

    private static int readChoice() {
        System.out.print("Opção: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            sc.next();
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    private static void handleMenuOption(int choice, OrderDAO orderDAO) {
        try {
            switch (choice) {
                case 1 -> CreateOrderView.execute(sc, orderDAO);
                case 2 -> ListOrdersView.execute(orderDAO);
                case 3 -> GetOrderByIdView.execute(sc, orderDAO);
                case 4 -> UpdateOrderView.execute(sc, orderDAO);
                case 5 -> DeleteOrderView.execute(sc, orderDAO);
                case 0 -> System.out.println("Retornando ao menu...");
                default -> System.out.println("\n---Opção inválida!---\n");
            }
        } catch (NotFoundException e) {
            System.out.println("\n---Erro: " + e.getMessage() + "---\n");
            System.out.println("Retornando ao menu...");
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
