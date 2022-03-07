package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.BidList;

import java.util.List;

public interface BidListService {

    List<BidList> getBidLists();

    BidList getBidById(Integer id) throws Exception;

    BidList addBid(BidList bidList);

    void deleteBidById(Integer id) throws Exception;
}
