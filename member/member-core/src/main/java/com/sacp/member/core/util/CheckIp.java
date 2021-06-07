package com.sacp.member.core.util;

import org.apache.commons.lang.StringUtils;
import org.lionsoul.ip2region.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class CheckIp {
    private static Logger logger = LoggerFactory.getLogger(CheckIp.class);
    private static final String UNKOWN_ADDRESS = "未知位置";

    /**
     * 根据IP获取地址
     *
     * @return 国家|区域|省份|城市|ISP
     */
    public static String getAddress(DbSearcher searcher,String ip) throws IOException, DbMakerConfigException {
        return getAddress(searcher, ip, DbSearcher.BTREE_ALGORITHM);
    }

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @param algorithm 查询算法
     * @return 国家|区域|省份|城市|ISP
     * DbSearcher.BTREE_ALGORITHM; //B-tree
     * DbSearcher.BINARY_ALGORITHM //Binary
     * DbSearcher.MEMORY_ALGORITYM //Memory
     */
    public static String getAddress(DbSearcher searcher,String ip, int algorithm) throws IOException, DbMakerConfigException {
        if (!Util.isIpAddress(ip)) {
            logger.error("错误格式的ip地址: {}", ip);
            return UNKOWN_ADDRESS;
        }
        DataBlock dataBlock;
        switch (algorithm) {
            case DbSearcher.BTREE_ALGORITHM:
                dataBlock = searcher.btreeSearch(ip);
                break;
            case DbSearcher.BINARY_ALGORITHM:
                dataBlock = searcher.binarySearch(ip);
                break;
            case DbSearcher.MEMORY_ALGORITYM:
                dataBlock = searcher.memorySearch(ip);
                break;
            default:
                logger.error("未传入正确的查询算法");
                return UNKOWN_ADDRESS;
        }
        return dataBlock.getRegion();
    }
}
