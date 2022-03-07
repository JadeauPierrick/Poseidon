package com.springapp.poseidon.repositories;

import com.springapp.poseidon.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
