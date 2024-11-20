package br.com.challenge.view.wallet;

import br.com.challenge.dao.WalletDAO;
import br.com.challenge.exception.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteWalletView {
    public static void execute(Scanner sc, WalletDAO walletDAO) throws SQLException, NotFoundException {
        System.out.print("Digite o id da carteira que deseja excluir: ");
        int id = sc.nextInt();

        walletDAO.delete(id);
        System.out.println("\n---Carteira excluída com sucesso!---\n");
    }
}
