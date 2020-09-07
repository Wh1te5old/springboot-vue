package com.zx.controller;

import com.github.pagehelper.PageInfo;
import com.zx.bo.GoodsBiz;
import com.zx.pojo.Goods;
import com.zx.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    public List<Goods> selectById() {
        goodsService.test();
        return goodsService.findAll();
    }


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/findByid/{id}")
    public List<Goods> selectById(@PathVariable Integer id) {
        return goodsService.findByid(id);
    }

    @GetMapping("/findByPage")
    public PageInfo<GoodsBiz> selectByPage(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
                                        @RequestParam("gname") String gname){
        return goodsService.selectByPage(page,size,gname);
    }

    /**
     * 新增
      * @param goods
     * @return
     */
    @PostMapping("/save")
    public int save(@RequestBody Goods goods){
        return goodsService.save(goods);
    }

    /**
     * 根据ID修改
     * @param goods
     * @return
     */
    @PutMapping("/updata")
    public int update(@RequestBody Goods goods){
        return goodsService.update(goods);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public int delelte(@RequestParam(value = "ids") List<Integer> ids){
        return goodsService.delete(ids);
    }


}
