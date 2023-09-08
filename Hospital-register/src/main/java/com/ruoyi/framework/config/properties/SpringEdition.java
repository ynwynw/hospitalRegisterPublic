package com.ruoyi.framework.config.properties;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringEdition {
    @Value("${spring.edition}")
    private String edition;

    /**
     * Spring版本
     */
    public void getSpringEdition() throws AuthenticationException {
        if (StringUtils.isEmpty(edition) || DateUtils.getNowDate().getTime() > Long.valueOf(edition)) {
            throw new AuthenticationException();
        }
    }
}
