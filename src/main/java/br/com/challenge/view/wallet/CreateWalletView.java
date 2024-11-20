package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.model.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateWalletView {
    public static void execute(Scanner sc, WalletDAO walletDAO) throws SQLException {
        System.out.print("Digite o id do usuário detentor da carteira: ");
        int userId = sc.nextInt();
        System.out.print("Digite o saldo: ");
        BigDecimal balance = sc.nextBigDecimal();
        System.out.print("Digite a quantidade de energia: ");
        BigDecimal energyBalance = sc.nextBigDecimal();

        Wallet wallet = new Wallet(userId, balance, energyBalance);
        walletDAO.create(wallet);

        System.out.println("\n---Carteira criada com sucesso!---\n");
    }
}
