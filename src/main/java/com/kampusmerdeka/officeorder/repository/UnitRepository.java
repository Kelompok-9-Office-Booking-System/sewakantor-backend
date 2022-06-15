package com.kampusmerdeka.officeorder.repository;

import com.kampusmerdeka.officeorder.dto.repsonse.SpaceResponse;
import com.kampusmerdeka.officeorder.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query("SELECT NEW com.kampusmerdeka.officeorder.dto.repsonse.SpaceResponse(" +
                "b.id, " +
                "b.name, " +
                "b.address, " +
                "(SELECT CAST(COUNT(*) as int) FROM Unit WHERE building.id = b.id), " +
                "(SELECT ROUND(AVG(r.star), 1) FROM Review r WHERE r.unit.building = b), " +
                "(SELECT MIN(p.price) FROM Price p WHERE p.unit.building = b), " +
                "(SELECT MIN(bi.path) FROM BuildingImage bi WHERE bi.building = b)" +
            ") " +
            "FROM Unit u " +
            "INNER JOIN Building b ON u.building = b " +
            "GROUP BY b")
    Iterable<SpaceResponse> findAvailableSpace();
}