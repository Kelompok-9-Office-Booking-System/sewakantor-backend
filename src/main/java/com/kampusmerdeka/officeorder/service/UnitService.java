package com.kampusmerdeka.officeorder.service;

import com.kampusmerdeka.officeorder.constant.FileDirectoryConstant;
import com.kampusmerdeka.officeorder.dto.repsonse.UnitImageResponse;
import com.kampusmerdeka.officeorder.dto.repsonse.UnitResponse;
import com.kampusmerdeka.officeorder.dto.request.UnitRequest;
import com.kampusmerdeka.officeorder.entity.Building;
import com.kampusmerdeka.officeorder.entity.Unit;
import com.kampusmerdeka.officeorder.entity.UnitImage;
import com.kampusmerdeka.officeorder.exception.DataNotFoundException;
import com.kampusmerdeka.officeorder.repository.BuildingRepository;
import com.kampusmerdeka.officeorder.repository.UnitImageRepository;
import com.kampusmerdeka.officeorder.repository.UnitRepository;
import com.kampusmerdeka.officeorder.util.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private UnitImageRepository unitImageRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    public ResponseEntity<Object> getAll() {
        Iterable<Unit> buildingIterable = unitRepository.findAll();

        List<UnitResponse> result = new ArrayList<>();
        buildingIterable.forEach(unit -> result.add(getResponse(unit)));

        return ResponseUtil.ok("list unit", result);
    }

    public ResponseEntity<Object> getOne(Long id) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        if (unitOptional.isEmpty()) return ResponseUtil.notFound("unit not found");

        UnitResponse response = getResponse(unitOptional.get());
        return ResponseUtil.ok("list unit", response);
    }

    @Transactional
    public ResponseEntity<Object> createOne(UnitRequest request) {
        try {
            Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
            if (buildingOptional.isEmpty()) return ResponseUtil.notFound("building not found");

            Building building = buildingOptional.get();
            Unit unit = Unit.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .capacity(request.getCapacity())
                    .length(request.getLength())
                    .width(request.getWidth())
                    .height(request.getHeight())
                    .building(building)
                    .build();

            unit = unitRepository.saveAndFlush(unit);

            List<UnitImage> buildingImages = new ArrayList<>();
            if (request.getImages() != null && request.getImages().size() > 0) {
                Unit savedUnit = unit;
                request.getImages().forEach(file -> {
                    if (file != null && !file.isEmpty()) {
                        try {
                            buildingImages.add(UnitImage.builder()
                                    .name(file.getOriginalFilename().replaceAll(" ", "-"))
                                    .unit(savedUnit)
                                    .path(FileUploadUtil.saveFile(
                                            FileDirectoryConstant.IMAGE_BUILDING_DIR,
                                            file.getOriginalFilename().replaceAll(" ", "-"),
                                            file)
                                    )
                                    .build()
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            unitImageRepository.saveAll(buildingImages);

            UnitResponse response = getResponse(unitRepository.saveAndFlush(unit));

            return ResponseUtil.ok("unit saved successfully", response);

        } catch (DataNotFoundException e) {
            return ResponseUtil.notFound(e.getLocalizedMessage());
        }
    }

    @SneakyThrows
    public ResponseEntity<Object> updateOne(Long id, UnitRequest request) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        if (unitOptional.isEmpty()) return ResponseUtil.notFound("unit not found");

        Optional<Building> buildingOptional = buildingRepository.findById(request.getBuildingId());
        if (buildingOptional.isEmpty()) return ResponseUtil.notFound("building not found");

        Building building = buildingOptional.get();
        Unit unit = unitOptional.get();

        Unit unitUpdate = Unit.builder()
                .id(unit.getId())
                .name(request.getName())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .length(request.getLength())
                .width(request.getWidth())
                .height(request.getHeight())
                .building(building)
                .build();

        UnitResponse response = getResponse(unitRepository.saveAndFlush(unitUpdate));

        return ResponseUtil.ok("unit updated successfully", response);
    }

    public ResponseEntity<Object> deleteOne(Long id) {
        Optional<Unit> buildingOptional = unitRepository.findById(id);
        if (buildingOptional.isEmpty()) return ResponseUtil.notFound("unit not found");

        Unit unit = buildingOptional.get();
        Long buildingId = unit.getId();

        unitRepository.deleteById(id);

        unitImageRepository.findByUnit_Id(buildingId).forEach(buildingImage -> FileDeleteUtil.delete(buildingImage.getPath()));

        return ResponseUtil.ok("unit deleted successfully");
    }

    private UnitResponse getResponse(Unit unit) {
        List<UnitImageResponse> images = new ArrayList<>();
        unitImageRepository.findByUnit(unit).forEach(buildingImage -> images.add(UnitImageResponse.builder()
                .id(buildingImage.getId())
                .image(Helpers.resourceToBase64(FileDownloadUtil.getFileAsResource(buildingImage.getPath())))
                .build()));

        return UnitResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .description(unit.getDescription())
                .capacity(unit.getCapacity())
                .length(unit.getLength())
                .width(unit.getWidth())
                .height(unit.getHeight())
                .buildingId(unit.getBuilding().getId())
                .building(unit.getBuilding().getName())
                .images(images)
                .build();
    }
}
