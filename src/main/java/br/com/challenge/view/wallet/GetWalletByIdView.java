package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;
import br.com.challenge.model.Wallet;

import java.sql.SQLException;
import java.util.Scanner;

public class GetWalletByIdView {
    public static void execute(Scanner sc, WalletDAO walletDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id da carteira que você deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("\n------------------------Carteira-------------------------------------");

        Wallet wallet = walletDAO.getById(id);
        System.out.println("Id: " + wallet.getId() + "\nId do usuàrio: " + wallet.getUserId() + "\nSaldo: " + wallet.getBalance() + "\nEnergia: " + wallet.getEnergyBalance());
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
