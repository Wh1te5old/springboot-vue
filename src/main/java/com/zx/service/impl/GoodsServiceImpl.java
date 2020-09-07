package com.zx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.bo.GoodsBiz;
import com.zx.mapper.GoodsMapper;
import com.zx.mapper.TypeMapper;
import com.zx.pojo.Goods;
import com.zx.pojo.Type;
import com.zx.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public List<Goods> findByid(Integer id) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gid",id);
        List<Goods> goods = goodsMapper.selectByExample(example);
        return goods!=null?goods:null;
    }

    @Override
    public int save(Goods goods) {
        //insertSelective   如果goods某值为空 就会插入一个空值进去  sql:INSERT INTO goods ( gid,gname,tid ) VALUES( ?,?,? )
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public int delete(List<Integer> ids) {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("gid",ids);
        return goodsMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<GoodsBiz> selectByPage(Integer page, Integer size, String gname) {
        PageHelper.startPage(page,size);
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if (gname!=null&&!gname.equals("")){
            criteria.andLike("gname",gname);
        }
        List<Goods> glist = goodsMapper.selectByExample(example);
        List<GoodsBiz> collect = glist.stream().map(g -> {
            GoodsBiz biz = new GoodsBiz();
            BeanUtils.copyProperties(g, biz);
            Type type = new Type();
            type.setTid(g.getTid());
            List<Type> select = typeMapper.select(type);
            Type type1 = select.get(0);
            biz.setTname(type1.getTname());
            return biz;
        }).collect(Collectors.toList());
        PageInfo<GoodsBiz> info = new PageInfo<GoodsBiz>(collect);
        return info;
    }

    @Override
    public void test(){

    }


}
