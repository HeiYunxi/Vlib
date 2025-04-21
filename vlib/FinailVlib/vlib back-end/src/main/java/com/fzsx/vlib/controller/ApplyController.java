package com.fzsx.vlib.controller;

import com.fzsx.vlib.entity.Apply;

import com.fzsx.vlib.entity.Lab;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IApplyService;
import com.fzsx.vlib.service.ILabService;
import com.fzsx.vlib.utils.ExcelUtil;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib - ApplyController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */

/**
 * 申请接口，为了简便，这里的申请和审批是同一个人，实际是不同的角色
 */
@Validated
@RestController
@RequestMapping("apply")
public class ApplyController {

    @Autowired
    private IApplyService iApplyService;
    @Autowired
    private ILabService iLabService;

    /**
     * 新增（保存不提交）申请
     *
     * @param
     * @return
     */
    @RequestMapping("/insertApply")
    @ResponseBody
    public Map<String, Object> insertApply(@RequestBody Apply apply) {
        if (null != apply) {
            if (null != apply.getApplyId() && !"".equals(apply.getApplyId())) {
                int flag = iApplyService.insertApply(apply);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 提交申请
     *
     * @param map
     * @return
     */
    @RequestMapping("/submitApply")
    @ResponseBody
    public Map<String, Object> submitApply(@RequestBody Map map) throws ParseException {

        Apply apply = new Apply();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String labNumber = map.get("labNumber").toString() == null ? "" : map.get("labNumber").toString();
        int userId = map.get("usersId").toString() == null ? 0 : Integer.parseInt(map.get("usersId").toString());
        int applyDay = map.get("applyDay").toString() == null ? 0 : Integer.parseInt(map.get("applyDay").toString());
        String applyReason = map.get("applyReason").toString() == null ? "" : map.get("applyReason").toString();
        Date applyDatetime = ft.parse(map.get("applyDatetime").toString());
//        int applyStatus = map.get("applyStatus").toString() == null ? 0 : Integer.parseInt(map.get("applyStatus").toString());
        String reviewComments = map.get("reviewComments").toString() == null ? "" : map.get("reviewComments").toString();

        if (map.get("reviewDatetime").toString() != null && map.get("reviewDatetime").toString() != "") {
            Date reviewDatetime = ft.parse(map.get("reviewDatetime").toString());
            apply.setReviewDatetime(reviewDatetime);
        }

        Date date = new Date();
        apply.setUserId(userId);
        apply.setApplyNum(String.valueOf(date.getTime()));
        apply.setApplyDay(applyDay);
        apply.setApplyReason(applyReason);
        apply.setApplyDatetime(applyDatetime);
//        apply.setApplyStatus(3);
        apply.setReviewComments(reviewComments);

        if (null != apply) {
            if (null != apply.getApplyId() && !"".equals(apply.getApplyId())) {
                iApplyService.insertApply(apply);
                int applyId = iApplyService.getApplyId(apply);
                apply.setApplyId(applyId);
                int labId = iLabService.getLabId(labNumber);
                int flag = iApplyService.submitApply(apply, labId);
                Map<String, Object> maps = new HashMap<>();
                maps.put("data", flag);
                return maps;
            } else {
                return null;
            }
        }
        return null;
    }


    /**
     * 删除申请
     *
     * @param apply
     * @return
     */
    @RequestMapping("/deleteApplyById")
    @ResponseBody
    public Map<String, Object> deleteApplyById(@RequestBody Apply apply) {
        if (null != apply) {
            if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {
                int flag = iApplyService.deleteApplyById(apply);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }


    /**
     * 更新申请
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateApply")
    @ResponseBody
    public Map<String, Object> updateApply(@RequestBody Map map) throws ParseException {
        Apply apply = new Apply();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        String labNumber = map.get("labNumber").toString() == null ? "" : map.get("labNumber").toString();
//        int userId = map.get("usersId").toString() == null ? 0 : Integer.parseInt(map.get("usersId").toString());
        int applyId = map.get("applyId").toString() == null ? 0 : Integer.parseInt(map.get("applyId").toString());
        int applyDay = map.get("applyDay").toString() == null ? 0 : Integer.parseInt(map.get("applyDay").toString());
        String applyReason = map.get("applyReason").toString() == null ? "" : map.get("applyReason").toString();
        Date applyDatetime = ft.parse(map.get("applyDatetime").toString());
        int applyStatus = map.get("applyStatus").toString() == null ? 0 : Integer.parseInt(map.get("applyStatus").toString());
//        String reviewComments = map.get("reviewComments").toString() == null ? "" : map.get("reviewComments").toString();

//        if (map.get("reviewDatetime").toString() != null && map.get("reviewDatetime").toString() != "") {
//            Date reviewDatetime = ft.parse(map.get("reviewDatetime").toString());
//            apply.setReviewDatetime(reviewDatetime);
//        }

        Date date = new Date();
//        apply.setUserId(userId);
//        apply.setApplyNum(date.toString());
        apply.setApplyId(applyId);
        apply.setApplyDay(applyDay);
        apply.setApplyReason(applyReason);
        apply.setApplyDatetime(applyDatetime);
        apply.setApplyStatus(applyStatus);

        if (applyStatus == 3) {
            if (null != apply) {
                if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {
                    int flag = iApplyService.updateApply(apply);
                    Map<String, Object> maps = new HashMap<>();
                    if (flag > 0) {
                        maps.put("data", null);
                        maps.put("code", 200);
                        maps.put("message", "成功");
                    } else {
                        maps.put("code", 500);
                        maps.put("message", "失败");
                    }
                    return maps;
                } else {
                    return null;
                }
            }
            return null;
        }else{
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", null);
        maps.put("code", 403);
        maps.put("message", "已审批，不可修改");
        return maps;
    }

}

    /**
     * 审批申请
     *
     * @param reviewApplyMap
     * @return
     */
    @RequestMapping("/reviewApply")
    @ResponseBody
    public Map<String, Object> reviewApply(@RequestBody Map reviewApplyMap) {
        Apply apply = new Apply();

        int applyId = reviewApplyMap.get("applyId").toString() == null ? 0 : Integer.parseInt(reviewApplyMap.get("applyId").toString());
        int applyStatus = reviewApplyMap.get("applyStatus").toString() == null ? 0 : Integer.parseInt(reviewApplyMap.get("applyStatus").toString());
        String reviewComments;
        if (Integer.parseInt(reviewApplyMap.get("reviewComments").toString()) == 1) {
            reviewComments = "不同意";
            apply.setReviewComments(reviewComments);
        } else if (Integer.parseInt(reviewApplyMap.get("reviewComments").toString()) == 2) {
            reviewComments = "同意";
            apply.setReviewComments(reviewComments);
        }
        apply.setApplyId(applyId);
//        apply.setApplyStatus(applyStatus);

        if (null != apply) {
            if (applyStatus == 3) {
                if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {

                    int flag = iApplyService.reviewApply(apply);
                    Map<String, Object> map = new HashMap<>();
                    map.put("data", flag);
                    map.put("code", 200);
                    map.put("message", "审批成功");
                    return map;
                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 500);
                    map.put("message", "审批失败");
                    return null;
                }
            } else {
                int flag = -1;
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                map.put("code", 403);
                map.put("message", "已完成审批，不可再次审批");
                return map;
            }
        }
        return null;
    }

    /**
     * 撤回申请: 从提交表中删除（个人可见，教师看不到）
     * 存在apply表中，不存在submit表中
     *
     * @param apply
     * @return
     */
    @RequestMapping("/withDrawApply")
    @ResponseBody
    public Map<String, Object> withDrawApply(@RequestBody Apply apply) {
//        int applyId = infoMap.get("applyId").toString() == null ? 0 : Integer.parseInt(infoMap.get("applyId").toString());
//        Apply apply = new Apply();
//        apply.setApplyId(applyId);
        if (null != apply) {
            if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {
                int flag = iApplyService.withDrawApply(apply);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 申请详情信息
     *
     * @param apply 申请实体类
     * @return Map {data，用户信息列表}
     * @path apply/detail
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Apply apply) {
        if (null != apply) {
            //用户ID不为空时，向下层传达查询用户信息列表
            if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {
                List<Map> applyList = iApplyService.detail(apply);
                Map<String, Object> map = new HashMap<>();
                map.put("data", applyList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 申请详情信息
     *
     * @param applyMap 申请实体类
     * @return Map {data，用户信息列表}
     * @path apply/reviewDetail
     */
    @RequestMapping(value = "/reviewDetail")
    @ResponseBody
    public Map<String, Object> reviewDetail(@RequestBody Map applyMap) {
        Apply apply = new Apply();

        int applyId = applyMap.get("applyId").toString() == null ? 0 : Integer.parseInt(applyMap.get("applyId").toString());
        apply.setApplyId(applyId);

        if (null != apply) {
            //用户ID不为空时，向下层传达查询用户信息列表
            if (null != String.valueOf(apply.getApplyId()) && !"".equals(String.valueOf(apply.getApplyId()))) {
                List<Map> applyList = iApplyService.detail(apply);
                Map<String, Object> map = new HashMap<>();
                map.put("data", applyList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 查询申请信息，列出所有申请
     *
     * @param map
     * @return
     */
    @RequestMapping("/Applylist")
    @ResponseBody
    public Map<String, Object> selectApplyList(@RequestBody Map<String, Object> map) {

        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String applyNum = map.get("applyNum").toString() == null ? "" : map.get("applyNum").toString();
        //int flag = map.get("flag").toString() == null ? 0 : Integer.parseInt(map.get("flag").toString());
//        int flag = map.get("flag").toString()=="true" ? 1 : 0;

        Apply apply = new Apply();
        apply.setApplyNum(applyNum);
        PageInfo<Apply> applyPageInfo = iApplyService.getApplyByHelperList(pageNum, pageSize, apply);
        //得到分页中的user条目对象
        List<Apply> pageList = applyPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", applyPageInfo.getTotal());
        return res;
    }


    /**
     * 通过用户id，查询申请列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/listApplysByUserId")
    @ResponseBody
    public Map<String, Object> listApplysByUserId(@RequestBody Map<String, Object> map) {
        int applyStatus =-1;
        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String labName = map.get("labName").toString() == null ? "" : map.get("labName").toString();

        if (map.get("applyStatus") == null||map.get("applyStatus") == "") {}else {
            applyStatus = map.get("applyStatus").toString() == null ? 0 : Integer.parseInt(map.get("applyStatus").toString());
        }

        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setApplyStatus(applyStatus);
        PageInfo<Map> applyPageInfo = iApplyService.listApplysByUserId(pageNum, pageSize, apply, labName);


        List<Map> pageList = applyPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", applyPageInfo.getTotal());
        return res;

//        return iApplyService.listApplysByUserId(userId, applyNum, applyStatus);
    }



    /**
     * 根据Id查询申请信息
     *
     * @param applyId
     * @return
     */
    @RequestMapping("/getApplyById")
    @ResponseBody
    public Apply getApplyById(@RequestBody int applyId) {

        Apply apply = new Apply();
        apply.setApplyId(applyId);
        return iApplyService.getApplyById(apply);
    }


    /**
     * 通过条件，列出教师端所有申请
     *
     * @return
     */
    @RequestMapping("/listApplysTeacherByInfor")
    @ResponseBody
    public Map<String, Object> listApplysTeacherByInfor(@RequestBody Map<String, Object> map) {
        String userName;
        int status = -1;

        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());

        if (map.get("userName") == null) {
            userName = "";
        } else {
            userName = map.get("userName").toString() == null ? "" : map.get("userName").toString();
        }

        if (map.get("applyStatus") == null||map.get("applyStatus")=="") {

        } else {
            status = map.get("applyStatus").toString() == null ? 0 : Integer.parseInt(map.get("applyStatus").toString());
        }
        int managerId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        Apply apply = new Apply();

        apply.setApplyStatus(status);
        PageInfo<Map> applyPageInfo = iApplyService.listApplysTeacherByInfor(pageNum, pageSize, apply, managerId,userName);

        List<Map> pageList = applyPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", applyPageInfo.getTotal());
        return res;
    }

    //
    @GetMapping("/exportApply")
    public void exportApply(HttpServletResponse response,
                            int userId,
                            String applyNum,
                            int applyStatus) throws IOException {
        List<Apply> list = iApplyService.exportApply(userId, applyNum, applyStatus);
        ExcelUtil.outputExcel(response, list, Apply.class, "申请列表.xls");
    }
}
