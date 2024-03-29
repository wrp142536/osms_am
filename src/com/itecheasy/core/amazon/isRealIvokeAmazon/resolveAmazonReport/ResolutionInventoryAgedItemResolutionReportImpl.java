package com.itecheasy.core.amazon.isRealIvokeAmazon.resolveAmazonReport;

import com.itecheasy.core.amazon.vo.AmazonInventoryAgedReportVO;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: liteng
 * @Date: 2018/8/10 11:33
 * @Description: InventoryAgedItem的解析
 */
public class ResolutionInventoryAgedItemResolutionReportImpl implements ResolutionReportFile {

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



    //FileToVoJson
    @Override
    public String fileToJson(List<String> filePathS, Integer shopId, Map<String, Integer> indexMap) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AmazonInventoryAgedReportVO> reportFormList = new ArrayList<AmazonInventoryAgedReportVO>(); //所有的报告文件绝对路径
        for (String filePath : filePathS) {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(filePath)));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();    //读取第一行
            line = br.readLine();   //第二行开始才是数据
            while (line != null) {
                String[] splitSingle = line.split("\t"); //取得每一行的每一个
                List<String> single = Arrays.asList(splitSingle);
                AmazonInventoryAgedReportVO amazonInventoryAgedReportVO = new AmazonInventoryAgedReportVO();
                amazonInventoryAgedReportVO.setFnsku(single.get(indexMap.get("fnsku")));
                amazonInventoryAgedReportVO.setProductName(single.get(indexMap.get("productName")));
                amazonInventoryAgedReportVO.setCondition(single.get(indexMap.get("condition")));
                amazonInventoryAgedReportVO.setSku(single.get(indexMap.get("sku")));
                amazonInventoryAgedReportVO.setAsin(single.get(indexMap.get("asin")));
                if (isNotNull(indexMap, single, "yourPrice")) {
                    amazonInventoryAgedReportVO.setYourPrice(new BigDecimal(single.get(indexMap.get("yourPrice"))));
                }

                Date snapshotDate = null;
                try {
                    snapshotDate = sdf.parse(single.get(indexMap.get("snapshotDate")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                amazonInventoryAgedReportVO.setSnapshotDate(snapshotDate);
                amazonInventoryAgedReportVO.setShopId(shopId);
                Date date = new Date();
                amazonInventoryAgedReportVO.setSyncLast(date);
                amazonInventoryAgedReportVO.setSyncFirst(date);

                amazonInventoryAgedReportVO.setAvaliableQuantitySellable(new BigDecimal((single.get(indexMap.get("avaliableQuantitySellable")))));

                amazonInventoryAgedReportVO.setQtyWithRemovalsInProgress(new BigDecimal((single.get(indexMap.get("qtyWithRemovalSinprogress")))));
                amazonInventoryAgedReportVO.setInvAge0To90Days(new BigDecimal((single.get(indexMap.get("invAge0to90days")))));
                amazonInventoryAgedReportVO.setInvAge91To180Days(new BigDecimal((single.get(indexMap.get("invAge91to180days")))));
                amazonInventoryAgedReportVO.setInvAge181To270Days(new BigDecimal((single.get(indexMap.get("invAge181to270days")))));
                amazonInventoryAgedReportVO.setInvAge271To365Days(new BigDecimal((single.get(indexMap.get("invAge271to365days")))));
                amazonInventoryAgedReportVO.setInvAge365PlusDays(new BigDecimal((single.get(indexMap.get("invAge365plusdays")))));

                amazonInventoryAgedReportVO.setCurrency(single.get(indexMap.get("currency")));

                if (isNotNull(indexMap, single, "salesPrice")) {
                    amazonInventoryAgedReportVO.setSalesPrice(new BigDecimal(single.get(indexMap.get("salesPrice"))));
                }

                if (isNotNull(indexMap, single, "qtyTobechargedltsf6mo")) {
                    amazonInventoryAgedReportVO.setQtyToBeChargedLtsf6Mo(new BigDecimal(single.get(indexMap.get("qtyTobechargedltsf6mo"))));
                }
                if (isNotNull(indexMap, single, "projectedLtsf6mo")) {
                    amazonInventoryAgedReportVO.setProjectedLtsf6Mo(new BigDecimal(single.get(indexMap.get("projectedLtsf6mo"))));
                }

                if (isNotNull(indexMap, single, "qtyTobechargedltsf12mo")) {
                    amazonInventoryAgedReportVO.setQtyToBeChargedLtsf12Mo(new BigDecimal(single.get(indexMap.get("qtyTobechargedltsf12mo"))));
                }
                if (isNotNull(indexMap, single, "unitsShippedlast7days")) {
                    amazonInventoryAgedReportVO.setUnitsShippedLast7Days((new BigDecimal(single.get(indexMap.get("unitsShippedlast7days")))));
                }
                if (isNotNull(indexMap, single, "projectedLtsf12mo")) {
                    amazonInventoryAgedReportVO.setProjectedLtsf12Mo((new BigDecimal(single.get(indexMap.get("projectedLtsf12mo")))));
                }
                if (isNotNull(indexMap, single, "unitsShippedlast7days")) {
                    amazonInventoryAgedReportVO.setUnitsShippedLast7Days((new BigDecimal(single.get(indexMap.get("unitsShippedlast7days")))));
                }
                if (isNotNull(indexMap, single, "unitsShippedlast30days")) {
                    amazonInventoryAgedReportVO.setUnitsShippedLast30Days(new BigDecimal(single.get(indexMap.get("unitsShippedlast30days"))));
                }
                if (isNotNull(indexMap, single, "unitsShippedlast60days")) {
                    amazonInventoryAgedReportVO.setUnitsShippedLast60Days((new BigDecimal(single.get(indexMap.get("unitsShippedlast60days")))));
                }
                if (isNotNull(indexMap, single, "unitsShippedlast90days")) {
                    amazonInventoryAgedReportVO.setUnitsShippedLast90Days((new BigDecimal(single.get(indexMap.get("unitsShippedlast90days")))));
                }


                amazonInventoryAgedReportVO.setAlert(single.get(indexMap.get("alert")));


                if (isNotNull(indexMap, single, "lowestPriceNew")) {
                    amazonInventoryAgedReportVO.setLowestPriceNew((new BigDecimal(single.get(indexMap.get("lowestPriceNew")))));
                }
                if (isNotNull(indexMap, single, "lowestPriceUsed")) {
                    amazonInventoryAgedReportVO.setLowestPriceUsed((new BigDecimal(single.get(indexMap.get("lowestPriceUsed")))));
                }
//                if (isNotNull(indexMap, single, "RecommendedAction")) {
                amazonInventoryAgedReportVO.setRecommendedAction(single.get(indexMap.get("RecommendedAction")));
//                }

                if (isNotNull(indexMap, single, "HealthyInventoryLevel")) {
                    amazonInventoryAgedReportVO.setHealthyInventoryLevel((new BigDecimal(single.get(indexMap.get("HealthyInventoryLevel")))));
                }
                if (isNotNull(indexMap, single, "RecommendedSalesPrice")) {
                    amazonInventoryAgedReportVO.setRecommendedSalesPrice((new BigDecimal(single.get(indexMap.get("RecommendedSalesPrice")))));
                }
                if (isNotNull(indexMap, single, "RecommendedSaleDurationDays")) {
                    amazonInventoryAgedReportVO.setRecommendedSaleDurationDays((new BigDecimal(single.get(indexMap.get("RecommendedSaleDurationDays")))));
                }
                if (isNotNull(indexMap, single, "RecommendedRemovalQuantity")) {
                    amazonInventoryAgedReportVO.setRecommendedRemovalvQuantity(new BigDecimal(single.get(indexMap.get("RecommendedRemovalQuantity"))));
                }
                if (isNotNull(indexMap, single, "EstimatedCostSavingsOfRemoval")) {
                    amazonInventoryAgedReportVO.setEstimatedCostSavingsOfRemoval((new BigDecimal(single.get(indexMap.get("EstimatedCostSavingsOfRemoval")))));
                }
                if (isNotNull(indexMap, single, "sellThrough")) {
                    amazonInventoryAgedReportVO.setSellThrough((new BigDecimal(single.get(indexMap.get("sellThrough")))));
                }
                if (isNotNull(indexMap, single, "cubicFeet")) {
                    amazonInventoryAgedReportVO.setCubicFeet(new BigDecimal(single.get(indexMap.get("cubicFeet"))));
                }
//                if (isNotNull(indexMap, single, "storageType")) {
                amazonInventoryAgedReportVO.setStorageType(single.get(indexMap.get("storageType")));
//                }

                //            if (single.get(indexMap.get("mfnFulfillableQuantity")) != null && !"".equalsIgnoreCase(single.get(indexMap.get("mfnFulfillableQuantity")))) {
                //                amazonInventoryAgedReportVO.setMfnFulfillableQuantity(new BigDecimal((single.get(indexMap.get("mfnFulfillableQuantity"))));
                //            }

                //            amazonInventoryAgedReportVO.setAfnListingExists("No".equalsIgnoreCase(single.get(indexMap.get("afnListingExists"))) ? 0 : 1);

                reportFormList.add(amazonInventoryAgedReportVO);
                line = br.readLine(); // 一次读入一行数据
            }
            br.close();
        }

        LOGGER.error("completeSync--------shopId:" + shopId + "-------------get amazon reportStock step4，resolve file  " + filePathS);

        //最后转为json返回
        return MAPPER.writeValueAsString(reportFormList);

    }

    //FileToVoJson

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
        int condition = singleFirst.indexOf("condition");
        int yourPrice = singleFirst.indexOf("your-price");
        int snapshot_date = singleFirst.indexOf("snapshot-date");

        //拼接为key val
        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        indexMap.put("sku", sku);
        indexMap.put("fnsku", fnsku);
        indexMap.put("asin", asin);
        indexMap.put("productName", productName);
        indexMap.put("condition", condition);
        indexMap.put("yourPrice", yourPrice);
        indexMap.put("snapshotDate", snapshot_date);

        indexMap.put("avaliableQuantitySellable", singleFirst.indexOf("avaliable-quantity(sellable)"));
        indexMap.put("qtyWithRemovalSinprogress", singleFirst.indexOf("qty-with-removals-in-progress"));
        indexMap.put("invAge0to90days", singleFirst.indexOf("inv-age-0-to-90-days"));
        indexMap.put("invAge91to180days", singleFirst.indexOf("inv-age-91-to-180-days"));
        indexMap.put("invAge181to270days", singleFirst.indexOf("inv-age-181-to-270-days"));
        indexMap.put("invAge271to365days", singleFirst.indexOf("inv-age-271-to-365-days"));
        indexMap.put("invAge365plusdays", singleFirst.indexOf("inv-age-365-plus-days"));
        indexMap.put("currency", singleFirst.indexOf("currency"));
        indexMap.put("qtyTobechargedltsf6mo", singleFirst.indexOf("qty-to-be-charged-ltsf-6-mo"));
        indexMap.put("projectedLtsf6mo", singleFirst.indexOf("projected-ltsf-6-mo"));
        indexMap.put("qtyTobechargedltsf12mo", singleFirst.indexOf("qty-to-be-charged-ltsf-12-mo"));
        indexMap.put("projectedLtsf12mo", singleFirst.indexOf("projected-ltsf-12-mo"));
        indexMap.put("unitsShippedlast7days", singleFirst.indexOf("units-shipped-last-7-days"));
        indexMap.put("unitsShippedlast30days", singleFirst.indexOf("units-shipped-last-30-days"));
        indexMap.put("unitsShippedlast60days", singleFirst.indexOf("units-shipped-last-60-days"));
        indexMap.put("unitsShippedlast90days", singleFirst.indexOf("units-shipped-last-90-days"));
        indexMap.put("alert", singleFirst.indexOf("alert"));

        indexMap.put("salesPrice", singleFirst.indexOf("sales_price"));
        indexMap.put("lowestPriceNew", singleFirst.indexOf("lowest_price_new"));
        indexMap.put("lowestPriceUsed", singleFirst.indexOf("lowest_price_used"));
        indexMap.put("RecommendedAction", singleFirst.indexOf("Recommended action"));
        indexMap.put("HealthyInventoryLevel", singleFirst.indexOf("Healthy Inventory Level"));
        indexMap.put("RecommendedSalesPrice", singleFirst.indexOf("Recommended sales price"));
        indexMap.put("RecommendedSaleDurationDays", singleFirst.indexOf("Recommended sale duration (days)"));
        indexMap.put("RecommendedRemovalQuantity", singleFirst.indexOf("Recommended Removal Quantity"));
        indexMap.put("EstimatedCostSavingsOfRemoval", singleFirst.indexOf("Estimated cost savings of removal"));
        indexMap.put("sellThrough", singleFirst.indexOf("sell-through"));
        indexMap.put("cubicFeet", singleFirst.indexOf("cubic-feet"));
        indexMap.put("storageType", singleFirst.indexOf("storage-type"));

        br.close();
        return indexMap;

    }
}
