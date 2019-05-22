package com.ssslinppp.ketama;

import java.util.Collection;
import java.util.TreeMap;

/**
 * Description： 一致性Hash算法：是一种分布式算法，常用于负载均衡;<br/>
 * 当node数发生变化时，如何保证尽量少引起迁移呢？
 * 即当增加或者删除节点时，对于大多数item，保证原来分配到的某个node，现在仍然应该分配到那个node，将数据迁移量的降到最低
 * <p>
 * To change this template use File | Settings | File Templates.
 */
public final class KetamaNodeLocator<T> {
    /**
     * 虚拟节点复制个数，目的：解决node分配不均匀的问题；
     * 越大，分配越均匀
     */
    private static int numReps = 160;
    private TreeMap<Long, T> ketamaNodes;
    private HashAlgorithm hashAlg;

    public KetamaNodeLocator(Collection<T> nodes) {
        this(nodes, HashAlgorithm.KETAMA_HASH);
    }

    public KetamaNodeLocator(Collection<T> nodes, HashAlgorithm alg) {
        this(nodes, HashAlgorithm.KETAMA_HASH, numReps);
    }

    /**
     * 一致性hash算法，节点分配;
     *
     * @param nodes      node集合
     * @param alg        Hash算法
     * @param nodeCopies 虚拟节点个数，每一个node都对应nodeCopies个虚拟节点
     */
    public KetamaNodeLocator(Collection<T> nodes, HashAlgorithm alg, int nodeCopies) {
        hashAlg = alg;
        ketamaNodes = new TreeMap<Long, T>();
        numReps = nodeCopies;
        for (T node : nodes) {
            for (int i = 0; i < numReps / 4; i++) {
                byte[] digest = hashAlg.computeMd5(node.toString() + i);
                for (int h = 0; h < 4; h++) {
                    long m = hashAlg.hash(digest, h);
                    ketamaNodes.put(m, node);
                }
            }
        }
    }

    public T getNode(final String k) {
        byte[] digest = hashAlg.computeMd5(k);
        T rv = getNodeForKey(hashAlg.hash(digest, 0));
        return rv;
    }

    /**
     * 使用TreeMap来实现顺时针查询最近node
     *
     * @param hash
     * @return
     */
    private T getNodeForKey(long hash) {
        if (ketamaNodes.isEmpty()) {
            return null;
        }
        if (!ketamaNodes.containsKey(hash)) {
            //Returns the least key greater than or equal to the given key, or null if there is no such key.
            Object ceilValue = ((TreeMap<Long, T>) ketamaNodes).ceilingKey(hash);
            if (ceilValue != null) {
                try {
                    hash = Long.valueOf(ceilValue.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    hash = 0;
                }
            }
            if (ceilValue == null || hash == 0) { // 找不到时，取第一个元素，构成一个闭环集合
                hash = ketamaNodes.firstKey();
            }
        }
        return ketamaNodes.get(hash);
    }
}
