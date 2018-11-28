package com.hiekn.demo.test.frame.text.pdfbox;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.PDFText2HTML;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

public class ExtractPdfImg {

	private static final String BASE_PATH = "C:\\Users\\DH\\Desktop\\quick\\test-data\\";

	public static void main(String[] args) throws Exception {

		File file = new File("H:\\BaiduNetdiskDownload\\电子书\\ABEL\\ABEL_REF.PDF");
		file = new File(BASE_PATH+"java规范1.2.pdf");

//		pdf2Html(file);
		extractPdfImg(file);
//		pdf2word(file);
//		pdf2Image(file);
//		image2Pdf(BASE_PATH+"pdfimg");
		

	}

	public static void pdf2Image(File file) throws Exception {
		PDDocument document = PDDocument.load(file);
		PDFRenderer renderer = new PDFRenderer(document);
		int pageCount = document.getNumberOfPages();
		for (int i = 0; i < pageCount; i++) {
			BufferedImage bim = renderer.renderImageWithDPI(i, 72);
//			ImageIO.write(bim, "jpeg", new File(BASE_PATH+"pdfimg\\"+i+".jpg"));
			ImageIOUtil.writeImage(bim, BASE_PATH+"pdfimg\\image"+i+".jpg",72);
		}
		document.close();
	}


	public static void pdf2Html(File file) throws Exception{
		PDDocument document = PDDocument.load(file);
		int pages = document.getNumberOfPages();
		PDFTextStripper text2html = new PDFTextStripper();
		text2html = new PDFText2HTML();
		text2html.setSortByPosition(true);
		text2html.setStartPage(1);
		text2html.setEndPage(pages);
		String content = text2html.getText(document);
		IOUtils.write(content, new FileOutputStream(new File(BASE_PATH+"a.html")),"utf-8");
		document.close();
	}

	public static void extractPdfImg(File file) throws Exception{
		PDDocument document = PDDocument.load(file);
		int j = 0;
		for(PDPage page : document.getPages()){
			PDResources resources = page.getResources();  
			Iterable<COSName> xobjects = resources.getXObjectNames();
			if (xobjects != null) {  
				Iterator<COSName> imageIter = xobjects.iterator();  
				while (imageIter.hasNext()) {  
					COSName key = imageIter.next();
					if(resources.isImageXObject(key)){  
						PDImageXObject image = (PDImageXObject) resources.getXObject(key);
						File target = new File(BASE_PATH+"pdfimg\\image"+j+".jpg");
						BufferedImage bufferedImage = image.getImage();  
						ImageIO.write(bufferedImage, "jpeg", target);
						j++;
					}
				}
			}
		}
	}

	public static void pdf2word(File file) throws Exception{
		PDDocument doc=PDDocument.load(file);
		int pagenumber=doc.getNumberOfPages();
		String fileName = BASE_PATH + "alibaba.doc";// 创建文件
		File f = new File(fileName);
		if(!f.exists()){
			f.createNewFile();
		}
		FileOutputStream fos=new FileOutputStream(f);
		Writer writer=new OutputStreamWriter(fos,"UTF-8");

		PDFTextStripper stripper=new PDFTextStripper();

		//      doc.addSignature(arg0, arg1, arg2);

		stripper.setSortByPosition(true);//排序
		stripper.setWordSeparator("");//pdfbox对中文默认是用空格分隔每一个字，通过这个语句消除空格（视频是这么说的）
		stripper.setStartPage(1);//设置转换的开始页
		stripper.setEndPage(pagenumber);//设置转换的结束页
		stripper.writeText(doc,writer);
		writer.close();
		doc.close();
	}


	public static void image2Pdf(String path)throws Exception {
		// 多张图片转换为PDF文件
		PDDocument doc =  new PDDocument();
		PDPage page = null;
		PDImageXObject ximage = null;
		PDPageContentStream contentStream = null;

		File files = new File(path);
		String[] a = files.list();
		for (String string : a) {
			if (string.toLowerCase().endsWith(".jpg")) {
				String temp = path + "\\" + string;
				ximage = PDImageXObject.createFromFile(temp, doc);
				page = new PDPage();
				doc.addPage(page);
				contentStream = new PDPageContentStream(doc, page);
				float scale = 0.5f;
				contentStream.drawImage(ximage, 20, 400, ximage.getWidth() * scale, ximage.getHeight() * scale);

				PDFont font = PDType1Font.HELVETICA_BOLD;
				contentStream.beginText();
				contentStream.setFont(font, 12);
				contentStream.newLineAtOffset(100, 700);
				//				contentStream.showText("Hello");
				contentStream.endText();

				contentStream.close();
			}
		}
		doc.save(BASE_PATH+"x.pdf");
		doc.close();
	}
	
	public static void printPdf(File file) throws Exception{
		PDDocument document = PDDocument.load(file);
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(new PDFPageable(document));
		if (job.printDialog()) {
		    job.print();
		}
	}
}
