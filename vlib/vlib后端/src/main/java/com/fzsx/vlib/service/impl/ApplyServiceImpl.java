package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Apply;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Lab;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.fzsx.vlib.mapper.ApplyMapper;
import com.fzsx.vlib.service.IApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * vlib - ApplyServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Resource
    private ApplyMapper applyMapper;


    @Override
    public int insertApply(Apply apply) {
        apply.setApplyStatus(0);

        //提交到Apply表，不提交到审批表
        int num = applyMapper.insert(apply);
        return num;
    }

    /**
     * 提交申请：当申请为未审核状态时，可以提交，提交后状态改为待审核
     * status 0-待提交 1-驳回 2-通过 3-待审批 4-撤回
     * flag 状态不为未审核(提交错误) -1; 提交失败 0; 提交成功 num
     *
     * @param apply 申请实体类
     * @return int - 删除数量
     */
    @Override
    public int submitApply(Apply apply,int labId) {
        if (apply.getApplyStatus() != 0) {
            return -1;
        }
        apply.setApplyStatus(3);
        applyMapper.update(apply);
        int num = applyMapper.submit(apply,labId);
        return num;
    }

    /**
     * 删除申请
     *
     * @param apply 申请实体类
     * @return int - 删除数量
     */
    public int deleteApplyById(Apply apply) {
        int num = applyMapper.delete(apply);
        return num;
    }

    /**
     * 编辑更新申请
     *
     * @param apply 申请实体类
     * @return int - 编辑数量
     */
    @Override
    public int updateApply(Apply apply) {
//        apply.setUpdate_time(new Date());
        int num = applyMapper.update(apply);

        return num;
    }

    /**
     * 审批申请
     *
     * @param apply
     * @return
     */
    @Override
    public int reviewApply(Apply apply) {
        if (apply.getReviewComments()=="不同意"){
            apply.setApplyStatus(1);
        }else{
            apply.setApplyStatus(2);
        }
        apply.setReviewDatetime(new Date());
        int num = applyMapper.reviewApply(apply);
        return num;
    }


    /**
     * 撤回申请：当状态为待审核时可以撤回
     * status 0-待提交 1-驳回 2-通过 3-待审批 4-撤回
     * flag： -1 不可撤回（不为待审核的申请-已完成审核或未提交）  0（撤回识别）  num（撤回成功-数量）
     *
     * @param apply
     * @return
     */
    @Override
    public int withDrawApply(Apply apply) {

        if (apply.getApplyStatus() == 1||apply.getApplyStatus() == 2||apply.getApplyStatus() == 4) {
            return -1;
        }
        apply.setApplyStatus(4);
        applyMapper.delete(apply);
        int num = applyMapper.withDrawApply(apply);
        return num;
    }

    public PageInfo<Apply> getApplyByHelperList(int currentPage, int pageSize, Apply apply){
        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<Apply> applyList = applyMapper.queryApplyList(apply);
        //得到分页的结果对象
        PageInfo<Apply> applyListPageInfo = new PageInfo<>(applyList);
        return applyListPageInfo;
    }


    @Override
    public PageInfo<Map>  listApplysByUserId(int pageNum, int pageSize, Apply apply, String labName) {
        PageHelper.startPage(pageNum, pageSize);
        //分页查询
        List<Map> applyList = applyMapper.queryUserApplyList(apply, labName);
        //得到分页的结果对象
        PageInfo<Map> applyListPageInfo = new PageInfo<>(applyList);
        return applyListPageInfo;
    }

    @Override
    public String  selectLabName(int applyId) {
        return applyMapper.selectLabName(applyId);
    }

    @Override
    public PageInfo<Map> listApplysTeacherByInfor(int pageNum, int pageSize, Apply apply, int managerId,String userName) {
        PageHelper.startPage(pageNum, pageSize);
        //分页查询
        List<Map> applyList = applyMapper.querySubmitApplyList(apply,managerId,userName);
        //得到分页的结果对象
        PageInfo<Map> applyListPageInfo = new PageInfo<>(applyList);
        return applyListPageInfo;

        //        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
//        if (null != applyNum) {
//            // 模糊查询 like '%user_num' = #(applyNum)
//            queryWrapper.like("apply_num", applyNum);
//        }
//        if (null != applyStatu) {
//            queryWrapper.eq("apply_status", applyStatu);
//        }
//        queryWrapper.ne("apply_status", 4);
//        List<ApplyEntity> listApplys = applyMapper.listApplysTeacher(queryWrapper);
//        return ResultDto.success(listApplys);
    }

    @Override
    public List<Apply> exportApply(int userId, String applyNum, int applyStatus) {
        return applyMapper.listApplys(userId,applyNum,applyStatus);
    }

    @Override
    public Apply getApplyById(Apply apply) {
        return applyMapper.getApplyById(apply);
    }

    @Override
    public List<Map> detail(Apply apply) {
        return applyMapper.detail(apply);
    }

    @Override
    public int getApplyId(Apply apply) {
        return applyMapper.getApplyId(apply);
    }


}

