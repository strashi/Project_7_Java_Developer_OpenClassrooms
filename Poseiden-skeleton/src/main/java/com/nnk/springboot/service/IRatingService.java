package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;

import java.util.List;
import java.util.Optional;

public interface IRatingService {

    public List<Rating> findAll() ;

    public Rating save(Rating rating) ;

    public Optional<Rating> findById(Integer id) ;

    public void delete(Rating rating);
}
