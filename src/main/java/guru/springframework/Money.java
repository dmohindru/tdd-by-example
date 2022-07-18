package guru.springframework;


import java.util.Objects;

public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return this.currency;
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;

        return amount == money.amount
                && Objects.equals(this.currency, money.currency);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        return new Money(amount/ bank.rate(this.currency, to), to);
    }


    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + "'" +
                "}";
    }

}
