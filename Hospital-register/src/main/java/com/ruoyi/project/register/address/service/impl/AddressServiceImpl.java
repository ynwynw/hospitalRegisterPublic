package com.ruoyi.project.register.address.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.register.address.mapper.AddressMapper;
import com.ruoyi.project.register.address.domain.Address;
import com.ruoyi.project.register.address.service.IAddressService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 地址管理Service业务层处理
 *
 * @author yanqinglan
 * @date 2023/01/20
 */
@Service
public class AddressServiceImpl implements IAddressService
{
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询地址管理
     *
     * @param id 地址管理ID
     * @return 地址管理
     */
    @Override
    public Address selectAddressById(Long id)
    {
        return addressMapper.selectAddressById(id);
    }

    /**
     * 查询地址管理列表
     *
     * @param address 地址管理
     * @return 地址管理
     */
    @Override
    public List<Address> selectAddressList(Address address)
    {
        return addressMapper.selectAddressList(address);
    }

    /**
     * 新增地址管理
     *
     * @param address 地址管理
     * @return 结果
     */
    @Override
    public int insertAddress(Address address)
    {
        address.setCreateTime(DateUtils.getNowDate());
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改地址管理
     *
     * @param address 地址管理
     * @return 结果
     */
    @Override
    public int updateAddress(Address address)
    {
        address.setUpdateTime(DateUtils.getNowDate());
        return addressMapper.updateAddress(address);
    }

    /**
     * 删除地址管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAddressByIds(String ids)
    {
        return addressMapper.deleteAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地址管理信息
     *
     * @param id 地址管理ID
     * @return 结果
     */
    @Override
    public int deleteAddressById(Long id)
    {
        return addressMapper.deleteAddressById(id);
    }
}
