package com.tags.backend.demo.links.repository;

import com.tags.backend.demo.account.domain.Account;
import com.tags.backend.demo.links.domain.Link;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkRepository extends JpaRepository<Link, Long> {

  @Query("select e from Link e where e.account=?1")
  List<Link> findByAccount(Account account);

  @Query("select e from Link e where e.account=?1 and e.baseUrl=?2 and e.query=?3")
  Link findLink(Account account, String url, String query);
}
