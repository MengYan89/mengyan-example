package com.mengyan.tc.repository;

import com.mengyan.tc.entity.TimeControlType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeControlTypeRepository extends JpaRepository<TimeControlType, Integer> {
}
