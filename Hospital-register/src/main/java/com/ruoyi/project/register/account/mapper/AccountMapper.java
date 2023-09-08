package com.ruoyi.project.register.account.mapper;

import java.util.List;
import com.ruoyi.project.register.account.domain.Account;

/**
 * 账户金额Mapper接口
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
public interface AccountMapper
{
    /**
     * 查询账户金额
     *
     * @param id 账户金额ID
     * @return 账户金额
     */
    public Account selectAccountById(Long id);

    public Account selectAccountByCon(Account account);

    /**
     * 查询账户金额列表
     *
     * @param account 账户金额
     * @return 账户金额集合
     */
    public List<Account> selectAccountList(Account account);

    /**
     * 新增账户金额
     *
     * @param account 账户金额
     * @return 结果
     */
    public int insertAccount(Account account);

    /**
     * 修改账户金额
     *
     * @param account 账户金额
     * @return 结果
     */
    public int updateAccount(Account account);

    /**
     * 删除账户金额
     *
     * @param id 账户金额ID
     * @return 结果
     */
    public int deleteAccountById(Long id);

    /**
     * 批量删除账户金额
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccountByIds(String[] ids);
}
