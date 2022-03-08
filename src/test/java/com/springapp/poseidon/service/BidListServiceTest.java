package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.BidList;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BidListServiceTest {

    @Autowired
    private BidListService bidListService;

    private BidList bid;

    @BeforeAll
    private void setUp() {
        bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10d);
    }

    @Test
    @Order(1)
    public void addBidTest() {
        bidListService.addBid(bid);
        assertNotNull(bid.getBidListId());
        assertEquals(bid.getBidQuantity(), 10d, 10d);
    }

    @Test
    @Order(2)
    public void getBidListsTest() {
        List<BidList> listResult = bidListService.getBidLists();
        assertTrue(listResult.size() > 0);
    }

    @Test
    @Order(3)
    public void getBidByIdTest() throws Exception {
        BidList bidList = bidListService.getBidById(bid.getBidListId());
        assertThat(bidList.getBidListId()).isEqualTo(bid.getBidListId());
    }

    @Test
    @Order(4)
    public void getBidByNullIdTest() {
        try {
            bidListService.getBidById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The bid was not found");
        }
    }

    @Test
    @Order(5)
    public void deleteBidByIdTest() throws Exception {
        bidListService.deleteBidById(bid.getBidListId());
        List<BidList> listResult = bidListService.getBidLists();
        assertThat(listResult.size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    public void deleteBidByNullIdTest() {
        try {
            bidListService.deleteBidById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid bid Id : " + 10);
        }
    }
}
