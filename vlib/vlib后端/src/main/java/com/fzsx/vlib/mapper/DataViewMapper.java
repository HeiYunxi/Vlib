package com.fzsx.vlib.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DataViewMapper {

    List<Map<String,Object>> getCountData();
    Integer equipmentSupplierCount();
    Integer equipmentCount();
    Integer equipmentLabCount();

    List<Map<String, Object>> getBarData();

    List<Map<String, Object>> getPieData();

    List<Map<String, Object>> getColumnarData();

    List<Map<String, Object>> getLineData();
}
