package com.ruoyi.project.third;

import cmcc.iot.onenet.javasdk.api.device.GetLatesDeviceData;
import cmcc.iot.onenet.javasdk.api.dto.IotDataDto;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.DeciceLatestDataPoint;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;

import com.ruoyi.project.common.about.domain.HospitalAbout;
import com.ruoyi.project.common.about.service.IHospitalAboutService;
import com.ruoyi.project.common.advise.domain.HospitalAdvise;
import com.ruoyi.project.common.advise.service.HospitalAdviseService;
import com.ruoyi.project.common.arearegister.domain.AreaRegister;
import com.ruoyi.project.common.arearegister.mapper.AreaRegisterMapper;
import com.ruoyi.project.common.arearegister.service.AreaRegisterService;
import com.ruoyi.project.common.collect.domain.HospitalCollect;
import com.ruoyi.project.common.collect.service.HospitalCollectService;
import com.ruoyi.project.common.hospitalregister.domain.HospitalRegister;
import com.ruoyi.project.common.hospitalregister.mapper.HospitalRegisterMapper;
import com.ruoyi.project.register.account.domain.Account;
import com.ruoyi.project.register.account.service.IAccountService;
import com.ruoyi.project.register.address.domain.Address;
import com.ruoyi.project.register.address.service.IAddressService;
import com.ruoyi.project.register.area.domain.Areainfo;
import com.ruoyi.project.register.area.service.IAreainfoService;
import com.ruoyi.project.register.hospital.domain.Hospitalinfo;
import com.ruoyi.project.register.hospital.service.HospitalinfoService;
import com.ruoyi.project.register.registerinfo.domain.Registerinfo;
import com.ruoyi.project.register.registerinfo.service.IRegisterinfoService;
import com.ruoyi.project.register.noticeinfo.domain.Noticeinfo;
import com.ruoyi.project.register.noticeinfo.service.INoticeinfoService;
import com.ruoyi.project.register.serviceinfo.domain.Serviceinfo;
import com.ruoyi.project.register.serviceinfo.service.IServiceinfoService;
import com.ruoyi.project.register.serviceorder.domain.Serviceorder;
import com.ruoyi.project.register.serviceorder.service.IServiceorderService;
import com.ruoyi.project.register.baseinfo.domain.Baseinfo;
import com.ruoyi.project.register.baseinfo.service.IBaseinfoService;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 小程序调用接口相关
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/hospital_mini")
public class HospitalMiniController {

    @Autowired
    private HospitalCollectService hospitalCollectService;

    @Autowired
    private IHospitalAboutService hospitalAboutService;

    @Autowired
    private IBaseinfoService baseinfoService;
    @Autowired
    private INoticeinfoService noticeinfoService;

    @Autowired
    private IAddressService addressService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRegisterinfoService registerinfoService;
    @Autowired
    private IServiceinfoService serviceinfoService;
    @Autowired
    private IServiceorderService serviceorderService;
    @Autowired
    private IUserService userService;

    @Autowired
    private HospitalinfoService hospitalinfoService;

    @Autowired
    private AreaRegisterMapper areaRegisterMapper;


    @Autowired
    private IAreainfoService areainfoService;


    @Autowired
    private HospitalRegisterMapper hospitalRegisterMapper;


    @Autowired
    private HospitalAdviseService hospitalAdviseService;

    private final static String IOT_KEY = "TuWcM6EgasJkL=1hCgyvxTwVdss=";
    private final static String IOT_DEVIDS = "1046826732";


    @PostMapping("/collect")
    @ResponseBody
    public AjaxResult updateCollect(HospitalCollect hospitalCollect) {
        int i = hospitalCollectService.updateCollect(hospitalCollect);
        return AjaxResult.success();
    }

