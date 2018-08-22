package com.itecheasy.core.amazon.isRealIvokeAmazon;

import com.amazonaws.mws.MarketplaceWebServiceException;
import com.itecheasy.common.util.DeployProperties;
import com.itecheasy.core.amazon.ALLReportUltimateVO;
import com.itecheasy.core.amazon.AmazonConfigInfo;
import com.itecheasy.core.amazon.isRealIvokeAmazon.resolveAmazonReport.ResolutionReportFile;
import com.itecheasy.core.amazon.vo.RequestReportVO;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liteng
 * @Date: 2018/7/14 16:34
 * @Description:
 */
public class MockGetStockReportFromAmazonImpl implements IsRealGetStockReportFromAmazon {

    private static final Logger LOGGER = Logger.getLogger(MockGetStockReportFromAmazonImpl.class);
    private static String mockTxt;
    private Map<String, ResolutionReportFile> resolutionReportFileMap = resolutionReportFileMap = new HashMap<String, ResolutionReportFile>();
    private ResolutionReportFile resolutionReportFile;

    /**
     * 构造方法
     */
    public MockGetStockReportFromAmazonImpl() {
        this.mockTxt = DeployProperties.getInstance().getProperty("mock.inputStream.file");

    }

    public void setResolutionReportFileMap(Map<String, ResolutionReportFile> resolutionReportFileMap) {
        this.resolutionReportFileMap = resolutionReportFileMap;
    }

    /**
     * 初始化方法
     */
    public void initResolutionReportFile() {

//		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
//		FileOutputOriginalVersionFactory ultimateGetReportFactory = (FileOutputOriginalVersionFactory) act.getBean("ultimateGetReportFactory");
//		ResolutionReportFile inventoryAged = ultimateGetReportFactory.getAmazonReportType("inventoryAged");
    }

    /**
     * 切换bean
     *
     * @param str 报告的枚举类型来动态切换bean
     */
    private void getResolutionReportFileBean(String str) {
        if (GetReportType.获取亚马逊库龄报告.enumType.equals(str)) {
            LOGGER.error("bean cast to resolutionInventoryAgedItem");
            this.resolutionReportFile = resolutionReportFileMap.get("resolutionInventoryAgedItem");
        } else if (GetReportType.获取亚马逊商品库存报告.enumType.equals(str)) {
            LOGGER.error("bean cast to amazonStockItemReport");
            this.resolutionReportFile = resolutionReportFileMap.get("amazonStockItemReport");
        } else {
            LOGGER.error("ResolutionReportFileBean inject false please check ");
        }
    }

    @Override
    public ALLReportUltimateVO getReportAllResultUltimateRTX(RequestReportVO step1VO, AmazonConfigInfo api) throws MarketplaceWebServiceException, InterruptedException, IOException {
        return null;
    }

    @Override
    public List<String> getReportAllResult(RequestReportVO step1VO, AmazonConfigInfo api) throws MarketplaceWebServiceException, InterruptedException, IOException {
        return Collections.singletonList(mockTxt);
    }

    @Override
    public String getReportAllResultUltimate(RequestReportVO step1VO, AmazonConfigInfo api) throws MarketplaceWebServiceException, InterruptedException, IOException {

        getResolutionReportFileBean(step1VO.getReportType());
        getResolutionReportFileBean("_GET_FBA_INVENTORY_AGED_DATA_");

        String property = DeployProperties.getInstance().getProperty("mock.inputStream.file");
//        String property = "C:\\Users\\Administrator\\Desktop\\inventory_age_API.txt";


        //加入调用了亚马逊并且同步了报告

        Map<String, Integer> reportIndex = this.resolutionReportFile.getReportIndex(property);
        String toJson = this.resolutionReportFile.fileToJson(Collections.singletonList(property), step1VO.getShopId(), reportIndex);

//        List<AmazonStockReportVO> amazonStockReportVOS = AmazonReportClient.fileOutputOriginalVersion(Collections.singletonList(path), 23);


//        System.out.println("toJson = " + toJson);


        return toJson;
    }


}
