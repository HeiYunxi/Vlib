package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Lab;
import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.mapper.LabMapper;
import com.fzsx.vlib.mapper.MenuMapper;
import com.fzsx.vlib.service.ILabService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * vlib - LabServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class LabServiceImpl implements ILabService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    LabMapper labMapper;

    @Override
    public int saveLabInfo(Lab lab) {
        String labNumber = lab.getLabNumber();
        System.out.println("test:" + labNumber);
        Equipment flag = labMapper.getLabNumber(labNumber);
        if (flag != null) {
            return 0;
        } else {
            lab.setCreate_time(new Date());
            lab.setUpdate_time(new Date());
            return labMapper.saveLabInfo(lab);
        }
    }

    @Override
    public PageInfo<Lab> getLabByHelperList(int currentPage, int pageSize, String labName) {
        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List labList = labMapper.queryLabList(labName);
        //得到分页的结果对象
        PageInfo labListPageInfo = new PageInfo<>(labList);
        return labListPageInfo;
    }

    @Override
    public int delete(Lab lab) {
        return labMapper.delete(lab);
    }

    @Override
    public List<Map> detail(Lab lab) {
        return labMapper.detail(lab);
    }

    @Override
    public int update(Lab lab) {
        lab.setUpdate_time(new Date());
        return labMapper.update(lab);
    }

    @Override
    public int getLabId(String labNumber) {
        return labMapper.getLabId(labNumber);
    }
}
