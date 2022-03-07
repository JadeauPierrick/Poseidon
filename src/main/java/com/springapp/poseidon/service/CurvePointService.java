package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {

    List<CurvePoint> getCurvePoints();

    CurvePoint getCurvePointById(Integer id) throws Exception;

    CurvePoint addCurvePoint(CurvePoint curvePoint);

    void deleteCurvePointById(Integer id) throws Exception;
}
