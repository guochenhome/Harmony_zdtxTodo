package com.zdtx.harmonyos.zdtxtodo.kits;

import org.jetbrains.annotations.Nullable;

/**
 * 创建日期:2021/2/24·15:59
 * 功能说明:﹝描述...﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class StringKit {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

}
