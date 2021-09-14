package com.spca.animalnest.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spca.animalnest.beans.Post;
import com.spca.animalnest.dto.PostDto;
import com.spca.animalnest.mapper.PostMapper;
import com.spca.animalnest.service.PostService;
import com.spca.animalnest.tool.OssInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Locale;

/**
 * @author seenline
 * @date 2021/8/23 23:17
 * @description
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper,Post> implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public void upload(String uid, Integer animalId, String content, MultipartFile multipartFile,String type) {
        if(multipartFile!=null)
        {
            String fileName=multipartFile.getOriginalFilename();
            String lastName=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            String name=uid+"-"+System.currentTimeMillis()+lastName;
            String URL = null;
            try {
                InputStream inputStream=multipartFile.getInputStream();
                URL=OssInteraction.push(name,inputStream,type);
            } catch (IOException e) {
                e.printStackTrace();
            }
            postMapper.insert(new Post().setUid(uid).setAnimalId(animalId).setContent(content).setUrl(URL));
        }
        else
            postMapper.insert(new Post().setUid(uid).setAnimalId(animalId).setContent(content));
    }

    @Override
    public LinkedList<PostDto> get(Integer currentPage,Integer animalId,String uid) {
        IPage<PostDto>page=new Page<>();
        page.setSize(10);
        page.setCurrent(currentPage);
        LinkedList<PostDto>result=postMapper.selectPage(page,animalId,uid);
        return result;
    }
}
