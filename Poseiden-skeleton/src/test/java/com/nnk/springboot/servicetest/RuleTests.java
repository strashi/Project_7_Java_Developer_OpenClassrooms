package com.nnk.springboot.servicetest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.impl.RuleNameService;
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
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;
	@MockBean
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName(1,"Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		when(ruleNameRepository.save(rule)).thenReturn(rule);
		rule = ruleNameService.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		when(ruleNameRepository.save(rule)).thenReturn(rule);
		rule = ruleNameService.save(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listTest = new ArrayList<>();
		listTest.add(rule);
		when(ruleNameRepository.findAll()).thenReturn(listTest);
		List<RuleName> listResult = ruleNameService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// FindById
		Optional<RuleName> optRule = Optional.of(rule);
		when(ruleNameRepository.findById(1)).thenReturn(optRule);
		Optional<RuleName> optRuleTest = ruleNameService.findById(1);
		Assert.assertSame(optRule,optRuleTest);

		// Delete
		doNothing().when(ruleNameRepository).delete(rule);
		ruleNameService.delete(rule);

		verify(ruleNameRepository,times(1)).delete(rule);
	}
}
