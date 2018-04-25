package com.i000.stock.user.web.controller;

import com.i000.stock.user.api.entity.bo.Page;
import com.i000.stock.user.api.entity.vo.ReplyFirstVo;
import com.i000.stock.user.api.entity.vo.ReplyVo;
import com.i000.stock.user.api.entity.vo.ReplyVos;
import com.i000.stock.user.api.service.ReplyService;
import com.i000.stock.user.core.result.Results;
import com.i000.stock.user.core.result.base.ResultEntity;
import com.i000.stock.user.core.util.ConvertUtils;
import com.i000.stock.user.core.util.ValidationUtils;
import com.i000.stock.user.dao.entity.bo.BaseSearchVo;
import com.i000.stock.user.dao.model.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:42 2018/4/24
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Resource
    private ReplyService replyService;

    /**
     * 127.0.0.1:8082/reply/createFirst
     * 对话题的评论信息
     *
     * @param replyFirstVo
     * @return
     */
    @PostMapping("/createFirst")
    public ResultEntity createFirst(@RequestBody ReplyFirstVo replyFirstVo) {
        ValidationUtils.validate(replyFirstVo);
        Reply reply = ConvertUtils.beanConvert(replyFirstVo, new Reply());
        reply.setCreatedTime(LocalDateTime.now());
        Long id = replyService.create(reply);
        return Results.newNormalResultEntity("id", id);
    }

    /**
     * 127.0.0.1:8082/reply/createReply
     * 对评论的评论信息
     *
     * @param replyVo
     * @return
     */
    @PostMapping("/createReply")
    public ResultEntity createReply(@RequestBody ReplyVo replyVo) {
        ValidationUtils.validate(replyVo);
        Reply reply = ConvertUtils.beanConvert(replyVo, new Reply());
        reply.setCreatedTime(LocalDateTime.now());
        return Results.newNormalResultEntity("id", replyService.create(reply));
    }

    /**
     * 127.0.0.1:8082/reply/search
     * 根据分页条件 和 话题主键 查询评论回复信息
     *
     * @param baseSearchVo
     * @param id
     * @return
     */
    @GetMapping("/search")
    public ResultEntity search(BaseSearchVo baseSearchVo, @RequestParam Long id) {
        ValidationUtils.validate(baseSearchVo);
        ValidationUtils.validateId(id, "话题主键不合法");
        Page<ReplyVos> search = replyService.search(baseSearchVo, id);
        return CollectionUtils.isEmpty(search.getList()) ? Results.newPageResultEntity(0L, new ArrayList<>(0)) :
                Results.newPageResultEntity(search.getTotal(), search.getList());
    }

    /**
     * 127.0.0.1:8082/reply/do_good
     * 对评论点赞
     *
     * @param id
     * @return
     */
    @GetMapping("/do_good")
    public ResultEntity doGood(@RequestParam Long id) {
        ValidationUtils.validateId(id, "话题主键不合法");
        return Results.newNormalResultEntity("addNum", replyService.doGood(id));
    }

    /**
     * 踩
     *
     * @param id
     * @return
     */
    @GetMapping("/do_bad")
    public ResultEntity doBad(@RequestParam Long id) {
        ValidationUtils.validateId(id, "话题主键不合法");
        return Results.newNormalResultEntity("addNum", replyService.doBad(id));
    }


}
