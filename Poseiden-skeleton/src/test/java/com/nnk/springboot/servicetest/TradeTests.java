package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.impl.TradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeService tradeService;
	@MockBean
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade(1,"Trade Account", "Type");

		// Save
		when(tradeRepository.save(trade)).thenReturn(trade);
		trade = tradeService.save(trade);
		Assert.assertNotNull(trade.getTradeId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		when(tradeRepository.save(trade)).thenReturn(trade);
		trade = tradeService.save(trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listTest = new ArrayList<>();
		listTest.add(trade);
		when(tradeRepository.findAll()).thenReturn(listTest);
		List<Trade> listResult = tradeService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// FindById
		Optional<Trade> optTrade = Optional.of(trade);
		when(tradeRepository.findById(1)).thenReturn(optTrade);
		Optional<Trade> optTradeTest = tradeService.findById(1);
		Assert.assertSame(optTrade,optTradeTest);

		// Delete
		doNothing().when(tradeRepository).delete(trade);
		tradeService.delete(trade);

		verify(tradeRepository,times(1)).delete(trade);
	}
}
