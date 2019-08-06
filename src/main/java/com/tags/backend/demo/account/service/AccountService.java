package com.tags.backend.demo.account.service;

import com.tags.backend.demo.account.domain.Account;
import com.tags.backend.demo.account.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  // FIXME: harcoded first and second account should be removed when account management is implemented
  private static final Account FIRST = new Account("john.doe@gmail.com", "John Doe", "johndoe");
  private static final Account SECOND = new Account("peter.parker@gmail.com", "Peter Parker", "peterparker");

  private AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account getAccount(Long accountId) {
    List<Account> all = accountRepository.findAll();
    if (all.size() == 0) {
      initAccounts();
    }
    if (accountId == 1L) {
      return FIRST;
    } else {
      return SECOND;
    }
  }

  private void initAccounts() {
    accountRepository.save(FIRST);
    accountRepository.save(SECOND);
  }

}