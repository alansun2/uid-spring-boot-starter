package com.alan344.uid.baidu.worker.entity;

import com.alan344.uid.baidu.worker.WorkerNodeType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Entity for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Setter
@Getter
@ToString
public class WorkerNodeEntity {

    /**
     * Entity unique id (table unique)
     * -- GETTER --
     * Getters & Setters
     */
    private long id;

    /**
     * workId
     */
    private long workId;

    /**
     * Type of CONTAINER: HostName, ACTUAL : IP.
     */
    private String hostName;

    /**
     * Type of CONTAINER: Port, ACTUAL : Timestamp + Random(0-10000)
     */
    private String port;

    /**
     * type of {@link WorkerNodeType}
     */
    private int type;

    /**
     * global token, same global token will return same workId
     */
    private String globalToken;

    /**
     * Worker launch date, default now
     */
    private Date launchDate = new Date();

    /**
     * Created time
     */
    private Date created;

    /**
     * Last modified
     */
    private Date modified;
}
