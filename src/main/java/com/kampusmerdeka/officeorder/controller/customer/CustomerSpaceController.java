package com.kampusmerdeka.officeorder.controller.customer;

import com.kampusmerdeka.officeorder.repository.ComplexRepository;
import com.kampusmerdeka.officeorder.repository.UnitRepository;
import com.kampusmerdeka.officeorder.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/customer/spaces")
public class CustomerSpaceController {
    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public ResponseEntity<Object> getSpaces() {
        return spaceService.getSpace();
    }
}
