package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.CurvePoint;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurvePointServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    private CurvePoint curvePoint;

    @BeforeAll
    private void setUp() {
        curvePoint = new CurvePoint();
        curvePoint.setCurveId(10);
        curvePoint.setTerm(10d);
        curvePoint.setValue(30d);
    }

    @Test
    @Order(1)
    public void addCurvePointTest() {
        curvePointService.addCurvePoint(curvePoint);
        assertNotNull(curvePoint.getId());
        assertThat(curvePoint.getCurveId()).isEqualTo(10);
    }

    @Test
    @Order(2)
    public void getCurvePointsTest() {
        List<CurvePoint> curvePoints = curvePointService.getCurvePoints();
        assertTrue(curvePoints.size() > 0);
    }

    @Test
    @Order(3)
    public void getCurvePointByIdTest() throws Exception {
        CurvePoint cp = curvePointService.getCurvePointById(curvePoint.getId());
        assertThat(cp.getId()).isEqualTo(curvePoint.getId());
    }

    @Test
    @Order(4)
    public void getCurvePointByNullIdTest() {
        try {
            curvePointService.getCurvePointById(10);

        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The curve point was not found");
        }
    }

    @Test
    @Order(5)
    public void deleteCurvePointByIdTest() throws Exception {
        curvePointService.deleteCurvePointById(curvePoint.getId());
        List<CurvePoint> curvePoints = curvePointService.getCurvePoints();
        assertThat(curvePoints.size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    public void deleteCurvePointByNullIdTest() {
        try {
            curvePointService.deleteCurvePointById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid curve point Id : " + 10);
        }
    }
}