    @PostMapping("/getCollect")
    @ResponseBody
    public AjaxResult getCollect(HospitalCollect hospitalCollect) {

        List<Serviceinfo> collect = hospitalCollectService.getCollect(hospitalCollect);
        return AjaxResult.success(collect);
    }
    @PostMapping("/advise")
    @ResponseBody
    public AjaxResult updateAdvise(HospitalAdvise hospitalAdvise) {
        int i = hospitalAdviseService.updateAdvise(hospitalAdvise);
        return AjaxResult.success();
    }

    @PostMapping("/getAdvise")
    @ResponseBody
    public AjaxResult getAdvise(HospitalAdvise hospitalAdvise) {

        List<HospitalAdvise> advise = hospitalAdviseService.getAdvise(hospitalAdvise);
        return AjaxResult.success(advise);
    }

    @PostMapping("/getBaseInfo")
    @ResponseBody
    public AjaxResult getBaseInfo(Baseinfo baseinfo) {
        List<Baseinfo> baseinfoList = baseinfoService.selectBaseinfoList(baseinfo);
        if (CollUtil.isNotEmpty(baseinfoList)) {
//            Baseinfo baseinfo = baseinfoList.stream().findFirst().orElse(new Baseinfo());
            for (Baseinfo baseinfo1 : baseinfoList) {
                return AjaxResult.success(baseinfo1);
            }
            return null;

        } else {
            return AjaxResult.error("获取数据失败");
        }
    }

    @PostMapping("/getIotInfo")
    @ResponseBody
    public AjaxResult getIotInfo() {
        GetLatesDeviceData api = new GetLatesDeviceData(IOT_DEVIDS, IOT_KEY);
        BasicResponse<DeciceLatestDataPoint> response = api.executeApi();
        int errno = response.errno;
        String template = "光照:{}，温度:{}，湿度:{}";
        String light1 = "0%";
        String temp1 = "0°C";
        String humi1 = "0%";
        if (errno == 0) {
            String json = response.getJson();
            IotDataDto iotData = JSONUtil.toBean(json, IotDataDto.class);
            if (ObjectUtil.isNotEmpty(iotData)) {
                IotDataDto.DataDto data = iotData.getData();
                if (ObjectUtil.isNotEmpty(data)) {
                    List<IotDataDto.DeviceDto> devices = data.getDevices();
                    if (CollUtil.isNotEmpty(devices)) {
                        IotDataDto.DeviceDto deviceDto = devices.get(0);
                        if (ObjectUtil.isNotEmpty(deviceDto)) {
                            List<IotDataDto.Datastreams> datastreams = deviceDto.getDatastreams();
                            if (CollUtil.isNotEmpty(datastreams)) {
                                light1 = getIotValue("light1", "%", datastreams);
                                temp1 = getIotValue("temp1", "°C", datastreams);
                                humi1 = getIotValue("humi1", "%", datastreams);
                            }
                        }
                    }
                }
            }
        }
        String iotInfo = StrUtil.format(template, light1, temp1, humi1);
        return AjaxResult.success(iotInfo);
    }

    private String getIotValue(String name, String unit, List<IotDataDto.Datastreams> list) {
        String result = "0" + unit;
        IotDataDto.Datastreams datastreams = list.stream().filter(item -> StrUtil.equals(name, item.getId())).findFirst().orElse(new IotDataDto.Datastreams());
        if (ObjectUtil.isNotEmpty(datastreams)) {
            result = datastreams.getValue() + unit;
        }
        return result;
    }


    @PostMapping("/getNoticeInfoList")
    @ResponseBody
    public AjaxResult getNoticeInfoList() {
        List<Noticeinfo> noticeinfoList = noticeinfoService.selectNoticeinfoList(new Noticeinfo());
        if (CollUtil.isNotEmpty(noticeinfoList)) {
            noticeinfoList.forEach(data -> {
                if (data.getTitle().length() > 12) {
                    data.setTitle(data.getTitle().substring(0, 12) + "...");
                }
            });
            if (noticeinfoList.size() >= 4){
                List<Noticeinfo> randomList = noticeinfoList.subList(0, 4);
                return AjaxResult.success(randomList);
            }else {
                return AjaxResult.success(noticeinfoList);
            }


        } else {
            return AjaxResult.error("暂无资讯信息");
        }
    }

