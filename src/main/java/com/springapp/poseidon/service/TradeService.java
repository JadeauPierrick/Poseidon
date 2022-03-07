package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Trade;

import java.util.List;

public interface TradeService {

    List<Trade> getTrades();

    Trade getTradeById(Integer id) throws Exception;

    Trade addTrade(Trade trade);

    void deleteTradeById(Integer id) throws Exception;
}
