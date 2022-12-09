package com.alan344.uid.baidu.worker.entity;

import com.alan344.uid.baidu.worker.WorkerNodeType;
import lombok.ToString;

import java.util.Date;

/**
 * Entity for M_WORKER_NODE
 *
 * @author yutianbao
 */
@ToString
public class WorkerNodeEntity {

    /**
     * Entity unique id (table unique)
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

    /**
     * Getters & Setters
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getGlobalToken() {
        return globalToken;
    }

    public void setGlobalToken(String globalToken) {
        this.globalToken = globalToken;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }
}
