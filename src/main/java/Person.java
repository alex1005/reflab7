public class Person extends Customer {
    private String surname;
    public Person(String name, String surname, String email, Account account) {
        super(name, email, account);
        this.surname = surname;
    }

    @Override
    public void withdraw(double sum, String currency) {
        account.withdrawFromAccountBalance(sum, currency, 1);
    }

    @Override
    protected String getFullName() {
        return getName() + " " + surname;
    }
}
