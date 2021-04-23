package com.hyqin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @description 菜单资源类
 * @author: huangyeqin
 * @create : 2021/4/22  15:19
 */
@Data
@TableName(value = "sys_menu")
public class MenuInfoDO implements Serializable {

  private static final long serialVersionUID = -8979773830738937346L;

  @TableId
  private Integer id;

  @TableField(value = "menu_name",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String menuName;

  @TableField(value = "menu_path",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String menuPath;

  @TableField(value = "menu_parent_id",updateStrategy = FieldStrategy.NOT_EMPTY)
  private Integer menuParentId;

  @TableField(value = "menu_type",updateStrategy = FieldStrategy.NOT_EMPTY)
  private Integer menuType;

  @TableField(value = "is_deleted",updateStrategy = FieldStrategy.NOT_EMPTY)
  private Integer isDeleted;
}
