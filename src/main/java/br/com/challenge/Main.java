package br.com.challenge;

import br.com.challenge.view.user.*;
import br.com.challenge.view.wallet.WalletView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n-----Seja Bem-Vindo a 123Kw!!-----\n");

        int choice;
        do {
            menu();
            choice = readChoice();
            handleMenuOption(choice);
        } while (choice != 0);
    }

    private static void menu() {
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Usuários");
        System.out.println("2. Carteiras");
        System.out.println("3. Transações");
        System.out.println("0. Sair");
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

    private static void handleMenuOption(int choice) {
        try {
            switch (choice) {
                case 1 -> UserView.execute();
                case 2 -> WalletView.execute();
                case 3 -> System.out.println("TODO: OrderView");
                case 0 -> System.out.println("Saindo da aplicação...");
                default -> System.out.println("\n---Opção inválida!---\n");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
