package com.alan344.uid.baidu;

import com.alan344.uid.baidu.exception.UidGenerateException;

/**
 * Represents a unique id generator.
 *
 * @author yutianbao
 */
public interface UidGenerator {

    /**
     * Get a unique ID
     *
     * @return UID
     * @throws UidGenerateException e
     */
    long getUID() throws UidGenerateException;

    /**
     * Parse the UID into elements which are used to generate the UID. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @param uid uid
     * @return Parsed info
     */
    String parseUID(long uid);

}
