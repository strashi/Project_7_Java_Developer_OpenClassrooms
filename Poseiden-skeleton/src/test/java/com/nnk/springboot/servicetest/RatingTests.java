package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.impl.RatingService;
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
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingService ratingService;
	@MockBean
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {
		Rating rating = new Rating(1,"Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		when(ratingRepository.save(rating)).thenReturn(rating);
		rating = ratingService.save(rating);
		Assert.assertNotNull(rating.getId());
		Assert.assertTrue(rating.getOrderNumber() == 10);

		// Update
		rating.setOrderNumber(20);
		when(ratingRepository.save(rating)).thenReturn(rating);
		rating = ratingService.save(rating);
		Assert.assertTrue(rating.getOrderNumber() == 20);

		// Find
		List<Rating> listTest = new ArrayList<>();
		listTest.add(rating);
		when(ratingRepository.findAll()).thenReturn(listTest);
		List<Rating> listResult = ratingService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// FindById
		Optional<Rating> optRating = Optional.of(rating);
		when(ratingRepository.findById(1)).thenReturn(optRating);
		Optional<Rating> optRatingTest = ratingService.findById(1);
		Assert.assertSame(optRating,optRatingTest);

		// Delete
		doNothing().when(ratingRepository).delete(rating);
		ratingService.delete(rating);
		verify(ratingRepository,times(1)).delete(rating);
	}
}
