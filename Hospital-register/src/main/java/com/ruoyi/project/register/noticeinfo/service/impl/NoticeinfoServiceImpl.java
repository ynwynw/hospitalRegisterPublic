package com.ruoyi.project.register.noticeinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.noticeinfo.mapper.NoticeinfoMapper;
import com.ruoyi.project.register.noticeinfo.domain.Noticeinfo;
import com.ruoyi.project.register.noticeinfo.service.INoticeinfoService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 公告信息Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class NoticeinfoServiceImpl implements INoticeinfoService
{
    @Autowired
    private NoticeinfoMapper noticeinfoMapper;

    /**
     * 查询公告信息
     *
     * @param id 公告信息ID
     * @return 公告信息
     */
    @Override
    public Noticeinfo selectNoticeinfoById(Long id)
    {
        return noticeinfoMapper.selectNoticeinfoById(id);
    }

    /**
     * 查询公告信息列表
     *
     * @param noticeinfo 公告信息
     * @return 公告信息
     */
    @Override
    public List<Noticeinfo> selectNoticeinfoList(Noticeinfo noticeinfo)
    {
        return noticeinfoMapper.selectNoticeinfoList(noticeinfo);
    }

    /**
     * 新增公告信息
     *
     * @param noticeinfo 公告信息
     * @return 结果
     */
    @Override
    public int insertNoticeinfo(Noticeinfo noticeinfo)
    {
        noticeinfo.setCreateTime(DateUtils.getNowDate());
        return noticeinfoMapper.insertNoticeinfo(noticeinfo);
    }

    /**
     * 修改公告信息
     *
     * @param noticeinfo 公告信息
     * @return 结果
     */
    @Override
    public int updateNoticeinfo(Noticeinfo noticeinfo)
    {
        noticeinfo.setUpdateTime(DateUtils.getNowDate());
        return noticeinfoMapper.updateNoticeinfo(noticeinfo);
    }

    /**
     * 删除公告信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNoticeinfoByIds(String ids)
    {
        return noticeinfoMapper.deleteNoticeinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公告信息信息
     *
     * @param id 公告信息ID
     * @return 结果
     */
    @Override
    public int deleteNoticeinfoById(Long id)
    {
        return noticeinfoMapper.deleteNoticeinfoById(id);
    }
}
