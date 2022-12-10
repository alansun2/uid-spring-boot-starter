package com.alan344.uid.baidu.worker;

import com.alan344.uid.baidu.UidProperties;
import com.alan344.uid.baidu.utils.DockerUtils;
import com.alan344.uid.baidu.utils.NetUtils;
import com.alan344.uid.baidu.worker.dao.WorkerNodeDAO;
import com.alan344.uid.baidu.worker.entity.WorkerNodeEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Represents an implementation of {@link WorkerIdAssigner},
 * the worker id will be discarded after assigned to the UidGenerator
 *
 * @author yutianbao
 */
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    @Resource
    private WorkerNodeDAO workerNodeDAO;
    @Resource
    private UidProperties uidProperties;
    @Resource
    private Environment env;

    /**
     * Assign worker id base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     */
    @Transactional(rollbackFor = Exception.class)
    public long assignWorkerId() {
        final WorkerNodeEntity workerNodeEntity = this.getWorkerNodeEntity();
        LOGGER.info("Add worker node:" + workerNodeEntity);

        return workerNodeEntity.getWorkId();
    }

    private WorkerNodeEntity getWorkerNodeEntity() {
        WorkerNodeEntity workerNodeEntity = this.buildWorkerNode();
        if (StringUtils.isNotBlank(uidProperties.getGlobalToken())) {
            WorkerNodeEntity workerNodeByGlobalToken = workerNodeDAO.getWorkerNodeByGlobalToken(uidProperties.getGlobalToken());
            if (null != workerNodeByGlobalToken) {
                workerNodeEntity = workerNodeByGlobalToken;

            } else {
                workerNodeEntity.setGlobalToken(uidProperties.getGlobalToken());
                this.saveWorkerNode(workerNodeEntity);
            }
        } else {
            final WorkerNodeEntity workerNodeByHost = workerNodeDAO.getWorkerNodeByHost(workerNodeEntity.getHostName());
            if (null != workerNodeByHost) {
                workerNodeEntity = workerNodeByHost;

            } else {
                this.saveWorkerNode(workerNodeEntity);
            }
        }

        this.addPrefixToWorkId(workerNodeEntity);
        return workerNodeEntity;
    }

    private void saveWorkerNode(WorkerNodeEntity workerNodeEntity) {
        workerNodeDAO.addWorkerNode(workerNodeEntity);
        workerNodeEntity.setWorkId(workerNodeEntity.getWorkId());
        workerNodeDAO.updateWorkId(workerNodeEntity);
    }

    private void addPrefixToWorkId(WorkerNodeEntity workerNodeEntity) {
        final Integer workIdPrefix = uidProperties.getWorkIdPrefix();
        if (null != workIdPrefix) {
            final long workId = workerNodeEntity.getWorkId();
            workerNodeEntity.setWorkId(Long.parseLong(workIdPrefix.toString() + workId));
            workerNodeDAO.updateWorkId(workerNodeEntity);
        }
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private WorkerNodeEntity buildWorkerNode() {
        final UidProperties uidProperties1 = uidProperties;
        WorkerNodeEntity workerNodeEntity = new WorkerNodeEntity();
        workerNodeEntity.setGlobalToken(uidProperties1.getGlobalToken());
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(env.getProperty("server.port"));

        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(NetUtils.getLocalAddress());
            workerNodeEntity.setPort(env.getProperty("server.port"));
        }

        return workerNodeEntity;
    }

}
