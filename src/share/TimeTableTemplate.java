package share;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import database.cached.UserDatabase;
import gui.PeriodStrip;

public class TimeTableTemplate 
{

	private Font font1;
	private Font font2;
	private PdfPTable table1;
	private Document doc;
	private String[] days = {"Period","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	private PdfPTable table2;
	private PdfPTable table3;
	private int column;

	public TimeTableTemplate(String path, int column,boolean tableUser) 
	{
		this.column = column;
		doc = new Document();
		doc.setPageSize(PageSize.A4);
		doc.setMargins(0.5f, 0.5f, 50, 0.5f);
		try {
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
			writer.setPageSize(PageSize.A4);			
			doc.open();

			setFont();
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(new SimpleDateFormat("dd-MM-yyyy ").format(new Date()).toString(),font2), 550, 30, 0);
			
			table1 = new PdfPTable(1);
			table1.setWidthPercentage(86);

			table2 = new PdfPTable(column+1);
			table2.setWidthPercentage(86);
			for(byte i=0; i<=column;i++)
			{
				table2.addCell(getCell(days[i], Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			}
			
			table3 = new PdfPTable(4);
			table3.setTotalWidth(new float[] {80,175,80,175});
			table3.setLockedWidth(true);
			table3.addCell(getCell("Course Code", Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			table3.addCell(getCell("Course Title", Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			table3.addCell(getCell("Credit", Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			if(tableUser)
				table3.addCell(getCell("Name Of Class", Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			else
				table3.addCell(getCell("Name Of Teacher", Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
		} 
		catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

	private void setFont()
	{
		font1 = new Font();
		font1.setSize(11);
		font1.setFamily(BaseFont.TIMES_ROMAN);
		font1.setStyle(Font.BOLD);
		font2 = new Font();
		font2.setSize(11);
		font2.setFamily(BaseFont.TIMES_ROMAN);
		font2.setStyle(Font.NORMAL);
	}
	
	public void addTitleInfo(String timeTableTitle)
	{
		timeTableTitle = UserDatabase.getInstance().getTimeTableTitle()+"\n\n"+timeTableTitle+" - Time Table";
		table1.addCell(getCell(timeTableTitle, Color.WHITE, Element.ALIGN_CENTER, 1, font1, 0));
	}
	
	public PdfPCell getCell(String text, Color color, int align, int mergeCell, Font font, int border)
	{
		PdfPCell cell = new PdfPCell(new Phrase(text,font));
		cell.setBackgroundColor(color);
		cell.setHorizontalAlignment(align);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(mergeCell);
		cell.setPaddingBottom(5);
		cell.setBorder(border);
		return cell;
	}
	
	public void setSlotData(Object[][] data)
	{
		ArrayList<String> time = PeriodStrip.getInstance(0).getTime(true);
		System.out.println("Column : "+column);
		byte periodNo=0;
		for(byte i=0;i<=UserDatabase.getInstance().getRow();i++)
		{
			if(i+1==UserDatabase.getInstance().getLunchPeriod())
			{
				table2.addCell(getCell("Lunch Break ("+time.get(i)+")", Color.LIGHT_GRAY, Element.ALIGN_CENTER, UserDatabase.getInstance().getColumn()+1, font1, 15));
				continue;
			}
			else
				table2.addCell(getCell((periodNo+1)+"\n"+time.get(i), Color.LIGHT_GRAY, Element.ALIGN_CENTER, 1, font1, 15));
			for(byte j=0; j<column;j++)
			{
				try {
					table2.addCell(getCell(data[j][periodNo].toString(), Color.white, Element.ALIGN_CENTER, 1, font2, 15));
				} 
				catch (Exception e) {
					table2.addCell(getCell("", Color.white, Element.ALIGN_CENTER, 1, font2, 15));
//					e.printStackTrace();
				}
			}
			periodNo++;
		}
	}
	
	public void setMetaData(ArrayList<String> data)
	{
		for(byte i=0;i<data.size();i++)
		{
			if(i%2 == 0)
				table3.addCell(getCell(data.toArray()[i].toString(), Color.white, Element.ALIGN_CENTER, 1, font2, 15));
			else
				table3.addCell(getCell(data.toArray()[i].toString(), Color.white, Element.ALIGN_LEFT, 1, font2, 15));
		}		
	}
	
	public void combineAll()
	{
		try {
			doc.add(table1);
			doc.add(new Paragraph("\n",font1));
			doc.add(table2);
			doc.add(new Paragraph("\n\n"));
			
			if(UserDatabase.getInstance().isAddMetaData())
				doc.add(table3);
			
			doc.close();
			System.out.println("Time Table Created");
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
