package com.ruoyi.project.register.noticeinfo.service;

import java.util.List;
import com.ruoyi.project.register.noticeinfo.domain.Noticeinfo;

/**
 * 公告信息Service接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface INoticeinfoService
{
    /**
     * 查询公告信息
     *
     * @param id 公告信息ID
     * @return 公告信息
     */
    public Noticeinfo selectNoticeinfoById(Long id);

    /**
     * 查询公告信息列表
     *
     * @param noticeinfo 公告信息
     * @return 公告信息集合
     */
    public List<Noticeinfo> selectNoticeinfoList(Noticeinfo noticeinfo);

    /**
     * 新增公告信息
     *
     * @param noticeinfo 公告信息
     * @return 结果
     */
    public int insertNoticeinfo(Noticeinfo noticeinfo);

    /**
     * 修改公告信息
     *
     * @param noticeinfo 公告信息
     * @return 结果
     */
    public int updateNoticeinfo(Noticeinfo noticeinfo);

    /**
     * 批量删除公告信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeinfoByIds(String ids);

    /**
     * 删除公告信息信息
     *
     * @param id 公告信息ID
     * @return 结果
     */
    public int deleteNoticeinfoById(Long id);
}
