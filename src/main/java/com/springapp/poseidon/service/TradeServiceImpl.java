package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Trade;
import com.springapp.poseidon.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade getTradeById(Integer id) throws Exception {
        Optional<Trade> trade = tradeRepository.findById(id);
        if (trade.isPresent()) {
            return trade.get();
        }else {
            throw new Exception("The trade was not found");
        }
    }

    @Override
    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteTradeById(Integer id) throws Exception {
        Optional<Trade> trade = tradeRepository.findById(id);
        if (trade.isPresent()) {
            tradeRepository.deleteById(id);
        }else {
            throw new Exception("Invalid trade Id : " + id);
        }
    }
}
