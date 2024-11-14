package br.com.challenge.view.user;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.exception.EntidadeException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserView {
    private static final Scanner sc = new Scanner(System.in);

    public static void execute() throws SQLException {
        try{
            UsersDAO usersDAO = new UsersDAO();
            int escolha;
            do{
                menu();
                escolha = sc.nextInt();
                switch (escolha){
                    case 1:
                        CreateUserView.execute(sc, usersDAO);
                        break;
                    case 2:
                        ListUsersView.execute(usersDAO);
                        break;
                    case 3:
                        GetUserIdView.execute(sc, usersDAO);
                        break;
                    case 4:
                        UpdateUserView.execute(sc, usersDAO);
                        break;
                    case 5:
                        DeleteUserView.execute(sc, usersDAO);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        UsersDAO.closeConnection();
                        break;
                    default:
                        System.out.println("Opção Inválida!");
                }
            }while (escolha != 0);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        } catch (EntidadeException e) {
            throw new RuntimeException(e);
        }
    }

    private static void menu(){
        System.out.println("Escolha uma opção");
        System.out.println("1. Criar Usuario");
        System.out.println("2. Listar Usuarios");
        System.out.println("3. Listar Usuario por Id");
        System.out.println("4. Atualizar Usuario");
        System.out.println("5. Apagar Usuario");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
}
