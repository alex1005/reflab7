import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(-22.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoneyBalance().getMoney(), is(-20.25));
    }

    @Test
    public void testPrintPersonCustomerDaysOverdrawn() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerDaysOverdrawn(), is("danix dan Account: IBAN: RO023INGB434321431241, Days Overdrawn: 9"));
    }

    @Test
    public void testPrintPersonCustomerMoney() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerMoney(), is("danix dan Account: IBAN: RO023INGB434321431241, Money: 34.0"));
    }

    @Test
    public void testPrintCustomerAccountNormal() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerAccount(), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: normal"));
    }

    @Test
    public void testPrintCustomerAccountPremium() throws Exception {
        Customer customer = getPersonWithAccount(true);
        assertThat(customer.printCustomerAccount(), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: premium"));
    }

    @Test
    public void testPrintCompanyCustomerDaysOverdrawn() throws Exception {
        Customer customer = getCompanyWithAccount(false);
        assertThat(customer.printCustomerDaysOverdrawn(), is("company Account: IBAN: RO023INGB434321431241, Days Overdrawn: 9"));
    }

    @Test
    public void testPrintCompanyCustomerMoney() throws Exception {
        Customer customer = getCompanyWithAccount(false);
        assertThat(customer.printCustomerMoney(), is("company Account: IBAN: RO023INGB434321431241, Money: 34.0"));
    }

    private Customer getPersonWithAccount(boolean premium) {
        Account account = new Account(premium, 9);
        Customer customer = getPersonCustomer(account);
        MoneyBalance balance = new MoneyBalance(34.0, "EUR");
        account.setIban("RO023INGB434321431241");
        account.setMoneyBalance(balance);
        return customer;
    }

    private Customer getCompanyWithAccount(boolean premium) {
        Account account = new Account(premium, 9);
        Customer customer = getCompanyCustomer(account);
        MoneyBalance balance = new MoneyBalance(34.0, "EUR");
        account.setIban("RO023INGB434321431241");
        account.setMoneyBalance(balance);
        return customer;
    }

    private Account getAccountByTypeAndMoney(boolean premium, double money) {
        Account account = new Account(premium, 9);
        MoneyBalance balance = new MoneyBalance(money, "EUR");
        account.setIban("RO023INGB434321431241");
        account.setMoneyBalance(balance);
        return account;
    }

    private Customer getPersonCustomer(Account account) {
        Customer customer = new Person("danix", "dan", "dan@mail.com", account);
        account.setCustomer(customer);
        return customer;
    }

    private Customer getCompanyCustomer(Account account) {
        Customer customer = new Company("company", "company@mail.com", account, 0.50);
        account.setCustomer(customer);
        return customer;
    }
}
