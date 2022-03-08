package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.Trade;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    private Trade trade;

    @BeforeAll
    private void setUp() {
        trade = new Trade();
        trade.setAccount("Trade Account");
        trade.setType("Type");
    }

    @Test
    @Order(1)
    public void addTradeTest() {
        tradeService.addTrade(trade);
        assertNotNull(trade.getTradeId());
        assertThat(trade.getAccount()).isEqualTo("Trade Account");
    }

    @Test
    @Order(2)
    public void getTradesTest() {
        List<Trade> trades = tradeService.getTrades();
        assertTrue(trades.size() > 0);
    }

    @Test
    @Order(3)
    public void getTradeByIdTest() throws Exception {
        Trade t = tradeService.getTradeById(trade.getTradeId());
        assertThat(t.getTradeId()).isEqualTo(trade.getTradeId());
    }

    @Test
    @Order(4)
    public void getTradeByNullIdTest() {
        try {
            tradeService.getTradeById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The trade was not found");
        }
    }

    @Test
    @Order(5)
    public void deleteTradeByIdTest() throws Exception {
        tradeService.deleteTradeById(trade.getTradeId());
        List<Trade> trades = tradeService.getTrades();
        assertThat(trades.size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    public void deleteTradeByNullIdTest() {
        try {
            tradeService.deleteTradeById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid trade Id : " + 10);
        }
    }
}
