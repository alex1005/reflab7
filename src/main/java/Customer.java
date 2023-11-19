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

        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + " " + accountDescription;
    }

    public String printCustomerMoney() {
        String fullName = getFullName();
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoneyBalance().getMoney();
        return fullName + " " + accountDescription;
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoneyBalance().getMoney() + ", Account type: " + account.getPrintableAccountType();
    }

    public void withdraw(double sum, String currency) {
        if (!account.getMoneyBalance().getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
    }
    protected abstract String getFullName();

    final protected void withdrawWithOverdraft(double sum, double totalOverdraft) {
        if (isInOverdraft()) {
            account.updateBalance((account.getMoneyBalance().getMoney() - sum) - sum * totalOverdraft);
        } else {
            account.updateBalance(account.getMoneyBalance().getMoney() - sum);
        }
    }

    private boolean isInOverdraft() {
        return account.getMoneyBalance().getMoney() < 0;
    }
}
