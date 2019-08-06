package com.tags.backend.demo.tags.repository;

import com.tags.backend.demo.links.domain.Link;
import com.tags.backend.demo.tags.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TagRepository extends JpaRepository<Tag, Long> {

  @Query("select e from Tag e where e.link=?1")
  List<Tag> findByLink(Link link);
}
