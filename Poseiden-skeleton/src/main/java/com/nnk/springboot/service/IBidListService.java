package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

public interface IBidListService {

    public List<BidList> findAll();

    public BidList save(BidList bid);

    public Optional<BidList> findById(Integer id);

    public void delete(BidList bid);
}
