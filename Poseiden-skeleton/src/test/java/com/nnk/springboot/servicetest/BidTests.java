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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

	@Mock
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
		BidList bid2 = new BidList(2,"Account Test2", "Type Test2", 30d);
		List<BidList> listTest = new ArrayList<>();
		listTest.add(bid);
		listTest.add(bid2);
		when(bidListRepository.findAll()).thenReturn(listTest);
		List<BidList> listResult = bidListService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		//Integer id = bid.getBidListId();
		//when(bidListRepository.findById(id)).thenReturn(Optional.of(bid));
		BidList bidon = new BidList(2,"Account Test2", "Type Test2", 30d);
		doNothing().when(bidListRepository).delete(any(BidList.class));
		bidListService.delete(bidon);


		//Optional<BidList> bidList = bidListService.findById(id);
		//Assert.assertFalse(bidList.isPresent());
		verify(bidListRepository,times(1)).delete(any(BidList.class));
	}
}
