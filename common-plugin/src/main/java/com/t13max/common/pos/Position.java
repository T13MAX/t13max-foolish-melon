package com.t13max.common.pos;

import lombok.Getter;

/**
 * 坐标
 *
 * @author: t13max
 * @since: 17:19 2024/7/17
 */
@Getter
public class Position {

    private static final int MASK = 0xfffffff;

    private final int x;

    private final int z;

    public Position(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Position(long pos) {
        this.x = (int) ((pos >> 32) & MASK);
        this.z = (int) (pos & MASK);
    }

    public long calcPos() {
        return calcPos(this.x, this.z);
    }

    public static long calcPos(int x, int z) {
        return (long) x << 32 + z;
    }
}
