package com.spca.animalnest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author seenline
 * @date 2021/8/24 14:04
 * @description
 */
@Data
@ApiModel(value = "帖子内容返回类")
public class PostDto {
    @ApiModelProperty(value = "帖子id")
    private Integer id;
    @ApiModelProperty(value = "帖子发布用户")
    private String uid;
    @ApiModelProperty(value = "帖子的内容")
    private String content;
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;
    @ApiModelProperty(value = "帖子创建时间")
    private String createTime;
}
