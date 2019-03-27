package cn.umisoft.web.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * @description: <p>分页信息封装，搭配前端使用</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/24 9:19 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmiPage<T> implements IPage<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    private long total;
    private long pageSize;
    private long page;
    private List<T> list;

    @JSONField(serialize=false)
    private List<UmiQueryCondition> filters;

    public UmiPage() {
        this.total = 0L;
        this.pageSize = 10L;
        this.page = 1L;
        this.list = Collections.emptyList();
        this.filters = Collections.emptyList();
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public UmiPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getSize() {
        return this.pageSize;
    }

    @Override
    public UmiPage<T> setSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getCurrent() {
        return this.page;
    }

    @Override
    public UmiPage<T> setCurrent(long current) {
        this.page = current;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public List<T> getRecords() {
        return this.list;
    }

    @Override
    public UmiPage<T> setRecords(List<T> list) {
        this.list = list;
        return this;
    }

    @Override
    @JSONField(serialize=false)
    public long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    @Override
    @JSONField(serialize=false)
    public boolean isSearchCount() {
        return true;
    }
}
