package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Apply;
import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Lab;
import com.fzsx.vlib.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * vlib - IApplyService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface IApplyService {

    /**
     * 新增申请
     * @param apply
     * @return
     */
    int insertApply(Apply apply);

    /**
     * 提交申请信息
     * @return
     */
    int submitApply(Apply apply,int labId);

    /**
     * 删除申请
     * @param apply
     * @return
     */
    int deleteApplyById(Apply apply);

    /**
     * 编辑更新申请信息
     * @return
     */
    int updateApply(Apply apply);

    /**
     * 审批申请
     * @param apply
     * @return
     */
    int reviewApply(Apply apply);


    /**
     * 撤回申请
     * @param
     * @return
     */
    int withDrawApply(Apply apply);



    /**
     * 通过Apply，查询申请信息
     * @return
     */
    PageInfo<Apply> getApplyByHelperList(int currentPage, int pageSize, Apply apply);



    /**
     * 通过用户id，查询申请列表
     * @param userId
     * @return
     */
    PageInfo<Map> listApplysByUserId(int pageNum, int pageSize, Apply apply, String labName);

    String selectLabName(int applyId);

    /**
     * 通过条件，列出教师端所有申请
     * @return
     */
    PageInfo<Map> listApplysTeacherByInfor(int pageNum, int pageSize, Apply apply, int managerId,String userName);


    /**
     * 导出信息
     * @return
     */
    List<Apply> exportApply(int userId, String applyNum, int applyStatus);

    Apply getApplyById(Apply apply);

    List<Map> detail(Apply apply);

    int getApplyId(Apply apply);
}
