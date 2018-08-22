package com.itecheasy.core.amazon.isRealIvokeAmazon.resolveAmazonReport;

import com.itecheasy.core.amazon.vo.AmazonInventoryAgedReportVO;
import com.itecheasy.core.amazon.vo.AmazonLogisticsForecastCostReportVO;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: liteng
 * @Date: 2018/8/22 15:49
 * @Description:
 */
public class LogisticsForecastCostReportImpl implements ResolutionReportFile {

    private static final Logger LOGGER = Logger.getLogger(ResolutionInventoryAgedItemResolutionReportImpl.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 判断字段是否为空
     *
     * @param indexMap
     * @param single
     * @param indexMapKey
     * @return
     */
    public static boolean isNotNull(Map<String, Integer> indexMap, List<String> single, String indexMapKey) {
        if (single.get(indexMap.get(indexMapKey)) != null && !"".equalsIgnoreCase(single.get(indexMap.get(indexMapKey)).trim())) {
            return true;
        }
        return false;
    }



    /**
     * FileToVoJson
     * @param filePathS
     * @param shopId
     * @param indexMap
     * @return
     * @throws IOException
     */
    @Override
    public String fileToJson(List<String> filePathS, Integer shopId, Map<String, Integer> indexMap) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AmazonLogisticsForecastCostReportVO> reportFormList = new ArrayList<AmazonLogisticsForecastCostReportVO>(); //所有的报告文件绝对路径
        for (String filePath : filePathS) {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(filePath)));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            br.readLine();    //读取第一行
            line = br.readLine();   //第二行开始才是数据
            while (line != null) {
                String[] splitSingle = line.split("\t"); //取得每一行的每一个
                List<String> single = Arrays.asList(splitSingle);

                AmazonLogisticsForecastCostReportVO logisticsForecastCostReportVO = new AmazonLogisticsForecastCostReportVO();

                logisticsForecastCostReportVO.setShopId(shopId);
                logisticsForecastCostReportVO.setSyncLast(date);
                logisticsForecastCostReportVO.setSyncFirst(date);

                logisticsForecastCostReportVO.setFnsku(single.get(indexMap.get("fnsku")));
                logisticsForecastCostReportVO.setProductName(single.get(indexMap.get("productName")));

                logisticsForecastCostReportVO.setSku(single.get(indexMap.get("sku")));
                logisticsForecastCostReportVO.setAsin(single.get(indexMap.get("asin")));
                if (isNotNull(indexMap, single, "yourPrice")) {
                    logisticsForecastCostReportVO.setYourPrice(new BigDecimal(single.get(indexMap.get("yourPrice"))));
                }

                logisticsForecastCostReportVO.setCurrency(single.get(indexMap.get("currency")));




                if (isNotNull(indexMap, single, "salesPrice")) {
                    logisticsForecastCostReportVO.setSalesPrice(new BigDecimal(single.get(indexMap.get("salesPrice"))));
                }
                
                reportFormList.add(logisticsForecastCostReportVO);
                line = br.readLine(); // 一次读入一行数据
            }
            br.close();
        }

        LOGGER.error("completeSync--------shopId:" + shopId + "-------------get amazon step4，resolve file  " + filePathS);

        //最后转为json返回
        return MAPPER.writeValueAsString(reportFormList);

    }

    /**
     * 根据报告来过去对应字段的下标
     *
     * @param filePath 传入第一行，之后分割
     * @return 一个对应位置的map
     */
    @Override
    public Map<String, Integer> getReportIndex(String filePath) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(filePath))); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();    //读取第一行
//        String[] splitRows = line.split("\r\n");  //根据换行符来分割，得到每行的数据
//        String[] splitRowFirst = splitRows[0].split("\t"); //取得每一行的每一个
        String[] splitRowFirst = line.split("\t"); //取得每一行的每一个
        List<String> singleFirst = Arrays.asList(splitRowFirst);
        int sku = singleFirst.indexOf("sku");
        int fnsku = singleFirst.indexOf("fnsku");
        int asin = singleFirst.indexOf("asin");
        int productName = singleFirst.indexOf("product-name");
        int yourPrice = singleFirst.indexOf("your-price");

        int productGroup = singleFirst.indexOf("product-group");
        int brand = singleFirst.indexOf("brand");
        int salesPrice = singleFirst.indexOf("sales-price");

        int fulfilledBy = singleFirst.indexOf("fulfilled-by");
        int longestSide = singleFirst.indexOf("longest-side");
        int medianSide = singleFirst.indexOf("median-side");
        int shortestSide = singleFirst.indexOf("shortest-side");
        int lengthAndGirth = singleFirst.indexOf("length-and-girth");
        int unitOfDimension = singleFirst.indexOf("unit-of-dimension");
        int itemPackageWeight = singleFirst.indexOf("item-package-weight");
        int unitOfWeight = singleFirst.indexOf("unit-of-weight");
        int productSizeTier = singleFirst.indexOf("product-size-tier");


        int currency = singleFirst.indexOf("currency");
        int estimatedFeeTotal = singleFirst.indexOf("estimated-fee-total");
        int estimatedReferralFeePerUnit = singleFirst.indexOf("estimated-referral-fee-per-unit");
        int estimatedVariableClosingFee = singleFirst.indexOf("estimated-variable-closing-fee");
        int estimatedOrderHandlingFeePerOrder = singleFirst.indexOf("estimated-order-handling-fee-per-order");

        int estimatedPickPackFeePerUnit = singleFirst.indexOf("estimated-pick-pack-fee-per-unit");
        int estimatedWeightHandlingFeePerUnit = singleFirst.indexOf("estimated-weight-handling-fee-per-unit");
        int expectedFulfillmentFeePerUnit = singleFirst.indexOf("expected-fulfillment-fee-per-unit");


        //拼接为key val
        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        indexMap.put("sku", sku);
        indexMap.put("fnsku", fnsku);
        indexMap.put("asin", asin);
        indexMap.put("productName", productName);
        indexMap.put("yourPrice", yourPrice);
        indexMap.put("currency", currency);

        indexMap.put("productGroup", productGroup);
        indexMap.put("brand", brand);
        indexMap.put("salesPrice", salesPrice);
        indexMap.put("fulfilledBy", fulfilledBy);
        indexMap.put("longestSide", longestSide);
        indexMap.put("medianSide", medianSide);
        indexMap.put("shortestSide", shortestSide);
        indexMap.put("lengthAndGirth", lengthAndGirth);
        indexMap.put("unitOfDimension", unitOfDimension);
        indexMap.put("itemPackageWeight", itemPackageWeight);
        indexMap.put("unitOfWeight", unitOfWeight);
        indexMap.put("productSizeTier", productSizeTier);
        indexMap.put("estimatedFeeTotal", estimatedFeeTotal);
        indexMap.put("estimatedReferralFeePerUnit", estimatedReferralFeePerUnit);
        indexMap.put("estimatedVariableClosingFee", estimatedVariableClosingFee);
        indexMap.put("estimatedOrderHandlingFeePerOrder", estimatedOrderHandlingFeePerOrder);
        indexMap.put("estimatedPickPackFeePerUnit", estimatedPickPackFeePerUnit);
        indexMap.put("estimatedWeightHandlingFeePerUnit", estimatedWeightHandlingFeePerUnit);
        indexMap.put("expectedFulfillmentFeePerUnit", expectedFulfillmentFeePerUnit);

        br.close();
        return indexMap;

    }


}
