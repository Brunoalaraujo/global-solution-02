package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.model.Wallet;

import java.sql.SQLException;
import java.util.List;

public class ListWalletsView {
    public static void execute(WalletDAO walletDAO) throws SQLException {
        List<Wallet> list = walletDAO.getAll();

        System.out.println("------------------------Lista de carteiras-------------------------------------");

        if (list.isEmpty()) {
            System.out.println("Lista vazia");
            System.out.println("-------------------------------------------------------------------------------------");
        }

        for (Wallet wallet : list) {
            System.out.println("Id: " + wallet.getId() + "\nId do usuário: " + wallet.getUserId() + "\nSaldo: " + wallet.getBalance() + "\nSaldo de energia: " + wallet.getEnergyBalance());
            System.out.println("-------------------------------------------------------------------------------------");
        }

    }
}
