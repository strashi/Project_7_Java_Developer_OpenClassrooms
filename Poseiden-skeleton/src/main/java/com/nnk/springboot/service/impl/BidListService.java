package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService implements IBidListService {

    @Autowired
    private BidListRepository bidListRepository;
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bid) {
        return bidListRepository.save(bid);
    }

    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    public void delete(BidList bid) {
        bidListRepository.delete(bid);
    }
}
