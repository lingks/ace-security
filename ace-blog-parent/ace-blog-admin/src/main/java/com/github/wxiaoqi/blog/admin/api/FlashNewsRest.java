package com.github.wxiaoqi.blog.admin.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.blog.admin.api.vo.ArticleData;
import com.github.wxiaoqi.blog.admin.api.vo.Buttom;
import com.github.wxiaoqi.blog.admin.api.vo.FlashData;
import com.github.wxiaoqi.blog.admin.api.vo.FlashVO;
import com.github.wxiaoqi.blog.admin.biz.ArticleBiz;
import com.github.wxiaoqi.blog.admin.biz.FlashNewsBiz;
import com.github.wxiaoqi.blog.admin.entity.Article;
import com.github.wxiaoqi.blog.admin.entity.FlashNews;
import com.github.wxiaoqi.blog.admin.mapper.FlashNewsMapper;
import com.github.wxiaoqi.blog.admin.util.HttpClientUtil;
import com.github.wxiaoqi.security.common.msg.ListRestResponse;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ace on 2017/7/16.
 */
@RestController
@RequestMapping("api/flash")
public class FlashNewsRest {
    @Autowired
    private FlashNewsBiz flashNewsBiz;

    @Autowired
    private FlashNewsMapper flashNewsMapper;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONPObject get(@PathVariable int id, String callback){
        return new JSONPObject(callback,new ObjectRestResponse<Article>().rel(true).result(flashNewsBiz.selectById(id)));
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject get(int pageIndex, int pageSize, String title, String callback){


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int count = flashNewsMapper.selectCountByCrtTime(getPastDate(7),df.format(new Date()),title);
        PageHelper.startPage(pageIndex, pageSize);

        return new JSONPObject(callback, new ListRestResponse<FlashNews>().rel(true).count(count).result( flashNewsMapper.selectByCrtTime(getPastDate(7),df.format(new Date()),title)));

    }


    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);

        return result;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public JSONPObject list(long timestamp, @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize, String callback){
        String url = "http://www.bishijie.com/api/news/?size=" + pageSize;
        if(timestamp > 0){
            url += "&timestamp="+ timestamp/1000;
        }else{
            url += "";
        }

        //System.out.println(url);
        String s = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(s);
        List<FlashVO> result = new ArrayList<FlashVO>();
        if(jsonObject.getInteger("error") == 0){
            List<FlashData> data = new ArrayList<FlashData>();
            FlashVO flashVO = new FlashVO();
            List<Buttom> buttomsList = new ArrayList<Buttom>();
            FlashData vo = new FlashData();
            for(Map.Entry<String, Object> entry : jsonObject.getJSONObject("data").entrySet()){

                flashVO.setDate(entry.getKey());

                JSONObject js = JSONObject.parseObject(entry.getValue().toString());
                List<Buttom> buttoms = JSONArray.parseArray(js.getString("buttom"),Buttom.class);

                buttomsList.addAll(buttoms);

                vo.setDate(js.getString("date"));
                vo.setTop(JSONArray.parseArray(js.get("top").toString(),String.class));



            }
            data.add(vo);
            flashVO.setData(data);
            vo.setButtom(buttomsList);
            result.add(flashVO);
        }
        return new JSONPObject(callback, new ListRestResponse<FlashNews>().rel(true).result(result));

    }

//
//    public static void main(String[] args) throws IOException{
//        String url = "https://ihuoqiu.com/Home/Index?data=W9F3j2vgufgdWZmdtGFOlg$_2C$$_2C$&pageIndex=1";
//
//        String s = HttpClientUtil.doPost(url);
//        JSONObject object = JSONObject.parseObject(s);
//        if(object.getString("code").equals("200") && object.getBoolean("success") == true){
//            String msg = object.getString("msg");
//            List<ArticleData> list = JSONArray.parseArray(msg, ArticleData.class);
//
//            for(ArticleData data : list){
//
//
//                String url2 = "https://ihuoqiu.com/Content/information?data=" + data.getData1();
//                Document doc = Jsoup.connect(url2).get();
//                Elements divs = doc.select("div .article");
//                for (Element element : divs) {
//                    System.out.println(element);
//                }
//            }
//        }
//
//
//
//
//    }


    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ObjectRestResponse get(long timestamp, @RequestParam(name = "id") String id, String callback) {
        Example example = new Example(FlashNews.class);
        if(id != null){
            example.createCriteria().andEqualTo("title", id);
        }

        List<FlashNews> list = flashNewsBiz.selectByExample(example);
        if(list!= null && list.size() >0){
            return new ObjectRestResponse<FlashNews>().rel(true).result(list.get(0));
        }
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<FlashNews> add(FlashNews entity){

        flashNewsMapper.insertSelective(entity);
        return new ObjectRestResponse<FlashNews>().rel(true);
    }
}
