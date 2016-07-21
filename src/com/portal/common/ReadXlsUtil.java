package com.portal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JkReleaseDetail;

public class ReadXlsUtil {

	public static List readXlsx(InputStream in, int type) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		// 循环工作表Sheet
		List list = new ArrayList();
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				// 循环列Cell
				if (type == 0) {
					JddReleaseDetail detail =null;
					for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
						detail=makeList(xssfRow.getCell(cellNum), cellNum, detail);
					}
					list.add(detail);
				}
				if (type == 1) {
					JkReleaseDetail detail = null;
					for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
						detail=makeList(xssfRow.getCell(cellNum), cellNum, detail);
					}
					list.add(detail);
				}

			}
		}
		return list;
	}

	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	public static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	/**
	 * 存放在JddReleaseDetail里面 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月12日 下午5:03:17
	 * @param xssfCell
	 * @param cellNum
	 * @param detail
	 */
	public static JddReleaseDetail makeList(XSSFCell xssfCell, int cellNum, JddReleaseDetail detail) {
		if (xssfCell != null && !StringUtil.isEmpty(getValue(xssfCell)) ) {
			if(detail==null){
				detail=new JddReleaseDetail();
			}
			if (cellNum == 0) {
				detail.setUserId( getValue(xssfCell).trim());
			}
			else if (cellNum == 1) {
				detail.setUserName( getValue(xssfCell).trim());
			}
			else if (cellNum == 2) {
				detail.setRuleChild( getValue(xssfCell).trim());
			}
			else if (cellNum == 3) {
				detail.setSource(getValue(xssfCell).trim());
			}
			else if (cellNum == 4) {
				detail.setReason(getValue(xssfCell).trim());
			}
			else if (cellNum == 5) {
				detail.setJddCount( (int) Double.parseDouble(getValue(xssfCell).trim()));
			}
		}
		return detail;

	}

	/**
	 * 存在在JkReleaseDetail Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月12日 下午5:02:59
	 * @param xssfCell
	 * @param cellNum
	 * @param detail
	 */
	public static JkReleaseDetail makeList(XSSFCell xssfCell, int cellNum, JkReleaseDetail detail) {
		if (xssfCell != null && !StringUtil.isEmpty(getValue(xssfCell))) {
			if(detail==null){
				detail=new JkReleaseDetail();
			}
			if (cellNum == 0) {
				detail.setUserId(getValue(xssfCell).trim());
			}
			else if (cellNum == 1) {
				detail.setUserName(getValue(xssfCell).trim());
			}
			else if (cellNum == 2) {
				detail.setJkCount( (int) Double.parseDouble(getValue(xssfCell).trim()));
			}
		}
		return detail;

	}

}
