package com.alan344.uid.baidu.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a padded {@link AtomicLong} to prevent the FalseSharing problem<p>
 * <p>
 * The CPU cache line commonly be 64 bytes, here is a sample of cache line after padding:<br>
 * 64 bytes = 8 bytes (object reference) + 6 * 8 bytes (padded long) + 8 bytes (a long value)
 */
public class PaddedAtomicLong extends AtomicLong {
    private static final long serialVersionUID = -3415778863941386253L;

    public PaddedAtomicLong(long initialValue) {
        super(initialValue);
    }

}