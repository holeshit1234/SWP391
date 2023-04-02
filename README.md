Welcome to Account Management App
This Java application is an account management system that uses Maven for dependency management, TestNG Testcase for testing, and does not use Selenium. The application provides three main functions, including adding an account, updating an account, and viewing all accounts or a specific account by ID.

Technologies Used
Java
Maven
TestNG
Netbeans IDE
Functions
Add an account: Add new Account(int id, String name, String email, and String phone).
Update account: Update account by id.
Get All Account: Display a list of all accounts currently in the system.
Get Account By Id: Get an account by id.
public void addAccount(Account account) {
        Account accountById = getAccountById(account.getId());
        if (accountById == null) {
            accounts.add(account);
        }
    }
Testing
This application using TestNG Test Case testing framework for Java Maven Application. Note that these tests do not use Selenium.

@Test
    public void testAddAccount() {
        Account account = new Account(1, "Alice", "alice@gmail.com", "012345678");
        accountManager.addAccount(account);
        Assert.assertEquals(accountManager.getAccountById(1), account);
    }
Contributing
If you find a bug or want to suggest an enhancement, please submit an issue or pull request. We welcome contributions from the community.

License
Copyright © 2023 Team 3 - SWT301
