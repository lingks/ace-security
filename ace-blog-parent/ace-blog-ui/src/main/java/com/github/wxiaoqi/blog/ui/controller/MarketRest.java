package com.github.wxiaoqi.blog.ui.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.blog.ui.controller.vo.Market;
import com.github.wxiaoqi.blog.ui.util.HttpClientUtil;
import org.asynchttpclient.AsyncHttpClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */
@RestController
@RequestMapping("api/market")
public class MarketRest {
    private static AsyncHttpClient asyncHttpClient;

    @RequestMapping(value = "/getByIds", method = RequestMethod.POST)
    @ResponseBody
    public List<Market> getByIds(@RequestBody String ids) throws Exception {

        long start = System.currentTimeMillis();
        String url = "https://block.cc/api/v1/coin/get?coin=";
        System.out.println(ids );

        String idss = "";
        if(ids != null && !"".equals(ids)){
            idss = ids.replace("%2C",",");
        }
        idss = idss.replace("ids=","");

        List<Market> markets = new ArrayList<Market>();
        for(String id :idss.split(",")){
            if(!"".equals(id)){
                String s = HttpClientUtil.doGet(url + id);
                JSONObject json = JSONObject.parseObject(s);
                System.out.println(json);
                if(json != null && !"".equals(json)){
                    if(json.getInteger("code") == 0){
                        if(json.getString("data") != null && !"".equals(json.getString("data"))){
                            Market market = JSONObject.parseObject(json.getString("data"), Market.class);
                            if(market.getZhName() != null && !"".equals(market.getZhName())){
                                market.setName(market.getName() +"-"+ market.getZhName());
                            }
                            System.out.println(market.getId());
                            markets.add(market);
                        }
                    }
                }
            }
        }

        System.out.println(markets);
        System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis() - start) / 1000f + " 秒 ");
        return markets;
    }

    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    public String getNews(){
        String url = "https://www.reddit.com/r/bitcoin.embed?limit=9";
        String s = HttpClientUtil.doGet(url);
        String ss = s.replace("/**/document.write(","");
        String sss =ss.replace(")","");
        System.out.println(sss);
        return sss;
    }
}


