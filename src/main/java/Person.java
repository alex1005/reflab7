public class Person extends Customer {
    private String surname;
    public Person(String name, String surname, String email, Account account) {
        super(name, email, account);
        this.surname = surname;
    }

    @Override
    public void withdraw(double sum, String currency) {
        super.withdraw(sum, currency);
        withdrawWithOverdraft(sum, account.overdraftFee());
    }

    @Override
    protected String getFullName() {
        return getName() + " " + surname;
    }
}
