package com.yixin.dsc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MongoDataUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDataUtil.class);
	
	
	public static void main(String[] args) {
		File file = new File("E:\\execute\\订单准入mongo信息.txt");
		BufferedReader br=null;
        try{
        	List<String> supplyRuleId = Lists.newArrayList("100030","100033");
        	List<String> cmbc = Lists.newArrayList("100002","100003","100004","100005","100006","100007","100008","100009","100010","100012","100014","100015","100016","100017","100018","100019","100020","100021","100022","100023","100024","100025","100026","100027","100028","100029","100030","100031","100059","100061","100062");
        	List<String> weBank = Lists.newArrayList("100002","100008","100017","100018","100019","100020","100021","100022","100023","100024","100025","100026","100027","100028","100029","100032","100033","100034","100035","100036","100037","100038","100039","100042","100043","100044","100045","100046","100047","100048","100049","100050","100051","100052","100053","100054","100055","100056","100057","100058","100060","100063");
            LOGGER.info("按照行读取文件内容 ----------");
            
            br=new BufferedReader(new FileReader(file));
            String temp;
            Boolean flag = false;
            RuleMach ruleMach = null;
            List<RuleMach> machList = Lists.newArrayList();
            while((temp=br.readLine())!=null){
            	if(temp.equals("{")) {
            		ruleMach = new RuleMach();
            		flag = true;
            		/*row = sheet.createRow(i);
					i++;*/
            	}
            	if(flag) {
            		temp = temp.trim();
            		if(temp.contains("ruleId")) {
            			String data = temp.replace("ruleId :", "").replace(",", "");
            	        ruleMach.setRuleId(data.trim());
            		}
            		if(temp.contains("isMach")) {
            			String data = temp.replace("isMach :", "").replace(",", "");
             	        ruleMach.setIsMach(data.trim());
            		}
            		if(temp.contains("message")) {
            			String data = temp.replace("message :", "").replace(",", "");
             	        ruleMach.setMessage(data.trim());
            		}
            		if(temp.contains("applyNo")) {
            			String data = temp.replace("applyNo :", "").replace(",", "");
             	        ruleMach.setApplyNo(data.trim());
            		}
            		if(temp.contains("createTime")) {
            			String data = temp.replace("createTime : ISODate(", "").replace("Z),", "").replace("T", " ");
             	        ruleMach.setCreateTime(data.trim());
            		}
            		
            	}
            	if(temp.equals("}")) {
            		flag = false;
            		machList.add(ruleMach);
            	}
            }
            LOGGER.info("machList.size:{}",machList.size());
            Map<String,Integer> machMap = Maps.newHashMap();
            Map<String,String> messageMap = Maps.newHashMap();
            
          //第一步创建workbook  
            HSSFWorkbook wb = new HSSFWorkbook();  
            HSSFSheet sheet = createExcel(wb,"准入数据1");
            int i = 1;
            HSSFRow row = null;
            CellStyle  style = wb.createCellStyle();      
            style.setAlignment(CellStyle.ALIGN_CENTER);  //居中 
            for(RuleMach rulem:machList){
            	if(supplyRuleId.contains(rulem.getRuleId())){
            		continue;
            	}
            	
				if(i>=5000){
					sheet = createExcel(wb,"准入数据2");
					i = 1;
					row = sheet.createRow(i);
				} else {
					row = sheet.createRow(i);
					i++;
				}
				Cell cell1 = row.createCell(0);         //第一个单元格  
    	        cell1.setCellValue(rulem.getRuleId());                  //设定值  
    	        cell1.setCellStyle(style);                   //内容居中  
    	        
    	        Cell cell2 = row.createCell(1);         //第二个单元格  
     	        cell2.setCellValue(rulem.getIsMach());                  //设定值  
     	        cell2.setCellStyle(style);
     	        
     	        Cell cell3 = row.createCell(2);         //第一个单元格  
    	        cell3.setCellValue(rulem.getMessage());                  //设定值  
    	        cell3.setCellStyle(style);
    	        
    	        Cell cell4 = row.createCell(3);         //第一个单元格  
     	        cell4.setCellValue(rulem.getApplyNo());                  //设定值  
     	        cell4.setCellStyle(style);
     	        
     	        Cell cell5 = row.createCell(4);         //第一个单元格  
    	        cell5.setCellValue(rulem.getCreateTime());                  //设定值  
    	        cell5.setCellStyle(style);
    	        
            	Integer count = machMap.get(rulem.getRuleId());
            	count = count == null?0:count;
            	machMap.put(rulem.getRuleId(), ++count);
            	
            	messageMap.put(rulem.getRuleId(), rulem.getMessage());
            }
            List<RuleMachSort> sortList = Lists.newArrayList();
            for(Map.Entry<String,Integer> entry:machMap.entrySet()){
            	String ruleId = entry.getKey();
            	Integer count = entry.getValue();
            	sortList.add(new RuleMachSort(ruleId,count));
            }
            Collections.sort(sortList, new Comparator<RuleMachSort>() {

				@Override
				public int compare(RuleMachSort o1, RuleMachSort o2) {
					return o2.getCount() - o1.getCount();
				}
			});
            int totalCount = 0;
            for(RuleMachSort machSort:sortList){
            	totalCount += machSort.getCount();
            }
            BigDecimal totalCountDec = new BigDecimal(totalCount);
            BigDecimal hundredCountDec = new BigDecimal(100);
            BigDecimal countDec = null;
            HSSFSheet sheet2 = createExcel2(wb);
            i = 1;
            for(RuleMachSort machSort:sortList){
            	String ruleId = machSort.getRuleId();
            	Integer count = machSort.getCount();
            	
            	row = sheet2.createRow(i);
            	i++;
            	
            	Cell cell1 = row.createCell(0);         //第一个单元格  
    	        cell1.setCellValue(ruleId);                  //设定值  
    	        cell1.setCellStyle(style);
    	        
    	        Cell cell2 = row.createCell(1);         //第一个单元格  
    	        cell2.setCellValue(count);                  //设定值  
    	        cell2.setCellStyle(style);
    	        
    	        Cell cell3 = row.createCell(2);         //第一个单元格  
    	        cell3.setCellValue(messageMap.get(ruleId)); //设定值  
    	        cell3.setCellStyle(style);
    	        
    	        countDec = new BigDecimal(count);
    	        String proportion = countDec.divide(totalCountDec,4, BigDecimal.ROUND_HALF_UP).multiply(hundredCountDec).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    	        proportion = proportion+"%";
    	        
    	        Cell cell4 = row.createCell(3);         //第一个单元格  
    	        cell4.setCellValue(proportion); //设定值  
    	        cell4.setCellStyle(style);
    	        
    	        
    	        String caiptal = "";
    	        if(cmbc.indexOf(ruleId)>=0){
    	        	caiptal = "民生";
    	        } else {
    	        	caiptal = "微众";
    	        }
    	        Cell cell5 = row.createCell(4);         //第一个单元格  
    	        cell5.setCellValue(caiptal); //设定值  
    	        cell5.setCellStyle(style);
            }
            // 第六步将生成excel文件保存到指定路径下
            FileOutputStream out = new FileOutputStream("E:\\execute\\订单准入统计.xls");
     		try {
     			wb.write(out);
     		} catch (IOException e) {
     			e.printStackTrace();
     		} finally {//使用finally块来关闭输出流
     			try {
     				if (out != null) {
     					out.close();
     				}
     			} catch (IOException ex) {
     				ex.printStackTrace();
     			}
     		}
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	static class RuleMach{
		private String ruleId;
		
		private String isMach;
		
		private String message;
		
		private String applyNo;
		
		private String createTime;

		public String getRuleId() {
			return ruleId;
		}

		public void setRuleId(String ruleId) {
			this.ruleId = ruleId;
		}

		public String getIsMach() {
			return isMach;
		}

		public void setIsMach(String isMach) {
			this.isMach = isMach;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getApplyNo() {
			return applyNo;
		}

		public void setApplyNo(String applyNo) {
			this.applyNo = applyNo;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
	}
	
	
	static class RuleMachSort{
		private String ruleId;
		
		private String message;
		
		private Integer count;
		
		public RuleMachSort(String ruleId, Integer count) {
			super();
			this.ruleId = ruleId;
			this.count = count;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getRuleId() {
			return ruleId;
		}

		public void setRuleId(String ruleId) {
			this.ruleId = ruleId;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
		
	}
	
	
	public static HSSFSheet createExcel(HSSFWorkbook wb,String sheetName) {  
          
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet(sheetName);
          
        //第三步创建行row:添加表头0行  
        HSSFRow row1 = sheet.createRow(0);  
        CellStyle  style = wb.createCellStyle();      
        style.setAlignment(CellStyle.ALIGN_CENTER);  //居中  
          
          
        //第四步创建单元格  
        //第一行
        Cell cell1 = row1.createCell(0);         //第一个单元格  
        cell1.setCellValue("ruleId");                  //设定值  
        cell1.setCellStyle(style);                   //内容居中  
        
        Cell cell2 = row1.createCell(1);         //第二个单元格  
        cell2.setCellValue("isMach");                  //设定值  
        cell2.setCellStyle(style);
        
        Cell cell3 = row1.createCell(2);         //第一个单元格  
        cell3.setCellValue("message");                  //设定值  
        cell3.setCellStyle(style);
        
        Cell cell4 = row1.createCell(3);         //第一个单元格  
        cell4.setCellValue("applyNo");                  //设定值  
        cell4.setCellStyle(style);
        
        Cell cell5 = row1.createCell(4);         //第一个单元格  
        cell5.setCellValue("createTime");                  //设定值  
        cell5.setCellStyle(style);
        return sheet;
		
	}
	
	public static HSSFSheet createExcel2(HSSFWorkbook wb) {  
        
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("规则准入记录");
          
        //第三步创建行row:添加表头0行  
        HSSFRow row1 = sheet.createRow(0);  
        CellStyle  style = wb.createCellStyle();      
        style.setAlignment(CellStyle.ALIGN_CENTER);  //居中  
          
          
        //第四步创建单元格  
        //第一行
        Cell cell1 = row1.createCell(0);         //第一个单元格  
        cell1.setCellValue("ruleId");                  //设定值  
        cell1.setCellStyle(style);                   //内容居中  
        
        Cell cell2 = row1.createCell(1);         //第二个单元格  
        cell2.setCellValue("卡单次数");                  //设定值  
        cell2.setCellStyle(style);
        
        Cell cell3 = row1.createCell(2);         //第二个单元格  
        cell3.setCellValue("message");                  //设定值  
        cell3.setCellStyle(style);
        
        Cell cell4 = row1.createCell(3);         //第二个单元格  
        cell4.setCellValue("占比");                  //设定值  
        cell4.setCellStyle(style);
        
        Cell cell5 = row1.createCell(4);         //第二个单元格  
        cell5.setCellValue("所属资方");                  //设定值  
        cell5.setCellStyle(style);
        return sheet;
		
	}
	
	
}
