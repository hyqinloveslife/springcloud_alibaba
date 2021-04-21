package com.hyqin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.domain.Comments;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 评论的接口
 * @author: hyqin
 * @create : 2020/9/15
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comments> {
}
