package com.yixin.dsc.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.dsc.dto.ExportProductInfoDTO;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;


public class ExcelUtils {
	protected final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	


	public static byte[] createExcelForMainInfo(List<DscMainInfoDto> mainInfoList) {
		//第一步创建workbook  
		XSSFWorkbook wb = new XSSFWorkbook();
		
        //第二部创建sheet页，并设置标题名称
		Sheet sheet = wb.createSheet("进件详情");

		//第三步定义每个单元格的样式
        CellStyle  style = wb.createCellStyle();      
        style.setAlignment(CellStyle.ALIGN_CENTER);  //居中  

		//列标题
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("申请编号");
		row0.createCell(1).setCellValue("所属资方");
		row0.createCell(2).setCellValue("创建时间");
		row0.createCell(3).setCellValue("当前阶段");
		row0.createCell(4).setCellValue("征信授权书");
		row0.createCell(5).setCellValue("征信合同");
		row0.createCell(6).setCellValue("预审");
		row0.createCell(7).setCellValue("初审");
		row0.createCell(8).setCellValue("终审");
		row0.createCell(9).setCellValue("请款");
		row0.createCell(10).setCellValue("贷后");

		for (int k = 0; k < mainInfoList.size(); k++) {
			DscMainInfoDto mainInfo = mainInfoList.get(k);
			Row row = sheet.createRow(k + 1);
			row.createCell(0).setCellValue(mainInfo.getApplyNo());
			row.createCell(1).setCellValue(mainInfo.getFinancialName());
			row.createCell(2).setCellValue(mainInfo.getCreateTime());
			row.createCell(3).setCellValue(mainInfo.getAssetPhase());
			row.createCell(4).setCellValue(mainInfo.getCreditSignState());
			row.createCell(5).setCellValue(mainInfo.getContractSignState());
			row.createCell(6).setCellValue(mainInfo.getPreState());
			row.createCell(7).setCellValue(mainInfo.getFirstState());
			row.createCell(8).setCellValue(mainInfo.getLastState());
			row.createCell(9).setCellValue(mainInfo.getPaymentState());
			row.createCell(10).setCellValue(mainInfo.getAfterLoanState());
		}

        //第五步转成流并返回
		return HSSFWorkbookCaseByteArray(wb);
	}



	public static byte[] createExcelForMainInfo(List<DscMainInfoDto> mainInfoList, List<DscPaymentErrorDto> paymentErrorDtoList) {
		//创建workbook
		XSSFWorkbook wb = new XSSFWorkbook();

		if (CollectionUtils.isNotEmpty(mainInfoList)) {
			//创建sheet页，并设置标题名称
			Sheet sheet = wb.createSheet("进件详情");
			//定义每个单元格的样式
			CellStyle  style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);  //居中
			//列标题
			Row row0 = sheet.createRow(0);
			row0.createCell(0).setCellValue("申请编号");
			row0.createCell(1).setCellValue("所属资方");
			row0.createCell(2).setCellValue("创建时间");
			row0.createCell(3).setCellValue("当前阶段");
			row0.createCell(4).setCellValue("征信授权书");
			row0.createCell(5).setCellValue("征信合同");
			row0.createCell(6).setCellValue("预审");
			row0.createCell(7).setCellValue("初审");
			row0.createCell(8).setCellValue("终审");
			row0.createCell(9).setCellValue("请款");
			row0.createCell(10).setCellValue("贷后");
			for (int k = 0; k < mainInfoList.size(); k++) {
				DscMainInfoDto mainInfo = mainInfoList.get(k);
				Row row = sheet.createRow(k + 1);
				row.createCell(0).setCellValue(mainInfo.getApplyNo());
				row.createCell(1).setCellValue(mainInfo.getFinancialName());
				row.createCell(2).setCellValue(mainInfo.getCreateTime());
				row.createCell(3).setCellValue(mainInfo.getAssetPhase());
				row.createCell(4).setCellValue(mainInfo.getCreditSignState());
				row.createCell(5).setCellValue(mainInfo.getContractSignState());
				row.createCell(6).setCellValue(mainInfo.getPreState());
				row.createCell(7).setCellValue(mainInfo.getFirstState());
				row.createCell(8).setCellValue(mainInfo.getLastState());
				row.createCell(9).setCellValue(mainInfo.getPaymentState());
				row.createCell(10).setCellValue(mainInfo.getAfterLoanState());
			}
		}
		if (CollectionUtils.isNotEmpty(paymentErrorDtoList)) {
			//创建sheet页，并设置标题名称
			Sheet sheet = wb.createSheet("请款失败");
			//定义每个单元格的样式
			CellStyle  style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);  //居中
			//列标题
			Row row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("申请编号");
			row1.createCell(1).setCellValue("发起请款时间");
			row1.createCell(2).setCellValue("所属资方");
			row1.createCell(3).setCellValue("错误信息");

