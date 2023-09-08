package cmcc.iot.onenet.javasdk.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class IotDataDto {

    private int errno;

    private String error;

    private DataDto data;

    @Data
    @Accessors(chain = true)
    public static class DataDto {

        private List<DeviceDto> devices;

    }

    @Data
    @Accessors(chain = true)
    public static class DeviceDto {

        private String title;

        private String id;

        private List<Datastreams> datastreams;

    }

    @Data
    @Accessors(chain = true)
    public static class Datastreams {

        private String at;

        private String id;

        private String value;

    }

}
