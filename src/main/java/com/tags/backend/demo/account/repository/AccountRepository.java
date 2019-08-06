package com.tags.backend.demo.account.repository;

import com.tags.backend.demo.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
