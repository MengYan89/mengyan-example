package com.mengyan.tc.controller;

import com.mengyan.tc.entity.DayTimeStatistics;
import com.mengyan.tc.entity.TimeControlType;
import com.mengyan.tc.repository.DayTimeStatisticsRepository;
import com.mengyan.tc.repository.TimeControlTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private DayTimeStatisticsRepository dayTimeStatisticsRepository;

    @Autowired
    private TimeControlTypeRepository timeControlTypeRepository;
    @GetMapping("/test")
    public String test() {
        DayTimeStatistics dayTimeStatistics = new DayTimeStatistics();
        dayTimeStatistics.setDtsId("uuid");
        dayTimeStatistics.setUserId(33333L);
        dayTimeStatistics.setDtsDay(new Date());
        dayTimeStatistics.setDtsTimeStart(0);
        dayTimeStatistics.setDtsTimeEnd(230);
        dayTimeStatistics.setDtsTimeUnit(230);
        dayTimeStatistics.setDtsType(0);
        dayTimeStatistics.setModificationTime(new Date());
        dayTimeStatistics.setCreateTime(new Date());
        dayTimeStatistics.setIsDelete(0);
        dayTimeStatisticsRepository.save(dayTimeStatistics);
        return "OK";
    }

    @GetMapping("/test2")
    public String test2() {
        TimeControlType timeControlType = new TimeControlType();
        timeControlType.setTypeId(1);
        timeControlType.setTypeName("游戏");
        timeControlType.setTypeParentId(0);
        timeControlType.setCreateTime(new Date());
        timeControlType.setModificationTime(new Date());
        timeControlType.setIsDelete(0);
        timeControlTypeRepository.save(timeControlType);
        return "OK";
    }
}
