package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;
import java.util.Optional;

public interface ICurvePointService {

    public List<CurvePoint> findAll();

    public CurvePoint save(CurvePoint curvePoint);

    public Optional<CurvePoint> findById(Integer id);

    public void delete(CurvePoint curvePoint);
}
