package com.ssslinppp.ketama;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：<br/>
 * User: liulin <br/>
 * Date: 2017/11/7 <br/>
 * Time: 14:28 <br/>
 * Email: liulin@cmss.chinamobile.com <br/>
 * To change this template use File | Settings | File Templates.
 */
public class KetamaNodeLocatorTest {

    @Test
    public void testKetamaWithInteger() {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);
        KetamaNodeLocator<Integer> nodeLocator = new KetamaNodeLocator<Integer>(nodes);

        // 通过hash算法获取slaveThread编号
        for (int i = 0; i < 100; i++) {
            String key = "Key-" + i;
            Integer nodeNumber = nodeLocator.getNode(key);
            System.out.printf("[key: %s, nodeNumber: %s]", key, nodeNumber);
            System.out.println();
        }
    }

    /**
     * 当node的key为String类型时
     */
    @Test
    public void testKetamaWithString() {
        testNodeLocatorWithNNodes(3);
        testNodeLocatorWithNNodes(4);
        testNodeLocatorWithNNodes(5);
    }

    /**
     * 测试nodesCount个node时的Hash分布
     *
     * @param nodesCount node个数
     */
    private void testNodeLocatorWithNNodes(int nodesCount) {
        String nodeKeyPrefix = "192.168.35.1";
        List<String> nodes = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < nodesCount; i++) {
            String node = nodeKeyPrefix + i;
            nodes.add(node);
            map.put(node, new ArrayList<>());
        }
        KetamaNodeLocator<String> nodeLocator = new KetamaNodeLocator<String>(nodes);

        for (int i = 0; i < 40; i++) {
            String item = "item-" + i;
            String nodeNumber = nodeLocator.getNode(item);
            map.get(nodeNumber).add(item);
        }

        System.out.println("######## nodes个数: " + nodes.size());
        for (String node : nodes) {
            System.out.printf("[node: %s, itemCount: %s, items: %s]", node, map.get(node).size(), map.get(node));
//            System.out.printf("[node: %s, itemCount: %s]", node, map.get(node).size());
            System.out.println();
        }
    }


}