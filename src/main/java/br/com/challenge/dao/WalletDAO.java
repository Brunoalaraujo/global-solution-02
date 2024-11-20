package br.com.challenge.dao;

import br.com.challenge.exception.NotFoundException;
import br.com.challenge.factory.ConnectionFactory;
import br.com.challenge.model.Wallet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO {
    private static Connection connection;

    public WalletDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private Wallet parseWallet(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int userId = result.getInt("user_id");
        BigDecimal balance = result.getBigDecimal("balance");
        BigDecimal energyBalance = result.getBigDecimal("energy_balance");
        return new Wallet(id, userId, balance, energyBalance);
    }

    public void create(Wallet wallet) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO T_WALLETS (id, user_id, balance, energy_balance) values (seq_wallet.nextval, ?, ?, ?)", new String[]{"id"});
        stm.setInt(1, wallet.getUserId());
        stm.setBigDecimal(2, wallet.getBalance());
        stm.setBigDecimal(3, wallet.getEnergyBalance());
        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            wallet.setId(generatedKeys.getInt(1));
        }
    }

    public Wallet getById(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_WALLETS WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new NotFoundException("Carteira não encontrada!");
        }

        return parseWallet(result);
    }

    public List<Wallet> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_WALLETS");
        ResultSet result = stm.executeQuery();
        List<Wallet> wallets = new ArrayList<>();

        while (result.next()) {
            wallets.add(parseWallet(result));
        }

        return wallets;
    }

    public void update(Wallet wallet) throws SQLException , NotFoundException{
        PreparedStatement stm = connection.prepareStatement("UPDATE T_WALLETS SET balance = ?, energy_balance = ? WHERE id = ?");
        stm.setBigDecimal(1, wallet.getBalance());
        stm.setBigDecimal(2, wallet.getEnergyBalance());
        stm.setInt(3, wallet.getId());
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Carteira não encontrada");
        }
    }

    public void delete(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM T_WALLETS WHERE id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Carteira não encontrada");
        }
    }
}
