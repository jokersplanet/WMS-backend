package com.wms.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lwh
 * @create 2024-05-28 0:11
 */
public class SqlUtils {
    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
