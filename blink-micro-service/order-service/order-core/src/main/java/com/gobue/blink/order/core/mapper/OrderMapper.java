package com.gobue.blink.order.core.mapper;

import com.gobue.blink.order.core.model.OrderDO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from t_order where orderId = #{orderId}")
    OrderDO getByOrderId(String orderId);

    @Delete("delete from t_order where id = #{id}")
    int deleteById(Long id);

    @Update("update t_order set name = #{demo.name} where id = #{demo.id}")
    int update(@Param("demo") OrderDO orderDO);

    // 分页查询必须写在xml里
    Page<OrderDO> query(@Param("id") Long id, @Param("page") Pageable pageRequest);

    @Insert({"insert into t_order (name) values (#{order.name})"})
    @Options(useGeneratedKeys = true, keyProperty = "account.id", keyColumn = "id")
    int create(@Param("order") OrderDO orderDO);

    // xml中统计
    List<OrderDO> stat();

}
