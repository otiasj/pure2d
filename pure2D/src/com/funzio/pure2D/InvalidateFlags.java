/**
 * 
 */
package com.funzio.pure2D;

/**
 * @author long
 */
public interface InvalidateFlags {
    public static final int ORIGIN = 1 << 0;
    public static final int POSITION = 1 << 1;
    public static final int SCALE = 1 << 2;
    public static final int ROTATION = 1 << 3;
    public static final int SIZE = 1 << 4;
    public static final int BOUNDS = ORIGIN | POSITION | ROTATION | SCALE | SIZE;

    public static final int VISIBILITY = 1 << 5;
    public static final int COLOR = 1 << 6;
    public static final int ALPHA = 1 << 7;
    public static final int BLEND = 1 << 8;
    public static final int VISUAL = VISIBILITY | COLOR | ALPHA | BLEND;

    public static final int ALL = BOUNDS | VISUAL;
}
