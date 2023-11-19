public class Company extends Customer {
    private static int ADDITIONAL_DISCOUNT_DIVIDER = 2;
    private static int BASE_DISCOUNT_DIVIDER = 1;
    private double companyOverdraftDiscount = 1;

    public Company(String name, String email, Account account) {
        super(name, email, account);
    }

    public Company(String name, String email, Account account, double companyOverdraftDiscount) {
        super(name, email, account);
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    @Override
    public void withdraw(double sum, String currency) {
        int discountDivider = account.isPremium() ? ADDITIONAL_DISCOUNT_DIVIDER : BASE_DISCOUNT_DIVIDER;
        account.withdrawFromAccountBalance(sum, currency,  companyOverdraftDiscount / discountDivider);
    }

    @Override
    protected String getFullName()  {
        return getName();
    }
}
