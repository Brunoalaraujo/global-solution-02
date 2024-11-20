package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class WalletView {
    private static final Scanner sc = new Scanner(System.in);

    public static void execute() throws SQLException {
        WalletDAO walletDAO = new WalletDAO();
        int choice;

        try {
            do {
                menu();
                choice = readChoice();
                handleMenuOption(choice, walletDAO);
            } while (choice != 0);
        } finally {
            walletDAO.closeConnection();
        }
    }

    private static void menu() {
        System.out.println("\nEscolha uma opção: ");
        System.out.println("1. Criar carteira");
        System.out.println("2. Listar carteiras");
        System.out.println("3. Listar carteira pelo id");
        System.out.println("4. Atualizar carteira");
        System.out.println("5. Apagar carteira");
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

    private static void handleMenuOption(int choice, WalletDAO walletDAO) {
        try {
            switch (choice) {
                case 1 -> CreateWalletView.execute(sc, walletDAO);
                case 2 -> ListWalletsView.execute(walletDAO);
                case 3 -> GetWalletByIdView.execute(sc, walletDAO);
                case 4 -> UpdateWalletView.execute(sc, walletDAO);
                case 5 -> DeleteWalletView.execute(sc, walletDAO);
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
