package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public List<TbBrand> findAll();


    /*
     * 品牌分页
     * @param  pageNum  当前页面
     * @param  pageSize  每页记录数
     * */
    public PageResult findPage(int pageNum, int pageSize);


    /*
     * 增加
     * */
    public void add(TbBrand brand);


    /*

     * 根据ID查询实体
     *
     * */
    public TbBrand findOne(Long id);


    /*
     * 修改
     *
     * */
    public void update(TbBrand brand);


    /*
     * 删除
     * */
    public void delete(Long[] ids);


    /*
     * 查询
     * */
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize);

    /*
     *返回下拉列表数据
     * */
    List<Map> selectOptionList();
}
