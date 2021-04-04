package com.mengyan.tc.repository;

import com.mengyan.tc.entity.DayTimeStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTimeStatisticsRepository extends JpaRepository<DayTimeStatistics, String> {
}
