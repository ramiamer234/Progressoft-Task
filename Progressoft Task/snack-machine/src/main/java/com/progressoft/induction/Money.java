package com.progressoft.induction;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount;

    static final Money ZERO = new Money(BigDecimal.valueOf(0.0));
    static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.50));
    static final Money DINAR = new Money(BigDecimal.valueOf(1.0));

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.amount = amount;
    }


    public Money add(Money amnt2) {
        return new Money(this.amount.add(amnt2.getAmount()));
    }

    public boolean isLessThan(Money money) {
        if (money == null) return false;
        return this.amount.compareTo(money.getAmount()) < 0;
    }

    public Money subtract(Money amnt2) {
        if (amnt2.getAmount().compareTo(this.amount) > 0) {
            throw new IllegalArgumentException();
        }
        return new Money(this.amount.subtract(amnt2.getAmount()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }


}
