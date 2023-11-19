public class Account {

    private String iban;

    private boolean isPremium;

    private int daysOverdrawn;

    private MoneyBalance moneyBalance;

    private Customer customer;

    public Account(boolean isPremium, int daysOverdrawn) {
        super();
        this.isPremium = isPremium;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (isPremium) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (isPremium) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public boolean isPremium() {
        return isPremium;
    }

    public String getPrintableAccountType() {
        return isPremium ? "premium" : "normal";
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void updateBalance(double money) {
        moneyBalance.setMoney(money);
    }

    public MoneyBalance getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(MoneyBalance moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String printCustomer() {
        return customer.getName() + " " + customer.getEmail();
    }
}
