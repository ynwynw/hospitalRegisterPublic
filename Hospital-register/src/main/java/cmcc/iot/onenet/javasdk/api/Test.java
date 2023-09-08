package cmcc.iot.onenet.javasdk.api;

import cmcc.iot.onenet.javasdk.api.device.GetLatesDeviceData;
import cmcc.iot.onenet.javasdk.api.dto.IotDataDto;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.DeciceLatestDataPoint;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Map;

public class Test {

    public static void main(String[] args){

        String key = "TuWcM6EgasJkL=1hCgyvxTwVdss=";
        String devIds="1046826732";
        /**
         * 批量查询设备最新数据
         * 参数顺序与构造函数顺序一致
         * @param devIds :设备id用逗号隔开, 限制1000个设备,String
         * @param key:masterkey
         */
        GetLatesDeviceData api = new GetLatesDeviceData(devIds,key);
        BasicResponse<DeciceLatestDataPoint> response = api.executeApi();

        String json = response.getJson();
        Map map = JSONUtil.toBean(json, Map.class);



        IotDataDto iotDataDto = JSONUtil.toBean(json, IotDataDto.class);


        System.out.println("errno:"+response.errno+" error:"+response.error);
        System.out.println(response.getJson());


    }


}
