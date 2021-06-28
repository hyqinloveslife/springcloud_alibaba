package com.hyqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysErrorLogDao;
import com.hyqin.dao.SysLogDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.BaseDTO;
import com.hyqin.dto.SysErrorLogDTO;
import com.hyqin.dto.SysErrorLogQueryDTO;
import com.hyqin.dto.SysLogQueryDTO;
import com.hyqin.entity.SysLog;
import com.hyqin.util.MongoDBHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description 系统操作日志管理
 * @author: huangyeqin
 * @create : 2021/6/22  19:34
 */
@Slf4j
@Api_System
@Api(tags = "系统日志管理")
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

    @Resource
    private SysLogDao sysLogDao;

    @Resource
    private SysErrorLogDao errorLogDao;

    @Resource
    private MongoDBHelper mongoDBHelper;

    @ApiOperation("列表查询-操作日志信息")
    @PostMapping("/list-log")
    public R list(@RequestBody SysLogQueryDTO queryDTO) throws Exception {
        List<SysLog> sysLogs = sysLogDao.listLogs(queryDTO);
        return R.success(sysLogs);
    }

    @ApiOperation("列表分页查询-操作日志信息")
    @PostMapping("/list-log-page")
    public R listPage(@RequestBody SysLogQueryDTO queryDTO) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().like(StringUtils.isNotBlank(queryDTO.getOperation()),
                SysLog::getOperation, queryDTO.getOperation());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getUsername()),
                SysLog::getUsername, queryDTO.getUsername());
        queryWrapper.lambda().orderByDesc(SysLog::getCreatedTime);
        IPage<SysLog> sysLogs = sysLogDao.selectPage(new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), queryWrapper);
        return R.success(sysLogs);
    }

    @ApiOperation("保存操作日志")
    @PostMapping("/save-log")
    public R save(@RequestBody SysLog saveLog) {
        int result = sysLogDao.insertSelective(saveLog);
        return result == 0 ? R.error("新增失败") : R.success("保存");
    }


    @ApiOperation("分页查询异常日志")
    @PostMapping("/list-error-log")
    public R listErrlog(@RequestBody SysErrorLogQueryDTO queryDTO) {
        //QueryWrapper<SysErrorLog> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getErrNo()), SysErrorLog::getErrNo, queryDTO.getErrNo());
        //queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getSno()), SysErrorLog::getSno, queryDTO.getSno());
        //IPage<SysErrorLog> page = errorLogDao.selectPage(new Page<>(), queryWrapper);
        Query query = new Query();
        //Criteria criteria = Criteria.where("err_no").is(queryDTO.getErrNo());
        //
        //query.addCriteria(criteria);
        // 根据创建时间倒序
        //query.with(Sort.by(Sort.Order.asc("createdTime")));
        query.with(Sort.by("createdTime"));

        Map<String, Object> page = mongoDBHelper.findByPage(query, queryDTO
                , SysErrorLogDTO.class, "sys_error_log");
        return R.success(page);
    }
}
