package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.Lab;
import com.fzsx.vlib.entity.Equipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * vlib - LabMapper.xml
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface LabMapper {
    Equipment getLabNumber(String labNumber);

    int saveLabInfo(Lab lab);

    List queryLabList(String labName);

    int delete(Lab lab);

    List<Map> detail(Lab lab);

    int update(Lab lab);

    int getLabId(String labNumber);
}
