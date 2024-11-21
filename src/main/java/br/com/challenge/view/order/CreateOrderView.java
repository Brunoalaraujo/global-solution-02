package br.com.challenge.view.order;

import br.com.challenge.dao.OrderDAO;
import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.Order;
import br.com.challenge.model.OrderType;
import br.com.challenge.model.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateOrderView {
    public static void execute(Scanner sc, OrderDAO orderDAO, WalletDAO walletDAO) {
        try {
            System.out.print("Digite o id da carteira: ");
            int walletId = sc.nextInt();
            sc.nextLine();

            Wallet existingWallet = walletDAO.getById(walletId);

            if (existingWallet == null) {
                throw new NotFoundException();
            }

            System.out.print("Digite o tipo da ordem (BUY/SELL): ");
            String typeInput = sc.nextLine().trim().toUpperCase();
            OrderType type = OrderType.valueOf(typeInput);

            System.out.print("Digite o preço da ordem: ");
            BigDecimal price = sc.nextBigDecimal();

            System.out.print("Digite a quantidade da ordem: ");
            BigDecimal amount = sc.nextBigDecimal();

            if (price.multiply(amount).compareTo(existingWallet.getBalance()) > 0) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar a ordem.");
            }

            Order order = new Order(walletId, type, price, amount);

            orderDAO.create(order);

            System.out.println("\n--- Ordem criada com sucesso! ---\n");
            System.out.println("ID da Ordem Criada: " + order.getId());
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Tipo de ordem inválido. Use 'BUY' ou 'SELL'.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a ordem: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