    @PostMapping("/getServiceOrderInfoList")
    @ResponseBody
    public AjaxResult getServiceOrderInfoList(Integer serviceId) {
        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderByDoctorId(serviceId);
        if (CollUtil.isNotEmpty(serviceorderList)) {
            return AjaxResult.success(serviceorderList);
        } else {
            return AjaxResult.error("null");
        }
    }
    @PostMapping("/getServiceOrderInfoAppraiseList")
    @ResponseBody
    public AjaxResult getServiceOrderInfoAppraiseList(Serviceorder serviceorder) {
//        serviceorder.setPayStatus("1");
//        serviceorder.setServiceStatus("2");
        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderInfoAppraiseList(serviceorder);
        if (CollUtil.isNotEmpty(serviceorderList)) {
            return AjaxResult.success(serviceorderList);
        } else {
            return AjaxResult.error("null");
        }
    }
    @PostMapping("/getServiceOrderInfoList1")
    @ResponseBody
    public AjaxResult getServiceOrderInfoList1(Serviceorder serviceorder) {
        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderInfoList(serviceorder);
        if (CollUtil.isNotEmpty(serviceorderList)) {
            return AjaxResult.success(serviceorderList);
        } else {
            return AjaxResult.error("null");
        }
    }

    @PostMapping("/getNoticeInfoDetail")
    @ResponseBody
    public AjaxResult getNoticeInfoDetail(String noticeId) {
        if (StringUtils.isEmpty(noticeId)) {
            return AjaxResult.error("公告id为空。");
        }
        Noticeinfo noticeinfo = noticeinfoService.selectNoticeinfoById(Long.parseLong(noticeId));
        return AjaxResult.success(noticeinfo);
    }

    /**
     * 添加/更新地址
     */
    @PostMapping("/saveAddress")
    @ResponseBody
    public AjaxResult saveAddress(Address address) {
        if (address.getId() == null) {
            addressService.insertAddress(address);
        } else {
            addressService.updateAddress(address);
        }
        return AjaxResult.success();
    }

    /**
     * 获取地址信息
     */
    @PostMapping("/getAddress")
    @ResponseBody
    public AjaxResult getAddress(String addressId) {
        if (StringUtils.isEmpty(addressId)) {
            return AjaxResult.error("地址id为空。");
        }
        Address address = addressService.selectAddressById(Long.parseLong(addressId));
        return AjaxResult.success(address);
    }

