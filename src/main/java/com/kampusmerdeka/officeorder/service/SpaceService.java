package com.kampusmerdeka.officeorder.service;

import com.kampusmerdeka.officeorder.repository.BuildingRepository;
import com.kampusmerdeka.officeorder.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
    @Autowired
    private BuildingRepository buildingRepository;

    public ResponseEntity<Object> getSpace(){



        return ResponseUtil.ok("list space");
    }
}
