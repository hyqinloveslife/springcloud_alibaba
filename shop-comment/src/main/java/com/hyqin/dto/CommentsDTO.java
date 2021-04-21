package com.hyqin.dto;

import com.hyqin.vo.PageInfo;
import com.hyqin.domain.Comments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/16
 */
@ApiModel(value = "评论dto")
@Data
public class CommentsDTO extends PageInfo<Comments> {
    private Long id;

    @ApiModelProperty(value = "评论人")
    private String author;

    @ApiModelProperty(value = "评论内容")
    private String content;
}
