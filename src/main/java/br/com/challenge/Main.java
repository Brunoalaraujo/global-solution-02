package br.com.challenge;

import br.com.challenge.dao.UsersDAO;
import br.com.challenge.view.user.UserView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UsersDAO usersDAO;

        System.out.println("Seja Bem-Vindo a 123Kw!!");

        try{
            usersDAO = new UsersDAO();
            int escolha;
            do{
                menu();
                escolha = sc.nextInt();
                switch (escolha){
                    case 1:
                        UserView.execute();
                        break;
                    case 2:
                        break;
                    case 3:
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
        }
    }

    static void menu(){
        System.out.println("Escolha uma opção");
        System.out.println("1. Usuários");
        System.out.println("2. Carteiras");
        System.out.println("3. Transações");
        System.out.println("0. Sair");
    }
}
