package br.com.preservtec.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import br.com.preservtec.model.Procuracao;

public class PDFCreator {

	private Document doc = null;
	private OutputStream os = null;
	private String text;

	public PDFCreator() {

	}

	public void criaDocumento(Procuracao procuracao) {
		Random random = new Random();
		text = procuracao.getCartorio().toString() + "-" + procuracao.getBook().toString() + "-"
				+ procuracao.getPage().toString() + "-" + procuracao.getDate().replaceAll("/", "") + "-"
				+ random.nextInt(90);

		try {
			// cria o documento tamanho A4, margens de 2,54cm
			doc = new Document(PageSize.A4, 72, 72, 72, 72);

			// cria a stream de saída
			os = new FileOutputStream("Registros/"+text + ".pdf");

			// associa a stream de saída ao
			PdfWriter.getInstance(doc, os);

			// abre o documento
			doc.open();

			// Image image1 = image;
			Paragraph p = new Paragraph(procuracao.toString());
			doc.add(p);
			// doc.add(image);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (doc != null) {
				// fechamento do documento
				doc.close();
			}
			if (os != null) {
				// fechamento da stream de saída
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
