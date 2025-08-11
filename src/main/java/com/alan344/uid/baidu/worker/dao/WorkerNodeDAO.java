package com.alan344.uid.baidu.worker.dao;

import com.alan344.uid.baidu.worker.entity.WorkerNodeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * DAO for M_WORKER_NODE
 */
public interface WorkerNodeDAO {

    /**
     * Get {@link WorkerNodeEntity} by globalToken
     *
     * @return
     */
    WorkerNodeEntity getWorkerNodeByGlobalToken(@Param("globalToken") String globalToken);


    /**
     * Get {@link WorkerNodeEntity} by Host Port
     *
     * @return
     */
    WorkerNodeEntity getWorkerNodeByHostAndPort(@Param("hostName") String hostName, @Param("port") String port);

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
