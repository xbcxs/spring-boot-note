package com.xbcxs.common.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author X
 */
public class DataFormatter {

    public static List<Map> format(String parentId, List<Map> list) {
        List topList = new ArrayList();
        Map top = new HashMap(4);
        top.put("id", parentId);
        top.put("children", null);
        topList.add(top);
        Map map = (Map) buildTree(topList, list).get(0);
        return (List<Map>) map.get("children");
    }

    public static List<Map> buildTree(List<Map> parentList, List<Map> originalList) {
        for (Map parent : parentList) {
            List childrenList = new ArrayList<>();
            for (Map current : originalList) {
                if (parent.get("id").equals(current.get("parentId"))) {
                    Map newMap = new HashMap();
                    // 将originalList中代理MAP转换成标准MAP防止删除失败。
                    new HashMap().putAll(current);
                    childrenList.add(newMap);
                }
            }
            if (!childrenList.isEmpty()) {
                parent.put("children", childrenList);
                originalList.removeAll(childrenList);
                buildTree(childrenList, originalList);
            }
        }
        return parentList;
    }
}
