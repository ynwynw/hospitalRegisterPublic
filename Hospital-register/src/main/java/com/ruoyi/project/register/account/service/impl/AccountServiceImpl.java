package com.ruoyi.project.register.account.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.core.env.Environment;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.account.mapper.AccountMapper;
import com.ruoyi.project.register.account.domain.Account;
import com.ruoyi.project.register.account.service.IAccountService;
import com.ruoyi.common.utils.text.Convert;


/**
 * 账户金额Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class AccountServiceImpl implements IAccountService
{
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询账户金额
     *
     * @param id 账户金额ID
     * @return 账户金额
     */
    @Override
    public Account selectAccountById(Long id)
    {
        return accountMapper.selectAccountById(id);
    }


    @Override
    public Account selectAccountByCon(Account account){
        return accountMapper.selectAccountByCon(account);
    }
    /**
     * 查询账户金额列表
     *
     * @param account 账户金额
     * @return 账户金额
     */
    @Override
    public List<Account> selectAccountList(Account account)
    {
        return accountMapper.selectAccountList(account);
    }

    /**
     * 新增账户金额
     *
     * @param account 账户金额
     * @return 结果
     */
    @Override
    public int insertAccount(Account account)
    {
        account.setCreateTime(DateUtils.getNowDate());
        return accountMapper.insertAccount(account);
    }

    /**
     * 修改账户金额
     *
     * @param account 账户金额
     * @return 结果
     */
    @Override
    public int updateAccount(Account account)
    {
        account.setUpdateTime(DateUtils.getNowDate());
        return accountMapper.updateAccount(account);
    }

    /**
     * 删除账户金额对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccountByIds(String ids)
    {
        return accountMapper.deleteAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账户金额信息
     *
     * @param id 账户金额ID
     * @return 结果
     */
    @Override
    public int deleteAccountById(Long id)
    {
        return accountMapper.deleteAccountById(id);
    }

    //导出
//    @Override
//    public String export(Account account) throws Exception {
//        try {
//
//            List<Account> content = accountMapper.selectAccountList(account);
//
//
//
//            //文件名=生产的文件名称+时间戳
//            String fileName = System.currentTimeMillis()+".xlsx";
//
//            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//            //设置背景颜色
//            headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//            //设置头字体
//            WriteFont headWriteFont = new WriteFont();
//            headWriteFont.setFontHeightInPoints((short)13);
//            headWriteFont.setBold(true);
//            headWriteCellStyle.setWriteFont(headWriteFont);
//            //设置头居中
//            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//            //内容策略
//            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//            //设置 水平居中
//            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//
//
//            String filePathName = "xlsx/"+ fileName;
//
//            // 这里需要设置不关闭流
//            EasyExcel.write(filePathName, Account.class)
//                    .registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet1")
//                    .doWrite(content);
//
//            return fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(getClass().getName() + " ===> export 方法出现异常：" + e.getMessage());
//        }
//    }

}
