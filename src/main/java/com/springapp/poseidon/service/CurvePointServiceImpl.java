package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.CurvePoint;
import com.springapp.poseidon.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements CurvePointService{

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> getCurvePoints() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) throws Exception {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if(curvePoint.isPresent()) {
            return curvePoint.get();
        }else {
            throw new Exception("The curve point was not found");
        }
    }

    @Override
    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteCurvePointById(Integer id) throws Exception {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if (curvePoint.isPresent()) {
            curvePointRepository.deleteById(id);
        }else {
            throw new Exception("Invalid curve point Id : " + id);
        }
    }

}
