package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.Apply;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * vlib - ApplyMapper
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface ApplyMapper {
    /**
     * 新增申请
     *
     * @param apply
     * @return
     */
    int insert(Apply apply);

    /**
     * 提交申请
     *
     * @param apply
     * @return
     */
    int submit(@Param("apply")Apply apply,@Param("labId")int labId);

    int getApplyId(Apply apply);
    /**
     * 删除申请
     *
     * @param apply
     * @return
     */
    int delete(Apply apply);

    /**
     * 编辑更新申请
     *
     * @param apply
     * @return
     */
    int update(Apply apply);

    /**
     * 审批申请
     *
     * @param apply
     * @return
     */
    int reviewApply(Apply apply);

    List<Map> detail(Apply apply);

    /**
     * 撤回申请
     *
     * @param apply
     * @return
     */
    int withDrawApply(Apply apply);

    String selectLabName(int applyId);

    List<Apply> queryApplyList(Apply apply);

    List<Map> queryUserApplyList(@Param("apply")Apply apply, @Param("labName")String labName);

    List<Map> querySubmitApplyList(@Param("apply")Apply apply,  @Param("managerId")int managerId,@Param("userName") String userName);

    List<Apply> listApplys(@Param("userId")int userId, @Param("applyNum")String applyNum, @Param("status")int applyStatus);

    Apply getApplyById(Apply apply);
}
