package com.alan344.uid.baidu.worker;

import com.alan344.uid.baidu.impl.DefaultUidGenerator;

/**
 * Represents a worker id assigner for {@link DefaultUidGenerator}
 *
 * @author yutianbao
 */
public interface WorkerIdAssigner {

    /**
     * Assign worker id for {@link DefaultUidGenerator}
     *
     * @return assigned worker id
     */
    long assignWorkerId();

}
