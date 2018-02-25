package com.github.wxiaoqi.blog.admin.biz;

import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.entity.Friendship;
import com.github.wxiaoqi.blog.admin.mapper.CommentMapper;
import com.github.wxiaoqi.blog.admin.mapper.FriendshipMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-12 8:48
 */
@Service
public class FriendshipBiz extends BaseBiz<FriendshipMapper,Friendship> {
}
