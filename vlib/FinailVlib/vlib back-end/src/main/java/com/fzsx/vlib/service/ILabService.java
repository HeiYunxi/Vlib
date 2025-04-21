package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Lab;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * vlib - ILabService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface ILabService {
    int saveLabInfo(Lab lab);

    PageInfo<Lab> getLabByHelperList(int pageNum, int pageSize, String labName);

    int delete(Lab lab);

    List<Map> detail(Lab lab);

    int update(Lab lab);

    int getLabId(String labNumber);
}
