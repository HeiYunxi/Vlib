package com.fzsx.vlib.service;

import java.util.Map;

public interface DataViewService {
    Map<String,Object> getCountData();

    Map<String, Object> getBarData();

    Map<String, Object> getPieData();

    Map<String, Object> getColumnarData();

    Map<String, Object> getBgetLineDataarData();
}
