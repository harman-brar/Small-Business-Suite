package payment;

import java.util.HashSet;
import java.util.Set;

public class AccountManager {
    Set<Account> accounts;

    public AccountManager() {
        accounts = new HashSet<>();
    }

    private void addAccount(Account acc) {
        accounts.add(acc);
    }

    private void removeAccount(Account acc) {
        if (accounts.contains(acc)) {
            accounts.remove(acc);
        }
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
