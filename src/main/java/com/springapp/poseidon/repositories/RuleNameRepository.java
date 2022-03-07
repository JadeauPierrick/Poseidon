package com.springapp.poseidon.repositories;

import com.springapp.poseidon.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
