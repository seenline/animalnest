package com.spca.animalnest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spca.animalnest.beans.Post;
import com.spca.animalnest.dto.PostDto;
import com.spca.animalnest.dto.R;
import com.spca.animalnest.mapper.PostMapper;
import com.spca.animalnest.service.PostService;
import com.spca.animalnest.service.imp.PostServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

/**
 * @author seenline
 * @date 2021/8/23 21:19
 * @description 帖子模块controller
 */
@RestController
@Api(tags = "帖子模块")
@RequestMapping("/post")
public class PostController<T> {

    @Autowired
    private PostService postService;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取逛逛中所有帖子内容",notes = "有分页功能，一页10个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true)
    })
    public R<LinkedList<PostDto>> postGetAll(@RequestParam(name = "page")Integer currentPage)
    {
        return R.ok(postService.get(currentPage,null,null));
    }

    @GetMapping("/getById")
    @ApiOperation(value = "获取指定动物下的帖子内容",notes = "有分页功能，一页10个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true),
            @ApiImplicitParam(name = "id",value = "动物id",required = true)
    })
    public R<LinkedList<PostDto>>postById(@RequestParam(name = "page")Integer currentPage,
                                          @RequestParam(name = "id")Integer animalId)
    {
        return R.ok(postService.get(currentPage,animalId,null));
    }

    @GetMapping("getByUser")
    @ApiOperation(value = "获取个人已发布动态",notes = "有分页功能，一页10个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true),
            @ApiImplicitParam(name = "id",value = "用户uid",required = true)
    })
    public R<LinkedList<PostDto>>postByUser(@RequestParam(name = "page")Integer currentPage,
                                            @RequestParam(name = "id")String uid)
    {
        return R.ok(postService.get(currentPage,null,uid));
    }


    @GetMapping("/like")
    @ApiOperation(value = "点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "帖子id"),
            @ApiImplicitParam(name = "type",value = "去除或增加点赞,以’+‘和’-'符号做区分")
    })
    public R like(@RequestParam(name = "id")Integer id,
                  @RequestParam(name = "type")String type)
    {
        Post post=postService.getOne(new QueryWrapper<Post>().eq("id",id));
        if(type.equals("+"))
            postService.update(post.setLikeCount(post.getLikeCount()+1),new QueryWrapper<Post>().eq("id",id));
        else
            postService.update(post.setLikeCount(post.getLikeCount()-1),new QueryWrapper<Post>().eq("id",id));
        return R.ok();
    }


    @PostMapping("/add")
    @ApiOperation(value = "发布帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid",value = "用户uid",required = true),
            @ApiImplicitParam(name = "idtoanimal",value = "动物id",required = true),
            @ApiImplicitParam(name = "content",value = "帖子内容",required = true),
            @ApiImplicitParam(name = "file",value = "视频或图片"),
            @ApiImplicitParam(name = "type",value = "文件类型")
    })
    public R add(@RequestParam(name = "uid")String uid,
                 @RequestParam(name = "idtoanimal")Integer animalId,
                 @RequestParam(name = "content")String content,
                 @RequestParam(name = "file",required = false)MultipartFile file,
                 @RequestParam(name = "type",required = false)String type)
    {
        postService.upload(uid,animalId,content,file,type);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除帖子")
    @ApiImplicitParam(name = "id",value = "帖子id")
    public R delete(@RequestParam(name = "id")Integer id)
    {
        Post tmp=postService.getById(id);
        if(tmp!=null) {
            postService.remove(new QueryWrapper<Post>().eq("id", id));
            return R.ok();
        }
        else
            return R.error();
    }


}
