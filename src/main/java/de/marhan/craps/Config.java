package de.marhan.craps;

import java.math.BigDecimal;

public class Config {

    private BigDecimal initialAccount = new BigDecimal("30");
    private Integer maxGames = 100;

    public BigDecimal getInitialAccount() {
        return initialAccount;
    }

    public Integer getMaxGames() {
        return maxGames;
    }

    public void setMaxGames(Integer maxGames) {
        this.maxGames = maxGames;
    }
}
