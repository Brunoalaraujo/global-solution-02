package br.com.challenge.view.user;

import br.com.challenge.dao.UserDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class UserView {
    private static final Scanner sc = new Scanner(System.in);

    public static void execute() throws SQLException {
        UserDAO userDAO = new UserDAO();
        int choice;

        try {
            do {
                menu();
                choice = readChoice();
                handleMenuOption(choice, userDAO);
            } while (choice != 0);
        } finally {
            userDAO.closeConnection();
        }
    }

    private static void menu() {
        System.out.println("\nEscolha uma opção: ");
        System.out.println("1. Criar usuário");
        System.out.println("2. Listar usuários");
        System.out.println("3. Listar usuário pelo id");
        System.out.println("4. Atualizar usuario");
        System.out.println("5. Apagar usuário");
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

    private static void handleMenuOption(int choice, UserDAO userDAO) {
        try {
            switch (choice) {
                case 1 -> CreateUserView.execute(sc, userDAO);
                case 2 -> ListUsersView.execute(userDAO);
                case 3 -> GetUserByIdView.execute(sc, userDAO);
                case 4 -> UpdateUserView.execute(sc, userDAO);
                case 5 -> DeleteUserView.execute(sc, userDAO);
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
