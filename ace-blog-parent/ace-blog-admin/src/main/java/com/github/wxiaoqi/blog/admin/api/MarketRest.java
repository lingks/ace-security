package com.github.wxiaoqi.blog.admin.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wxiaoqi.blog.admin.entity.Market;
import com.github.wxiaoqi.blog.admin.util.HttpClientUtil;
import com.github.wxiaoqi.blog.admin.util.MarketResultResponse;
import com.github.wxiaoqi.blog.admin.util.NumberFormat;
import org.asynchttpclient.*;
import org.springframework.web.bind.annotation.*;

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
    public MarketResultResponse get(int limit, int page,Integer coinType) throws Exception {
        String where = "";
        if(coinType != null) {

            if (coinType == 1) {
                where += "coinType=1";
            }
            if (coinType == 2) {
                where += "coinType=2";
            }
            if (coinType == 3) {
                where += "mineable=1";
            }
            if (coinType == 4) {
                where += "mineable=0";
            }
        }

        requestUrl = "https://block.cc/api/v1/coin/list?page="+(page - 1)+"&size="+limit+"&select=volume_ex&"+where;
        MarketResultResponse<List<Market>> resultResponse = null;
        try {
            String s = HttpClientUtil.doGet(requestUrl);
            JSONObject json = JSONObject.parseObject(s);

            JSONObject data = JSONObject.parseObject(json.getString("data"));
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
               // market.setPrice(NumberFormat.toFied2(market.getPrice()));
                market.setChange1d(NumberFormat.toFied2(market.getChange1d()));
                market.setChange7d(NumberFormat.toFied2(market.getChange7d()));
                market.setMarketCap(NumberFormat.toFied2(market.getMarketCap()/10000));

                market.setVolume_ex(NumberFormat.toFied2(market.getVolume_ex()==null||market.getVolume_ex().equals("")?0:(Double.parseDouble(market.getVolume_ex())/10000)) + "");
                market.setAvailable_supply(NumberFormat.toFied2((market.getAvailable_supply()==null|| market.getAvailable_supply().equals(""))?0:(Double.parseDouble(market.getAvailable_supply())/10000)) + "");

            }
            resultResponse = new MarketResultResponse(json.getInteger("code"),array,"success",data.getInteger("pageCount") * limit);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultResponse;
    }



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public MarketResultResponse list(int limitType, int size , String select,@RequestParam(name = "diefu", defaultValue = "0") Integer diefu) throws Exception {
        requestUrl = "https://block.cc/api/v1/coin/list?size="+size+"&select="+select+"&limitType="+limitType;

        if(diefu == 1){
            if(limitType == 1) {
                requestUrl = "https://block.cc/api/v1/coin/list?size=8&orderby=1&select=" + select + "&limitType=1";
            }else{
                requestUrl = "https://block.cc/api/v1/coin/list?size=8&orderby=1&select=" + select;
            }
        }
            MarketResultResponse<List<Market>> resultResponse = null;
        try {
            String s = HttpClientUtil.doGet(requestUrl);
            JSONObject json = JSONObject.parseObject(s);

            JSONObject data = JSONObject.parseObject(json.getString("data"));
            List<Market> array = JSONArray.parseArray(data.getString("list"),Market.class);

            int i = 1;
            for(Market market : array){

                if(market.getZhName() != null && !"".equals(market.getZhName())){
                    market.setName(market.getName() +"-"+ market.getZhName());
                }
                market.setPrice(NumberFormat.toFied2(market.getPrice()));
                market.setChange1d(NumberFormat.toFied2(market.getChange1d()));
                market.setChange7d(NumberFormat.toFied2(market.getChange7d()));
                market.setMarketCap(NumberFormat.toFied2(market.getMarketCap()/10000));

                market.setVolume_ex(NumberFormat.toFied2(market.getVolume_ex()==null||market.getVolume_ex().equals("")?0:(Double.parseDouble(market.getVolume_ex())/10000)) + "");
                market.setAvailable_supply(NumberFormat.toFied2((market.getAvailable_supply()==null|| market.getAvailable_supply().equals(""))?0:(Double.parseDouble(market.getAvailable_supply())/10000)) + "");

            }
            resultResponse = new MarketResultResponse(json.getInteger("code"),array,"success");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultResponse;
    }


    @RequestMapping(value = "/getByIds", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getByIds(@RequestBody String ids) throws Exception {

        String url = "https://block.cc/api/v1/coin/get?coin=";
        System.out.println(ids );

        String idss = "";
        if(ids != null && !"".equals(ids)){
            idss = ids.replace("%2C",",");
        }
        idss = idss.replace("ids=","");
        System.out.println(idss);
        JSONArray array = new JSONArray();
        for(String id :idss.split(",")){
            if(!"".equals(id)){
                System.out.println(url + id);
                String s = HttpClientUtil.doGet(url + id);
                JSONObject json = JSONObject.parseObject(s);
                System.out.println(json);

                array.add(json);
            }
        }

        return array;
    }

//
//    public static void main(String[] args) {
//        String url = "https://block.cc/api/v1/coin/get?coin=bitcoin";
//        String s = HttpClientUtil.doGet(url );
//        JSONObject json = JSONObject.parseObject(s);
//        System.out.println(json);
//
//
//    }
}


