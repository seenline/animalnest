package com.spca.animalnest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spca.animalnest.beans.Post;
import com.spca.animalnest.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;

/**
 * @author seenline
 * @date 2021/8/23 22:44
 * @description
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    LinkedList<PostDto> selectPage(IPage<PostDto> page,@Param("id1") Integer animalId,@Param("id2")String uid);
}
