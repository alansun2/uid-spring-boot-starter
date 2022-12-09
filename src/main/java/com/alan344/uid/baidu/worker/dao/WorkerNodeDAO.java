package com.alan344.uid.baidu.worker.dao;

import com.alan344.uid.baidu.worker.entity.WorkerNodeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * DAO for M_WORKER_NODE
 *
 * @author yutianbao
 */
public interface WorkerNodeDAO {

    /**
     * Get {@link WorkerNodeEntity} by globalToken
     *
     * @return
     */
    WorkerNodeEntity getWorkerNodeByGlobalToken(@Param("globalToken") String globalToken);

    /**
     * Add {@link WorkerNodeEntity}
     *
     * @param workerNodeEntity
     */
    void addWorkerNode(WorkerNodeEntity workerNodeEntity);

    /**
     * update workId
     *
     * @param workerNodeEntity
     */
    void updateWorkId(WorkerNodeEntity workerNodeEntity);
}
