package com.hyqin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyqin.domain.Comments;
import com.hyqin.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comments> implements CommentService {
}
