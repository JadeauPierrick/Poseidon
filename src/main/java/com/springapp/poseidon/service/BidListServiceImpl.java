package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.BidList;
import com.springapp.poseidon.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListServiceImpl implements BidListService{

    @Autowired
    private BidListRepository bidListRepository;

    @Override
    public List<BidList> getBidLists(){
        return bidListRepository.findAll();
    }

    @Override
    public BidList getBidById(Integer id) throws Exception {
        Optional<BidList> bidList = bidListRepository.findById(id);

        if (bidList.isPresent()){
            return bidList.get();
        }else {
            throw new Exception("The bid was not found");
        }
    }

    @Override
    public BidList addBid(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public void deleteBidById(Integer id) throws Exception {
        Optional<BidList> bid = bidListRepository.findById(id);
        if (bid.isPresent()) {
            bidListRepository.deleteById(id);
        }else {
            throw new Exception("Invalid bid Id : " + id);
        }
    }
}
