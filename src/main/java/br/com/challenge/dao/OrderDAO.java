package br.com.challenge.dao;

import br.com.challenge.exception.NotFoundException;
import br.com.challenge.factory.ConnectionFactory;
import br.com.challenge.model.Order;
import br.com.challenge.model.OrderStatus;
import br.com.challenge.model.OrderType;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static Connection connection;

    public OrderDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private Order parseOrder(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int walletId = result.getInt("wallet_id");
        OrderType type = OrderType.valueOf(result.getString("type"));
        BigDecimal price = result.getBigDecimal("price");
        BigDecimal amount = result.getBigDecimal("amount");
        OrderStatus status = OrderStatus.valueOf(result.getString("status"));
        LocalDateTime createdAt = result.getTimestamp("created_at").toLocalDateTime();
        Timestamp updatedTimestamp = result.getTimestamp("updated_at");
        LocalDateTime updatedAt = updatedTimestamp != null ? updatedTimestamp.toLocalDateTime() : null;
        return new Order(id, walletId, type, price, amount, status, createdAt, updatedAt);
    }

    public void create(Order order) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO T_ORDERS (id, wallet_id, type, price, amount, status, created_at, updated_at) values (seq_order.nextval, ?, ?, ?, ?, ?, ?, ?)", new String[]{"id"});
        stm.setInt(1, order.getWalletId());
        stm.setString(2, order.getType().toString());
        stm.setBigDecimal(3, order.getPrice());
        stm.setBigDecimal(4, order.getAmount());
        stm.setString(5, order.getStatus().toString());
        stm.setObject(6, order.getCreatedAt());
        if (order.getUpdatedAt() != null) {
            stm.setObject(7, order.getUpdatedAt());
        } else {
            stm.setNull(7, java.sql.Types.TIMESTAMP);
        }
        stm.executeUpdate();

        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            order.setId(generatedKeys.getInt(1));
        }
    }

    public Order getById(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_ORDERS WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new NotFoundException("Ordem não encontrada!");
        }

        return parseOrder(result);
    }

    public List<Order> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM T_ORDERS");
        ResultSet result = stm.executeQuery();
        List<Order> users = new ArrayList<>();

        while (result.next()) {
            users.add(parseOrder(result));
        }

        return users;
    }

    public void update(Order order) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("UPDATE T_ORDERS SET status = ?, updated_at = ? WHERE id = ?");
        stm.setString(1, order.getStatus().toString());
        stm.setObject(2, order.getUpdatedAt());
        stm.setInt(3, order.getId());
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Ordem não encontrada");
        }
    }

    public void delete(int id) throws SQLException, NotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM T_ORDERS WHERE id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();

        if (line == 0) {
            throw new NotFoundException("Ordem não encontrada");
        }
    }
}