    /**
     * 获取地址列表
     */
    @PostMapping("/getAddressList")
    @ResponseBody
    public AjaxResult getAddressList(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return AjaxResult.error("用户id为空。");
        }
        Address address = new Address();
        address.setUserId(Long.parseLong(userId));
        List<Address> addressList = addressService.selectAddressList(address);
        return AjaxResult.success(addressList);
    }

    /**
     * 创建服务订单
     */
    @PostMapping("/createServiceOrder")
    @ResponseBody
    public AjaxResult createServiceOrder(Serviceorder serviceorder) {
        if (StringUtils.isEmpty(serviceorder.getCreateBy())) {
            return AjaxResult.error("用户id为空。");
        }
        serviceorder.setUpdateTime(DateUtils.getNowDate());
        serviceorderService.insertServiceorder(serviceorder);
        return AjaxResult.success(serviceorder);
    }

    @PostMapping("/serviceOrderList")
    @ResponseBody
    public AjaxResult serviceOrderList(Serviceorder serviceorder) {
        if (serviceorder == null || StringUtils.isEmpty(serviceorder.getCreateBy())) {
            return AjaxResult.error("用户id为空");
        }

        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderList(serviceorder);
        serviceorderList.stream()
                .forEach(o -> {
                    o.setServiceinfo(serviceinfoService.selectServiceinfoById(o.getServiceId()));

                    if ("0".equals(o.getServiceStatus()) && "0".equals(o.getPayStatus())) {
                        o.setStateTip("待就诊");
                    } else if ("1".equals(o.getServiceStatus())) {
                        o.setStateTip("已取消");
                    } else if ("2".equals(o.getServiceStatus()) && "1".equals(o.getPayStatus())) {
                        o.setStateTip("已完成");
                    } else if ("3".equals(o.getServiceStatus()) && "0".equals(o.getPayStatus())) {
                        o.setStateTip("已过期");
                    }

                });
        return AjaxResult.success(serviceorderList);
    }

    /**
     * 订单支付
     */
    @PostMapping("/serviceOrderPay")
    @ResponseBody
    public AjaxResult serviceOrderPay(String orderId, String accountId) {
        if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(accountId)) {
            return AjaxResult.error("订单id为空");
        }
        Serviceorder serviceorder = serviceorderService.selectServiceorderById(Long.parseLong(orderId));
        serviceorder.setPayStatus("1");
        serviceorderService.updateServiceorder(serviceorder);

        Account account = accountService.selectAccountById(Long.parseLong(accountId));
        BigDecimal num1 = account.getRemainingSum();
        BigDecimal num2 = new BigDecimal(serviceorder.getPrice());
        BigDecimal result = num1.subtract(num2);
        account.setRemainingSum(result);
        accountService.updateAccount(account);
        return AjaxResult.success(serviceorder);
    }

    /**
     * 订单取消
     */
    @PostMapping("/serviceOrderCancel")
    @ResponseBody
    public AjaxResult serviceOrderCancel(String orderId, String reasonCancel) {
        if (StringUtils.isEmpty(orderId)) {
            return AjaxResult.error("订单id为空");
        }
        Serviceorder serviceorder = serviceorderService.selectServiceorderById(Long.parseLong(orderId));
        serviceorder.setServiceStatus("1");
        serviceorder.setReasonCancel(reasonCancel);
        serviceorderService.updateServiceorder(serviceorder);
        return AjaxResult.success(serviceorder);
    }


    @PostMapping("/serviceOrderComment")
    @ResponseBody
    public AjaxResult serviceOrderComment(String orderId, String point, String remark) {
        if (StringUtils.isEmpty(orderId)) {
            return AjaxResult.error("订单id为空");
        }
        if (point == null) {
            return AjaxResult.error("未评分");
        }
        Serviceorder serviceorder = serviceorderService.selectServiceorderById(Long.parseLong(orderId));
        serviceorder.setEvaluatePoint(point);
        serviceorder.setRemark(remark);
        serviceorderService.updateServiceorder(serviceorder);
        return AjaxResult.success();
    }

    @PostMapping("/updateCollectStatus")
    @ResponseBody
    public AjaxResult updateCollectStatus(Long id, String collect) {
        if (StringUtils.isEmpty(id.toString()) && StringUtils.isEmpty(collect)) {
            return AjaxResult.error("出错了");
        }
        serviceinfoService.updateCollect(id, collect);
        return AjaxResult.success();
    }


    /**
     * 获取我的余额
     */
    @PostMapping("/getMyAccount")
    @ResponseBody
    public AjaxResult getMyAccount(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return AjaxResult.error("用户id为空。");
        }
        Account account = new Account();
        account.setCreateBy(userId);
        account = accountService.selectAccountByCon(account);
        if (account == null) {
            account = new Account();
            account.setRemainingSum(BigDecimal.valueOf(0));
        }
        return AjaxResult.success(account);
    }

    /**
     * 充值
     */
    @PostMapping("/recharge")
    @ResponseBody
    public AjaxResult recharge(Account account) {
        if (account.getId() == null) {
            accountService.insertAccount(account);
        } else {
            Account temp = accountService.selectAccountById(account.getId());
            BigDecimal count1 = temp.getRemainingSum();
            BigDecimal count2 = account.getRemainingSum();
            BigDecimal result = count1.add(count2);
            account.setRemainingSum(result);
            accountService.updateAccount(account);
        }
        return AjaxResult.success();
    }

    @PostMapping("/getRegisterList")
    @ResponseBody
    public AjaxResult getRegisterList() {
        List<Registerinfo> registerinfoList = registerinfoService.selectRegisterinfoList(new Registerinfo());
        if (registerinfoList != null && registerinfoList.size() > 0) {
            for (Registerinfo register : registerinfoList) {
                if (register.getIntroduce().length() > 30) {
                    String introduce = register.getIntroduce().substring(0, 30) + "...";
                    register.setIntroduce(introduce);
                }

            }
            return AjaxResult.success(registerinfoList);
        } else {
            return AjaxResult.error("暂无楼层信息");
        }
    }

    @PostMapping("/getRegisterInfoList")
    @ResponseBody
    public AjaxResult getRegisterInfoList(Long id) {
        Registerinfo registerinfo = registerinfoService.selectRegisterinfoById(id);
        if (registerinfo != null) {
            return AjaxResult.success(registerinfo);
        }
        return AjaxResult.error("暂无楼层信息");

    }

    @PostMapping("/getRegisterList2")
    @ResponseBody
    public AjaxResult getRegisterList2(Registerinfo registerinfo) {
        List<Registerinfo> registerinfoList = registerinfoService.selectRegisterinfoList(registerinfo);
        if (registerinfoList != null && registerinfoList.size() > 0) {
            return AjaxResult.success(registerinfoList);
        } else {
            return AjaxResult.error("科室");
        }
    }

    @PostMapping("/getServiceList")
    @ResponseBody
    public AjaxResult getServiceList(Long registerId) {
        if (ObjectUtil.isEmpty(registerId)) {
            return AjaxResult.error("楼层id为空");
        }
        Serviceinfo service = new Serviceinfo();
        service.setRegisterId(registerId);
        List<Serviceinfo> serviceinfoList = serviceinfoService.selectServiceinfoList(service);
        if (serviceinfoList != null && serviceinfoList.size() > 0) {
//            for (Serviceinfo serviceinfo : serviceinfoList) {
//                if (serviceinfo.getIntroduce().length() > 30) {
//                    String introduce = serviceinfo.getIntroduce().substring(0, 30) + "...";
//                    serviceinfo.setIntroduce(introduce);
//                }
//
//            }
            return AjaxResult.success(serviceinfoList);
        } else {
            return AjaxResult.error("暂无房间信息");
        }
    }

    @PostMapping("/getServiceList2")
    @ResponseBody
    public AjaxResult getServiceList2(Serviceinfo serviceinfo) {
        List<Serviceinfo> serviceinfoList = serviceinfoService.selectServiceinfoList(serviceinfo);
        if (serviceinfoList != null && serviceinfoList.size() > 0) {
            return AjaxResult.success(serviceinfoList);
        } else {
            return AjaxResult.error("无医生信息");
        }
    }

    @PostMapping("/search")
    @ResponseBody
    public AjaxResult search(String name,Long areaId) {

        if (StringUtils.isEmpty(name)) {
            return AjaxResult.error("名字为空");
        }

        Serviceinfo service = new Serviceinfo();//医生
        Hospitalinfo hospitalinfo = new Hospitalinfo();//医院
        HospitalRegister hospitalRegister = new HospitalRegister();//根据areaid查询其他东西
        Baseinfo baseinfo = new Baseinfo();
        hospitalRegister.setAreaId(areaId);
        ArrayList<Serviceinfo> list1 = new ArrayList<>();
        ArrayList<Baseinfo> list2 = new ArrayList<>();
        List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);//一个areaid会有很多其他东西
        if (hospitalRegisters.size() > 0){
        for (HospitalRegister register : hospitalRegisters) {
            service.setId(register.getDoctorId());
            service.setName(name);
            List<Serviceinfo> serviceinfo = serviceinfoService.selectServiceinfoList(service);//根据doctor和名字模糊查询医生
            if (serviceinfo.size()>0){
                Serviceinfo serviceinfo1 = serviceinfo.get(0);
                list1.add(serviceinfo1);
            }

            hospitalinfo.setHospitalName(name);
            hospitalinfo.setHospitalId(register.getHospitalId());
            Hospitalinfo hospitalinfos = hospitalinfoService.selectByName(hospitalinfo);//根据doctor和名字模糊查询医院

            if (hospitalinfos != null){
                baseinfo.setHospitalId(hospitalinfos.getHospitalId());
                List<Baseinfo> baseinfos = baseinfoService.selectBaseinfoList(baseinfo);
                if (baseinfos.size() > 0){
                    for (Baseinfo baseinfo1 : baseinfos) {
                        list2.add(baseinfo1);
                    }
                }

            }
        }

        }
        if (list1.size() > 0 && list1 != null) {
            List<Serviceinfo> newList1 = list1.stream().distinct().collect(Collectors.toList());
            return AjaxResult.success("医生", newList1);
        } else if (list2.size() > 0 && list2 != null) {
            List<Baseinfo> newList2 = list2.stream().distinct().collect(Collectors.toList());
            return AjaxResult.success("科室", newList2);
        } else {
            return AjaxResult.error("找不到");
        }
    }

    @PostMapping("/getNearServiceorderData")
    @ResponseBody
    public AjaxResult getNearServiceorderData(Serviceorder serviceorder) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = sdf.parse(sdf.format(new Date()));
        List<Serviceorder> serviceorders = serviceorderService.selectServiceorderList(serviceorder);
        for (Serviceorder serviceorder1 : serviceorders) {
            if (serviceorder1.getServiceStatus().equals("0")) {
                Date date1 = sdf.parse(serviceorder1.getOrderDate());
                if (date1.before(date2)) {
                    serviceorder1.setServiceStatus("3");
                    serviceorderService.updateServiceorder(serviceorder1);
                }
            }
        }
        int countDai = 0;
        int countGuo = 0;
        serviceorder.setPayStatus("0");
        serviceorder.setServiceStatus("0");
        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderList(serviceorder);

        serviceorder.setPayStatus("0");
        serviceorder.setServiceStatus("3");

        List<Serviceorder> serviceorderList2 = serviceorderService.selectServiceorderList(serviceorder);


        if (serviceorderList != null && serviceorderList.size() > 0 && serviceorderList2 != null && serviceorderList2.size() > 0) {
            countDai = serviceorderList.size();
            countGuo = serviceorderList2.size();
            return AjaxResult.success("您有" + (countDai + countGuo) + "条挂号记录待处理,其中待就诊记录" + countDai + "条，已过期记录" + countGuo + "条,请及时安排就诊");
        } else if (serviceorderList != null && serviceorderList.size() > 0) {
            countDai = serviceorderList.size();
            return AjaxResult.success("您有" + countDai + "条待就诊挂号记录待处理，请准时到达医院安排就诊");
        }

        if (serviceorderList2 != null && serviceorderList2.size() > 0) {
            countGuo = serviceorderList2.size();
            return AjaxResult.success("您有" + countGuo + "条已过期挂号记录待处理，请重新预约安排就诊");
        }
        return AjaxResult.error("nothing");
    }

    /**
     * 获取给定日期的时间信息，判断是否可以用
     *
     * @return
     */
    @PostMapping("/getNoon")
    public AjaxResult getNoon(String registerId, String serviceId, String orderDate) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(10, 0);
        resultMap.put(12, 0);
        resultMap.put(14, 0);
        resultMap.put(16, 0);
        resultMap.put(18, 0);
        resultMap.put(20, 0);
        Serviceorder serviceorder = new Serviceorder();
        serviceorder.setRegisterId(Long.parseLong(registerId));
        serviceorder.setServiceId(Long.parseLong(serviceId));
        serviceorder.setOrderDate(orderDate);
        List<Serviceorder> serviceorderList = serviceorderService.selectServiceorderList(serviceorder);
        if (serviceorderList == null || serviceorderList.isEmpty()) {
            return AjaxResult.success(resultMap);
        } else {
            serviceorderList = serviceorderList.stream().filter(o -> !"2".equals(o.getPayStatus())).collect(Collectors.toList());
            for (Serviceorder o : serviceorderList) {
                if ("10:00".equals(o.getOrderTime())) {
                    resultMap.put(10, 1);
                } else if ("12:00".equals(o.getOrderTime())) {
                    resultMap.put(12, 1);
                } else if ("14:00".equals(o.getOrderTime())) {
                    resultMap.put(14, 1);
                } else if ("16:00".equals(o.getOrderTime())) {
                    resultMap.put(16, 1);
                } else if ("18:00".equals(o.getOrderTime())) {
                    resultMap.put(18, 1);
                } else if ("20:00".equals(o.getOrderTime())) {
                    resultMap.put(20, 1);
                }
            }
            return AjaxResult.success(resultMap);
        }
    }


    @PostMapping("/getAreaList")
    @ResponseBody
    public AjaxResult getAreaList() throws Exception {

        List<Areainfo> list = areainfoService.selectAll();
        return AjaxResult.success(list);
    }
    @PostMapping("/getArea1")
    @ResponseBody
    public AjaxResult getArea1(Areainfo areainfo) throws Exception {

        Areainfo areainfo1 = areainfoService.selectAllaLL(areainfo);
        return AjaxResult.success(areainfo1);
    }

    @PostMapping("/getAreaRegisterList")
    @ResponseBody
    public AjaxResult getAreaRegisterList(Long areaId) throws Exception {

        ArrayList<Registerinfo> list = new ArrayList<>();
        HospitalRegister hospitalRegister = new HospitalRegister();
        hospitalRegister.setAreaId(areaId);
        List<HospitalRegister> areaRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
        for (HospitalRegister areaRegister : areaRegisters) {

            Registerinfo registerinfo = registerinfoService.selectRegisterinfoById(areaRegister.getRegisterId());
            list.add(registerinfo);
        }
        List<Registerinfo> newList = list.stream().distinct().collect(Collectors.toList());
        return AjaxResult.success(newList);
    }
    @PostMapping("/getDoctorList")
    @ResponseBody
    public AjaxResult getDoctorList(HospitalRegister hospitalRegister) throws Exception {
        List<Serviceinfo> serviceinfos = serviceinfoService.selectDoctorListByid(hospitalRegister);
        for (Serviceinfo serviceinfo : serviceinfos) {
            Hospitalinfo hospitalinfo1 = hospitalinfoService.selectHospitalHospitalById(serviceinfo.getHospitalId());
            serviceinfo.setHospitalName(hospitalinfo1.getHospitalName());
        }

        return AjaxResult.success(serviceinfos);
    }
    @PostMapping("/getHospitalList")
    @ResponseBody
    public AjaxResult getHospitalList(HospitalRegister hospitalRegister) throws Exception {

        List<Baseinfo> hospitalinfos = hospitalinfoService.selectAll(hospitalRegister);
        List<Baseinfo> newList = hospitalinfos.stream().distinct().collect(Collectors.toList());

        return AjaxResult.success(newList);
    }


    @PostMapping("/getDoctorListByAreaId")
    @ResponseBody
    public AjaxResult getDoctorListByAreaId(HospitalRegister hospitalRegister) throws Exception {

        ArrayList<Serviceinfo> list = new ArrayList<>();
        Hospitalinfo hospitalinfo1 = new Hospitalinfo();
        List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
        for (HospitalRegister register : hospitalRegisters) {

            Serviceinfo serviceinfo = serviceinfoService.selectServiceinfoById(register.getDoctorId());
            hospitalinfo1.setHospitalId(serviceinfo.getHospitalId());
            Hospitalinfo hospitalinfo = hospitalinfoService.selectByName(hospitalinfo1);
            Registerinfo registerinfoList = registerinfoService.selectRegisterinfoById(serviceinfo.getRegisterId());
            serviceinfo.setHospitalName(hospitalinfo.getHospitalName());
            serviceinfo.setRegisterName(registerinfoList.getName());
            list.add(serviceinfo);
        }
        return AjaxResult.success(list);
    }



    @PostMapping("/getRegisterListByAreaIdAndHospitalId")
    @ResponseBody
    public AjaxResult getRegisterListByAreaIdAndHospitalId(HospitalRegister hospitalRegister) throws Exception {

        ArrayList<Registerinfo> list = new ArrayList<>();
        List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
        for (HospitalRegister register : hospitalRegisters) {

            Registerinfo registerinfo = registerinfoService.selectRegisterinfoById(register.getRegisterId());
            if (registerinfo != null){
                list.add(registerinfo);
            }
        }
        List<Registerinfo> newList = list.stream().distinct().collect(Collectors.toList());
        return AjaxResult.success(newList);
    }


    @PostMapping("/getDoctorListByAreaIdAndHospitalIdAndRegister")
    @ResponseBody
    public AjaxResult getDoctorListByAreaIdAndHospitalIdAndRegister(HospitalRegister hospitalRegister) throws Exception {

        ArrayList<Serviceinfo> list = new ArrayList<>();
        Hospitalinfo hospitalinfo1 = new Hospitalinfo();
        List<HospitalRegister> hospitalRegisters = hospitalRegisterMapper.selectHospitalIdByAreaId(hospitalRegister);
        for (HospitalRegister register : hospitalRegisters) {

            Serviceinfo serviceinfo = serviceinfoService.selectServiceinfoById(register.getDoctorId());
            if (serviceinfo != null){
                hospitalinfo1.setHospitalId(serviceinfo.getHospitalId());
                Hospitalinfo hospitalinfo = hospitalinfoService.selectByName(hospitalinfo1);
                Registerinfo registerinfoList = registerinfoService.selectRegisterinfoById(serviceinfo.getRegisterId());
                serviceinfo.setHospitalName(hospitalinfo.getHospitalName());
                serviceinfo.setRegisterName(registerinfoList.getName());
                list.add(serviceinfo);
            }
        }
        return AjaxResult.success(list);
    }


    @PostMapping("/getRegisterByRegisterId")
    @ResponseBody
    public AjaxResult getRegisterByRegisterId(Registerinfo registerinfo) throws Exception {

        Registerinfo registerinfo1 = registerinfoService.selectRegisterinfoById(registerinfo.getId());

        if (registerinfo1 != null){

            return AjaxResult.success(registerinfo1);
        }
        else {
            return null;
        }
    }
    @PostMapping("/getHospitalByHospitalId")
    @ResponseBody
    public AjaxResult getHospitalByHospitalId(Hospitalinfo hospitalinfo) throws Exception {

        Hospitalinfo hospitalinfo1 = hospitalinfoService.selectByName(hospitalinfo);

        if (hospitalinfo1 != null){

            return AjaxResult.success(hospitalinfo1);
        }
        else {
            return null;
        }
    }
    @PostMapping("/getHospitalAbout")
    @ResponseBody
    public AjaxResult getHospitalAbout(HospitalAbout hospitalinfo) throws Exception {

        List<HospitalAbout> list = hospitalAboutService.selectHospitalAboutList(hospitalinfo);

        if (list.size()>0){

            return AjaxResult.success(list);
        }
        else {
            return null;
        }
    }






}
