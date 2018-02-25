package com.github.wxiaoqi.blog.admin.biz;

import com.github.wxiaoqi.blog.admin.entity.Comment;
import com.github.wxiaoqi.blog.admin.entity.Message;
import com.github.wxiaoqi.blog.admin.mapper.CommentMapper;
import com.github.wxiaoqi.blog.admin.mapper.MessageMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-06-12 8:48
 */
@Service
public class MessageBiz extends BaseBiz<MessageMapper,Message> {
}
