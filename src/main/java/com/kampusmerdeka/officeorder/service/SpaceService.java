package com.kampusmerdeka.officeorder.service;

import com.kampusmerdeka.officeorder.dto.repsonse.SpaceResponse;
import com.kampusmerdeka.officeorder.entity.Unit;
import com.kampusmerdeka.officeorder.repository.BuildingRepository;
import com.kampusmerdeka.officeorder.repository.UnitRepository;
import com.kampusmerdeka.officeorder.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UnitRepository unitRepository;

    public ResponseEntity<Object> getSpace() {
        Iterable<SpaceResponse> spaceResponses = unitRepository.findAvailableSpace();

        return ResponseUtil.ok("list space", spaceResponses);
    }
}
