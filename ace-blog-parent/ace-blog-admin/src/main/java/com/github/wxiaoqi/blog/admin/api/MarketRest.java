package com.github.wxiaoqi.blog.admin.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.blog.admin.entity.Market;
import com.github.wxiaoqi.blog.admin.util.HttpClientUtil;
import com.github.wxiaoqi.blog.admin.util.MarketResultResponse;
import org.asynchttpclient.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */
@RestController
@RequestMapping("api/market")
public class MarketRest {
    private static AsyncHttpClient asyncHttpClient;
    public static  String requestUrl = "";

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public MarketResultResponse get(int limit, int page) throws Exception {
        requestUrl = "https://block.cc/api/v1/coin/list?page="+(page - 1)+"&size="+limit+"&select=volume_ex";
        MarketResultResponse<List<Market>> resultResponse = null;
        try {
            String s = HttpClientUtil.doGet(requestUrl);
            JSONObject json = JSONObject.parseObject(s);

            JSONObject data = JSONObject.parseObject(json.getString("data"));
            System.out.println(json);

            List<Market> array = JSONArray.parseArray(data.getString("list"),Market.class);

            int i = 1;
            for(Market market : array){
                if(page == 1){
                    market.setSort(i);
                }else{
                    market.setSort(page * limit + i);
                }
                i ++;
                if(market.getZhName() != null && !"".equals(market.getZhName())){
                    market.setName(market.getName() +"-"+ market.getZhName());
                }
            }
            resultResponse = new MarketResultResponse(json.getInteger("code"),array,"success",data.getInteger("pageCount") * limit);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultResponse;
    }

//    public static void main(String[] args) {
//        String s = HttpClientUtil.doGet(requestUrl);
//        JSONObject json = JSONObject.parseObject(s);
//
//        JSONObject data = JSONObject.parseObject(json.getString("data"));
//        System.out.println(json);
//
//        JSONArray array = JSONArray.parseArray(data.getString("list"));
//
//
//        MarketResultResponse resultResponse = new MarketResultResponse(json.getInteger("code"),array,"success");
//        System.out.println(resultResponse);
//        System.out.println(s);
//    }
}


