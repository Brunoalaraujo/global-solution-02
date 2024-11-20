package br.com.challenge.model;

import java.math.BigDecimal;

public class Wallet {
    private int id;
    private int userId;
    private BigDecimal balance;
    private BigDecimal energyBalance;

    public Wallet(int id, int userId, BigDecimal balance, BigDecimal energyBalance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.energyBalance = energyBalance;
    }

    public Wallet(int userId, BigDecimal balance, BigDecimal energyBalance) {
        this.userId = userId;
        this.balance = balance;
        this.energyBalance = energyBalance;
    }

    public Wallet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getEnergyBalance() {
        return energyBalance;
    }

    public void setEnergyBalance(BigDecimal energyBalance) {
        this.energyBalance = energyBalance;
    }
}
