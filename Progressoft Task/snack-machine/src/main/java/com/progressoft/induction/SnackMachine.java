package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SnackMachine {
    private Money moneyInMachine;
    private Money moneyInTransaction;
    public static int DEFAULT_QUANTITY = 10;
    List<Snack> snacks = Arrays.asList(
            new Snack(SnackType.CHEWING_GUM, DEFAULT_QUANTITY, 0.5),
            new Snack(SnackType.CHIPS, DEFAULT_QUANTITY, 1.0),
            new Snack(SnackType.CHOCOLATE, DEFAULT_QUANTITY, 2.0)
    );

    public SnackMachine() {
        this.moneyInMachine = Money.ZERO;
        this.moneyInTransaction = Money.ZERO;
    }

    public Money moneyInside() {
        return this.moneyInMachine;
    }

    public Money moneyInTransaction() {
        return this.moneyInTransaction;
    }

    public void insertMoney(Money money) {
        if (money == null) throw new IllegalArgumentException();
        if (money.getAmount().compareTo(BigDecimal.valueOf(0.25)) != 0 &&
                money.getAmount().compareTo(BigDecimal.valueOf(0.50)) != 0 &&
                money.getAmount().compareTo(BigDecimal.valueOf(1.0)) != 0 &&
                money.getAmount().compareTo(BigDecimal.valueOf(5.0)) != 0 &&
                money.getAmount().compareTo(BigDecimal.valueOf(10.0)) != 0
        ) {
            throw new IllegalArgumentException();
        }

        this.moneyInTransaction = this.moneyInTransaction.add(money);
    }

    public Money buySnack(SnackType st) {

        Money change;
        Snack chosenSnack = snacks
                .stream()
                .filter(snack -> snack.snackType.equals(st))
                .findFirst()
                .get();
        Money snackPrice = new Money(BigDecimal.valueOf(chosenSnack.price()));

        if (this.moneyInTransaction.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalStateException();
        }

        if (this.moneyInTransaction.getAmount().compareTo(BigDecimal.valueOf(chosenSnack.price())) >= 0 && chosenSnack.quantity() > 0) {
            this.moneyInMachine = this.moneyInMachine.add(snackPrice);
            change = moneyInTransaction.subtract(snackPrice);
            this.moneyInTransaction = Money.ZERO;
            chosenSnack.decreaseQuantity();
        } else {
            throw new IllegalStateException();
        }

        return change;

    }

    public Snack chewingGums() {
        return snacks
                .stream()
                .filter(snack -> snack.snackType.equals(SnackType.CHEWING_GUM))
                .findFirst()
                .get();
    }

    public Snack chips() {
        return snacks
                .stream()
                .filter(snack -> snack.snackType.equals(SnackType.CHIPS))
                .findFirst()
                .get();
    }

    public Snack chocolates() {
        return snacks
                .stream()
                .filter(snack -> snack.snackType.equals(SnackType.CHOCOLATE))
                .findFirst()
                .get();
    }
}
