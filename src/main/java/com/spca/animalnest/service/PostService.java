package com.spca.animalnest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spca.animalnest.beans.Post;
import com.spca.animalnest.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

/**
 * @author seenline
 * @date 2021/8/23 23:20
 * @description
 */
public interface PostService extends IService<Post> {
    LinkedList<PostDto> get(Integer currentPage,Integer animalId,String uid);
    void upload(String uid, Integer animalId, String content, MultipartFile file,String type);
}
