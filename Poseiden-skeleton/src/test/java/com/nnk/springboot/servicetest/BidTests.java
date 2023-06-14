package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.impl.BidListService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class BidTests {

	@MockBean
	private BidListRepository bidListRepository;

	@Autowired
	private BidListService bidListService;

	@Test
	public void bidListTest() {
		BidList bid = new BidList(1,"Account Test", "Type Test", 10d);

		// Save
		when(bidListRepository.save(bid)).thenReturn(bid);
		bid = bidListService.save(bid);
		Assert.assertNotNull(bid.getBidListId());
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		when(bidListRepository.save(bid)).thenReturn(bid);
		bid = bidListService.save(bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listTest = new ArrayList<>();
		listTest.add(bid);
		when(bidListRepository.findAll()).thenReturn(listTest);
		List<BidList> listResult = bidListService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		doNothing().when(bidListRepository).delete(bid);
		bidListService.delete(bid);

		verify(bidListRepository,times(1)).delete(bid);
	}
}
