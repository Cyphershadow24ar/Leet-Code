// PROBLEM : (2043) Simple Bank System

// SOLUTION :

class Bank {
private:
    vector<long long> bal; // to store account balances
    int n; // total number of accounts

    // Helper function to check valid account number
    bool isValid(int acc) {
        return acc >= 1 && acc <= n;
    }

public:
    // Constructor
    Bank(vector<long long>& balance) {
        bal = balance;
        n = balance.size();
    }

    // Transfer money between two accounts
    bool transfer(int account1, int account2, long long money) {
        if (!isValid(account1) || !isValid(account2)) return false;
        if (bal[account1 - 1] < money) return false;
        bal[account1 - 1] -= money;
        bal[account2 - 1] += money;
        return true;
    }

    // Deposit money into an account
    bool deposit(int account, long long money) {
        if (!isValid(account)) return false;
        bal[account - 1] += money;
        return true;
    }

    // Withdraw money from an account
    bool withdraw(int account, long long money) {
        if (!isValid(account)) return false;
        if (bal[account - 1] < money) return false;
        bal[account - 1] -= money;
        return true;
    }
};

