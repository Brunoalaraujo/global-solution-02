package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateWalletView {
    public static void execute(Scanner sc, WalletDAO walletDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id da carteira: ");
        int id = sc.nextInt();
        System.out.print("Digite o novo saldo: ");
        BigDecimal balance = sc.nextBigDecimal();
        System.out.print("Digite o novo saldo de energia: ");
        BigDecimal energyBalance = sc.nextBigDecimal();

        Wallet existingWallet = walletDAO.getById(id);

        Wallet wallet = new Wallet(id, existingWallet.getUserId(), balance, energyBalance);

        walletDAO.update(wallet);

        System.out.println("\n---Carteira atualizada com sucesso!\n");
    }
}
