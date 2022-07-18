package guru.springframework;

public class Sum implements Expression {
    Expression augment;
    Expression addmend;

    public Sum(Expression augment, Expression addmend) {
        this.augment = augment;
        this.addmend = addmend;
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augment.times(multiplier), addmend.times(multiplier));
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int amount = bank.reduce(augment, to).amount + bank.reduce(addmend, to).amount;
        return new Money(amount, to);
    }
}
