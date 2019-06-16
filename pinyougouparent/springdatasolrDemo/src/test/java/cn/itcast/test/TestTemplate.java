package cn.itcast.test;

import cn.itcast.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;

import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testAdd() {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为 2 号专卖店");
        item.setTitle("华为 Mate9");
        item.setPrice(new BigDecimal(2000));
        solrTemplate.saveBean(item);
        solrTemplate.commit();
    }

    @Test
    public void findByid(){
        TbItem item = solrTemplate.getById(1L, TbItem.class);
        System.out.println(item.getBrand());
    }

    @Test
    public void  deleteByid(){
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }
    @Test
    public void testAddList(){
        List list = new ArrayList();
        for (int a = 0 ;a<100;a++){
            TbItem item = new TbItem();
            item.setId(a+1L);
            item.setBrand("华为"+a);
            item.setCategory("手机");
            item.setGoodsId(1L);
            item.setSeller("华为 2 号专卖店");
            item.setTitle("华为 Mate9");
            item.setPrice(new BigDecimal(2000+a));
            list.add(item);
        }
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    @Test
    public void testPageQuery(){
        Query query=new SimpleQuery("*:*");
        query.setOffset(20);//开始索引（默认 0）
        query.setRows(20);//每页记录数(默认 10)
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);


    //显示记录数据


        for(TbItem item:page.getContent()) {
            System.out.println(item.getTitle() +"   "+ item.getPrice()+"  "+item.getBrand());

        }
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数："+page.getTotalPages());

        }
        @Test

    public void deleteAll(){
            Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
    }

