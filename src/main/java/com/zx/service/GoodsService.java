package com.zx.service;

import com.github.pagehelper.PageInfo;
import com.zx.pojo.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> findAll();

    List<Goods> findByid(Integer id);

    int save(Goods goods);

    int update(Goods goods);

    int delete(List<Integer> ids);

    PageInfo selectByPage(Integer page, Integer size, String gname);

    public void test();
}
