package com.example.shardingSphere.algorithem;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

public class MyRangeTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> shardingValue) {
        //select * from course where cid between 1 and 100;
        Long upper = shardingValue.getValueRange().upperEndpoint();
        Long lower = shardingValue.getValueRange().lowerEndpoint();

        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName+"_1",logicTableName+"_2");
    }
}
