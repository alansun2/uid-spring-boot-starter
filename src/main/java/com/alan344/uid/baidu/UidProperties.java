package com.alan344.uid.baidu;

import lombok.Getter;
import lombok.Setter;
import net.dreamlu.mica.auto.annotation.AutoIgnore;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author AlanSun
 */
@AutoIgnore
@Getter
@Setter
@ConfigurationProperties(prefix = "uid")
public class UidProperties {
    /**
     * Bits allocate
     */
    private int timeBits = 41;
    /**
     * workId Bits allocate
     */
    private int workerBits = 10;
    /**
     * seq Bits allocate
     */
    private int seqBits = 12;
    /**
     * Customer epoch, unit as second. For example 2016-05-20 (ms: 1463673600000)
     */
    private String epochStr = "2025-08-11";
    /**
     * 相同的 globalToken 会返回同一个 workId
     * <p>
     * 可选
     */
    private String globalToken;
    /**
     * 注意不要超过 workId 的最大值，在多环境数据同步到一个环境是可能有用
     * <p>
     * 可选
     */
    private Integer workIdPrefix;
}
