package com.alan344.uid.baidu.worker;

import com.alan344.uid.baidu.utils.ValuedEnum;

/**
 * WorkerNodeType
 * <li>CONTAINER: Such as Docker
 * <li>ACTUAL: Actual machine
 *
 * @author yutianbao
 */
public enum WorkerNodeType implements ValuedEnum<Integer> {
    /**
     * 容i
     */
    CONTAINER(1),
    /**
     * 真实主机
     */
    ACTUAL(2);

    /**
     * Lock type
     */
    private final Integer type;

    /**
     * Constructor with field of type
     */
    private WorkerNodeType(Integer type) {
        this.type = type;
    }

    @Override
    public Integer value() {
        return type;
    }

}
