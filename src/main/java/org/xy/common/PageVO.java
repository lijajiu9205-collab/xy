package org.xy.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页出参的统一包装
 *
 * 为什么需要它：MyBatis-Plus 查出来的 Page 对象，字段名是 records / current / size / total / pages，
 * 而我们接口文档约定出参是 { total, pages, list }。名字对不上，前端就取不到数据，
 * 所以在中间加一层，把 records 改名成 list 再返回。
 *
 * 加泛型 <T> 是因为：商品列表、订单列表……所有分页接口都能用它，不用每张表写一个。
 */
@Data
public class PageVO<T> {
    // 总条数（不是总页数）
    private Long total;
    // 总页数
    private Long pages;
    // 当前这一页的数据
    private List<T> list;

    /**
     * 把 MP 的 Page 转成文档约定的格式
     * 调用方：PageVO.of(page)
     */
    public static <T> PageVO<T> of(IPage<T> page) {
        PageVO<T> vo = new PageVO<>();
        vo.setTotal(page.getTotal());
        vo.setPages(page.getPages());
        vo.setList(page.getRecords());   // 关键一步：records 改名 list
        return vo;
    }
}
