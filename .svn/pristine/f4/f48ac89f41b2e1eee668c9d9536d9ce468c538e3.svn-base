package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-1.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findByName(String name, Pageable pageable);

    List<Location> findByStatus(int status);

    List<Location> findByStatusAndCityId(int status, Long parentId);

    List<Location> findByStatusAndRegionId(int status, Long regionId);

    List<Location> findByStatusAndCityId(Integer code, Long cityId, Pageable pageable);
}
