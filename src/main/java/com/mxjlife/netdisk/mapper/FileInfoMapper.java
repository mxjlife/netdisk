package com.mxjlife.netdisk.mapper;

import com.mxjlife.netdisk.pojo.nd.FileInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * description: 文件信息 数据库操作接口
 * email realmxj@163.com
 * @author: mxj
 * date 2020-04-05 18:32:07
 * @version: V1.0
 */
@Mapper
public interface FileInfoMapper {

    /**
     * 添加数据
     */
    int insert(FileInfoPO fileInfo);

    /**
     * 使用主键 id 更新
     */
    int updateById(FileInfoPO fileInfo);

    /**
     * 使用主键 id 查询
     */
    FileInfoPO selectById(@Param("id") Long id);

    /**
     * 查询总条数
     */
    long getTotalCount();

    /**
     * 分页按条件查询信息
     * 参数说明：
     *   startTime  按照时间段过滤开始时间
     *   endTime  按照时间段过滤结束时间
     *   offset  按页查询的开始索引
     *   pageSize 页大小
     */
    List<FileInfoPO> selectPageByParams(@Param("params") Map<String, Object> params);

    /**
     * 按条件查询信息条数
     * 参数说明：
     *   startTime  按照时间段过滤开始时间
     *   endTime  按照时间段过滤结束时间
     *   offset  按页查询的开始索引
     *   pageSize 页大小
     */
    long selectCountByParams(@Param("params") Map<String, Object> params);



}
