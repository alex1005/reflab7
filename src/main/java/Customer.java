public abstract class Customer {

    private String name;
    private String email;
    protected Account account;

    public Customer(String name, String email, Account account) {
        this.name = name;
        this.email = email;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = getFullName();
        String accountDescription = account.getAccountDescriptionWithDaysOverdrawn();
        return fullName + " " + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = getFullName();
        String accountDescription = account.getAccountDescriptionWithMoney();
        return fullName + " " + accountDescription;
    }

    public String printCustomerAccount() {
        return account.toString();
    }

    @Override
    public String toString() {
        return getName() + " " + getEmail();
    }

    public abstract void withdraw(double sum, String currency);
    protected abstract String getFullName();
}
