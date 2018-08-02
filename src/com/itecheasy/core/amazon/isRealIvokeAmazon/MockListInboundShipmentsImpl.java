package com.itecheasy.core.amazon.isRealIvokeAmazon;

import com.amazon.client.AmazonInboundClient;
import com.amazon.client.AmazonReportClient;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.mock.FBAInboundServiceMWSMock;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;
import com.itecheasy.common.util.BeanUtils;
import com.itecheasy.common.util.DateUtils;
import com.itecheasy.core.amazon.AmazonConfigInfo;
import com.itecheasy.core.amazon.InboundShipmentInfoVO;
import com.itecheasy.core.amazon.ListInboundShipmentsResultVO;
import com.itecheasy.core.amazon.vo.AmazonShipmentStatusListVO;
import com.printMethod.annotations.LoggerNameDescription;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: liteng
 * @Date: 2018/7/20 10:09
 * @Description: mock
 */
public class MockListInboundShipmentsImpl implements IsRealListInboundShipments{

    private static final Logger LOGGER = Logger.getLogger(MockListInboundShipmentsImpl.class);

    @Override
    @LoggerNameDescription(methodNameDescription = "mock")
    public ListInboundShipmentsResultVO listInboundShipments(AmazonConfigInfo api, AmazonShipmentStatusListVO amazonShipmentStatusListVO) {
        ListInboundShipmentsRequest request = new ListInboundShipmentsRequest();
        ShipmentIdList idList = new ShipmentIdList();
        List<String> member = new ArrayList<String>(amazonShipmentStatusListVO.getShipmentIdList());

        idList.setMember(member);
        request.setShipmentIdList(idList);  //set ids
        request.setSellerId(api.getSellerID());
//		request.setMarketplace(api.getMarketplaceID());

//		此日期用于选择您在某个指定日期后（或当时）已更改库存供应情况的商品，日期格式为 ISO 8601。
        if(amazonShipmentStatusListVO.getStartDate()!=null){
            XMLGregorianCalendar queryStartDateTime = DateUtils.getXMLGregorianCalendar(DateUtils.convertDate("2018-5-1"));
            request.setLastUpdatedAfter(queryStartDateTime);
            request.setLastUpdatedBefore(DateUtils.getXMLGregorianCalendar(new Date()));
        }

        //模拟调用亚马逊
        FBAInboundServiceMWSMock mwsMock = new FBAInboundServiceMWSMock();
        ListInboundShipmentsResponse listInboundShipmentsResponse = mwsMock.listInboundShipments(request);

        //设置nextToken
        String nextToken = "mock_token used by test";
        ListInboundShipmentsResult result = new ListInboundShipmentsResult();
        result.setNextToken(nextToken);
        listInboundShipmentsResponse.setListInboundShipmentsResult(result);

        //把它有用的元素封装给vo，用于osms连接数据库来进行持久化
//        List<InboundShipmentInfo> memberList = listInboundShipmentsResponse.getListInboundShipmentsResult().getShipmentData().getMember();

        List<InboundShipmentInfo> memberList = new ArrayList<InboundShipmentInfo>();

        for (int i =0 ;i<member.size(); i++ ) {
            InboundShipmentInfo info = new InboundShipmentInfo();
            info.setShipmentId(member.get(i));
            info.setShipmentStatus("RECEIVING");
//            info.setShipmentName("fasfas");
//            info.setAreCasesRequired(true);
            memberList.add(info);
        }

        ListInboundShipmentsResultVO resultVO = new ListInboundShipmentsResultVO();
        resultVO.setInboundShipmentInfoVOList(BeanUtils.copyList(memberList,InboundShipmentInfoVO.class));
        resultVO.setNextToken(nextToken);

        return resultVO;
    }

    @Override
    public ListInboundShipmentsResultVO listInboundShipmentsByNextToken(AmazonConfigInfo api, AmazonShipmentStatusListVO amazonShipmentStatusListVO) {

            ListInboundShipmentsByNextTokenRequest request = new ListInboundShipmentsByNextTokenRequest();
            request.setNextToken(amazonShipmentStatusListVO.getNextToken());
            request.setSellerId(api.getSellerID());

            //used by test
            ObjectMapper mapper = new ObjectMapper();
            try {
                String string = mapper.writeValueAsString(request);
                LOGGER.info("传过来了：" + string);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                //模拟调用亚马逊
                FBAInboundServiceMWSMock mwsMock = new FBAInboundServiceMWSMock();
                ListInboundShipmentsByNextTokenResponse response = mwsMock.listInboundShipmentsByNextToken(request);

//            String nextToken = response.getListInboundShipmentsByNextTokenResult().getNextToken();
//            List<InboundShipmentInfo> orderDetailList = response.getListInboundShipmentsByNextTokenResult().getShipmentData().getMember();

                List<InboundShipmentInfo> memberList = new ArrayList<InboundShipmentInfo>();
                InboundShipmentInfo info = new InboundShipmentInfo();


                //把它有用的元素封装给vo，用于osms连接数据库来进行持久化
//            List<InboundShipmentInfoVO> listInboundShipmentsResultVOS = BeanUtils.copyList(orderDetailList, InboundShipmentInfoVO.class);
//            ListInboundShipmentsResultVO resultVO = new ListInboundShipmentsResultVO();
//            resultVO.setInboundShipmentInfoVOList(listInboundShipmentsResultVOS);

                //记录log
                String resultJson = mapper.writeValueAsString(response);
                LOGGER.info(resultJson);
                if (amazonShipmentStatusListVO.getNextToken().equalsIgnoreCase("mock_token used by test")) {
                    ListInboundShipmentsResultVO vo = new ListInboundShipmentsResultVO();
                    return vo;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

    }
}
