package com.hyqin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyqin.config.Api_Business;
import com.hyqin.domain.Comments;
import com.hyqin.domain.config.R;
import com.hyqin.dto.CommentsDTO;
import com.hyqin.mapper.CommentMapper;
import com.hyqin.service.CommentService;
import com.hyqin.utils.BeanHelper;
import com.hyqin.vo.CommentsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description 订单系统的评论模块
 * @author: hyqin
 * @create : 2020/9/15
 */
@Api(value = "订单系统评论管理", tags = "订单系统评论管理")
@Api_Business
@RestController
@RequestMapping("/comment")
public class CommentController {

  @Resource
  private CommentMapper commentMapper;

  @Resource
  private CommentService commentService;

  /**
   * 用了 @RequestBody 这个注解来接收参数后，必须用post方法
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/2/2 14:42
   * @Param : commentsDTO
   * @Result : com.hyqin.domain.config.R<java.util.List<com.hyqin.vo.CommentsVO>>
   */
  @ApiOperation(value = "查询评论列表")
  @PostMapping("/list")
  public R<List<CommentsVO>> list(@RequestBody CommentsDTO commentsDTO) throws Exception {
    QueryChainWrapper<Comments> query = new QueryChainWrapper<Comments>(commentMapper);
    query.eq(StringUtils.isNotBlank(commentsDTO.getAuthor()), "author", commentsDTO.getAuthor());
    query.like(StringUtils.isNotBlank(commentsDTO.getContent()), "content",
        commentsDTO.getContent());

    List<Comments> comments = query.list();

    List<CommentsVO> commentsVOS = BeanHelper.convertToBeanList(comments, CommentsVO.class);

    return R.success(commentsVOS);
  }

  /**
   * 测试一下mybatis-plus的单表操作之insert
   */
  @ApiOperation(value = "保存评论信息")
  @PostMapping("/save")
  public R save(@RequestBody CommentsDTO commentsDTO) {
    Comments comment = new Comments();
    comment.setContent(commentsDTO.getContent());
    comment.setAuthor(commentsDTO.getAuthor());
////        comment.setId(100L);
//        comment.setAuthor("曹操");
//        comment.setContent("治世之能臣");
    commentMapper.insert(comment);
    return R.success("保存成功");
  }

  /**
   * 测试mybatis-plus的批量插入数据
   */
  @ApiOperation(value = "批量插入数据")
  @GetMapping("/savelist")
  public boolean savelist() {
    List<Comments> comments = new ArrayList<>();
    comments.add(new Comments("关公", "关公面前刷大刀"));
    comments.add(new Comments("曹操", "哈哈哈"));
    return commentService.saveBatch(comments);
  }

  /**
   * 测试mybatis-plus的修改记录 <br/> 修改数据，是先根据主键来查询是否存在该记录，如果存在则修改，如果不存在则新增
   */
  @ApiOperation(value = "修改评论信息")
  @GetMapping("/saveorupdate")
  public boolean saveOrUpdate() {
    return commentService.saveOrUpdate(new Comments(1L, "关公", "红脸的关公战长沙"));
  }

  /**
   * 测试一下批量删除
   */
  @ApiOperation(value = "批量删除评论信息")
  @GetMapping("/removeall")
  public boolean remove() {
    Long[] idArray = {2L, 3L};
    return commentService.removeByIds(Arrays.asList(idArray));
  }

  /**
   * 测试一下如何构建queryWrapper <br/> 1、先构建实体类，标识需要更新的字段<br/> 2、然后构建wrapper，这些是更新条件 <br/> ==>  Preparing:
   * UPDATE comments SET content=?, author=? WHERE (author = ?) <br/> ==> Parameters: 一身是胆(String),
   * 赵子龙(String), 曹操(String)  <br/> <==    Updates: 2 <br/>
   */
  @ApiOperation(value = "更新评论信息")
  @GetMapping("/update")
  public boolean update() {
    Comments comments = new Comments("赵子龙", "一身是胆");
    UpdateWrapper<Comments> updateWrapper = new UpdateWrapper<>();

    updateWrapper.eq("author", "曹操");
    return commentService.update(comments, updateWrapper);
  }

  /**
   * 根据id来查询记录 <br/> ==>  Preparing: SELECT id,content,author FROM comments WHERE (author = ?)<br/
   * ==> Parameters: 1(String)<br/ <==    Columns: id, content, author<br/ <==        Row: 102,
   * 张飞穿针，大眼瞪小眼, 1<br/ <==      Total: 1<br/
   */
  @ApiOperation(value = "获取评论信息")
  @GetMapping("/get/{id}")
  public Comments get(@PathVariable("id") String id) {
    QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", id);

    return commentService.getOne(queryWrapper);
  }

  /**
   * 查询列表
   */
  @GetMapping("/list2")
  public List<Comments> list2(@PathVariable("cur") Integer cur,
      @PathVariable("size") Integer size) {
    return commentService.list();
  }

  /**
   * 查询列表
   */
  @ApiOperation(value = "分页查询评论信息")
  @GetMapping("/list3/{cur}/{size}")
  public IPage<Comments> list3(@PathVariable("cur") Integer cur,
      @PathVariable("size") Integer size) {
    IPage<Comments> page = new Page<>(cur, size);
    IPage<Comments> commentsIPage = commentMapper.selectPage(page, null);
    try {
      Thread.sleep(1000);
    } catch (Exception e) {

    }
    return commentsIPage;
  }
}
