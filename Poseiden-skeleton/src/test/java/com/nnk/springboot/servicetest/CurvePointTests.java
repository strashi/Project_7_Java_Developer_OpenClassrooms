package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.impl.CurvePointService;
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
public class CurvePointTests {

	@MockBean
	private CurvePointRepository curvePointRepository;

	@Autowired
	private CurvePointService curvePointService;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(1,10, 10d, 30d);

		// Save
		when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
		curvePoint = curvePointService.save(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

		curvePoint = curvePointService.save(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listTest = new ArrayList<>();
		listTest.add(curvePoint);
		when(curvePointRepository.findAll()).thenReturn(listTest);
		List<CurvePoint> listResult = curvePointService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// FindById
		Optional<CurvePoint> optCurvePoint = Optional.of(curvePoint);
		when(curvePointRepository.findById(1)).thenReturn(optCurvePoint);
		Optional<CurvePoint>optCurvePointTest = curvePointService.findById(1);
		Assert.assertSame(optCurvePoint,optCurvePointTest);

		// Delete
		doNothing().when(curvePointRepository).delete(curvePoint);
		curvePointService.delete(curvePoint);

		verify(curvePointRepository,times(1)).delete(curvePoint);
	}

}
