package cn.umisoft.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @Description:
 * @Author: hujie@umisoft.cn
 * @Date: 2019/1/26 11:22 PM
 */
public interface UmiMapper<T> extends BaseMapper<T> {
    /**
     * @description: <p>封装逻辑删除，逻辑删除一律采用该方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/26 11:22 PM
     * @param: entity
     * @return: 受影响数据条数
     */
    int deleteByIdWithFill(T entity);

    @Override
    default int deleteById(Serializable id) {
        throw new RuntimeException("本系统启用了逻辑删除，该方法逻辑删除时，无法记录最后操作人和最后操作时间信息，请使用deleteByIdWithFill替代");
    }

    @Override
    default int deleteByMap(Map<String, Object> columnMap){
        throw new RuntimeException("本系统启用了逻辑删除，该方法逻辑删除时，无法记录最后操作人和最后操作时间信息，请使用deleteByIdWithFill替代");
    }

    @Override
    default int deleteBatchIds(Collection<? extends Serializable> idList){
        throw new RuntimeException("本系统启用了逻辑删除，该方法逻辑删除时，无法记录最后操作人和最后操作时间信息，请使用deleteByIdWithFill替代");
    }

    @Override
    default int delete(Wrapper<T> wrapper){
        throw new RuntimeException("本系统启用了逻辑删除，该方法逻辑删除时，无法记录最后操作人和最后操作时间信息，请使用deleteByIdWithFill替代");
    }
}
