package com.example.influx.config.configOrder;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-08-02
 */
//@Configuration
@AutoConfigureBefore(B_SonConfig.class)
public class C_ParentConfig {
    public C_ParentConfig() {
        System.out.println("ParentConfig");
    }
}
