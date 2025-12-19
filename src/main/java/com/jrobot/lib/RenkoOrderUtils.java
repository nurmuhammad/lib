package com.jrobot.lib;

import com.hazelcast.collection.ISet;
import com.hazelcast.core.HazelcastInstance;

public class RenkoOrderUtils {

    public static boolean orderContains(HazelcastInstance hazelcastInstance, String base, String counter) {
        ISet<String> renkoOrderSet = hazelcastInstance.getSet(Constant.HAZELCAST_OPENED_ORDER_RENKO_PAIR);
        return orderContains(renkoOrderSet, base, counter);
    }

    public static boolean orderContains(ISet<String> renkoOrderSet, String base, String counter) {
        String renkoOrder = "RENKO_ORDER_" + base + "_" + counter;
        return renkoOrderSet.contains(renkoOrder);
    }


}