			for (int k=0;k<paymentErrorDtoList.size();k++) {
				DscPaymentErrorDto paymentErrorDto = paymentErrorDtoList.get(k);
				Row row = sheet.createRow(k + 1);
				row.createCell(0).setCellValue(paymentErrorDto.getApplyNo());
				row.createCell(1).setCellValue(paymentErrorDto.getCreateTime());
				row.createCell(2).setCellValue(paymentErrorDto.getFinancialCode());
				row.createCell(3).setCellValue(paymentErrorDto.getDesc());
			}
		}


		//第五步转成流并返回
		return HSSFWorkbookCaseByteArray(wb);
	}



	private static byte[] HSSFWorkbookCaseByteArray(XSSFWorkbook wb) {
		byte[] filebyte = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
 		try {
 			wb.write(out);
 			filebyte = out.toByteArray();
 		} catch (IOException e) {
 			logger.error("将生成excel文件转成流失败", e);
 		} finally {//使用finally块来关闭输出流
 			try {
			    out.close();
		    } catch (IOException ex) {
 				logger.error("使用finally块来关闭输出流失败", ex);
 			}
 		}
		return filebyte;
	}	
	
	
	/**
	 * 文件导出通用方法
	 * 
	 * @param sheetTitle
	 * @param title
	 * @param list
	 * @return 
	 */
	public static byte[] export(String sheetTitle, String[] title, List<?> list) {
		HSSFWorkbook wb = new HSSFWorkbook();// 创建excel表
		HSSFSheet sheet = wb.createSheet(sheetTitle);
		sheet.setDefaultColumnWidth(20);// 设置默认行宽
		// 表头样式（加粗，水平居中，垂直居中）
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置边框样式
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		HSSFFont fontStyle = wb.createFont();
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(fontStyle);

		// 标题样式（加粗，垂直居中）
		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle2.setFont(fontStyle);
		// 设置边框样式
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 字段样式（垂直居中）
		HSSFCellStyle cellStyle3 = wb.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置边框样式
		cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 创建表头
		HSSFRow row = sheet.createRow(0);
		row.setHeightInPoints(20);// 行高
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(sheetTitle);
		cell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (title.length - 1)));
		// 创建标题
		HSSFRow rowTitle = sheet.createRow(1);
		rowTitle.setHeightInPoints(20);
		HSSFCell hc;
		for (int i = 0; i < title.length; i++) {
			hc = rowTitle.createCell(i);
			hc.setCellValue(title[i]);
			hc.setCellStyle(cellStyle2);
		}
		byte result[] = null;
		ByteArrayOutputStream out = null;
		try {
			// 创建表格数据
			Field[] fields;
			int i = 2;
			for (Object obj : list) {
				fields = obj.getClass().getDeclaredFields();
				HSSFRow rowBody = sheet.createRow(i);
				rowBody.setHeightInPoints(20);
				int j = 0;
				for (Field f : fields) {
					f.setAccessible(true);
					Object va = f.get(obj);
					if (null == va) {
						va = "";
					}
					hc = rowBody.createCell(j);
					hc.setCellValue(va.toString());
					hc.setCellStyle(cellStyle3);
					j++;
				}
				i++;
			}
			out = new ByteArrayOutputStream();
			wb.write(out);
			result = out.toByteArray();
		} catch (Exception ex) {
			logger.info("导出数据异常:{}", ex);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException ex) {
				logger.info("导出数据异常:{}", ex);
			} 
		}
		return result;
	}
	


}
