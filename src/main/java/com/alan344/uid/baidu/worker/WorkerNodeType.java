package com.alan344.uid.baidu.worker;

/**
 * WorkerNodeType
 * <li>CONTAINER: Such as Docker
 * <li>ACTUAL: Actual machine
 */
public enum WorkerNodeType {
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
    WorkerNodeType(Integer type) {
        this.type = type;
    }

    public Integer value() {
        return type;
    }

}
