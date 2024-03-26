package it.sciancaleporevincenzo.gestRest.services;

import com.lowagie.text.DocumentException;
import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import it.sciancaleporevincenzo.gestRest.enums.Tipologia;
import it.sciancaleporevincenzo.gestRest.model.ElementoScontrino;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PrinterService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Value("${ricevuta.head}")
    String head;

    private void printByteArray(byte[] output) throws Exception {
        log.info("getReport init");
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        InputStream inputStream = new ByteArrayInputStream(output);
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(inputStream, flavor, null);
        DocPrintJob job = printer.createPrintJob();
        job.print(doc, null);
        log.info("getReport printed");
    }

    private static String htmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    private byte[] xhtmlToPdf(String xhtml) throws IOException, DocumentException {
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        iTextRenderer.createPDF(os);

        byte[] byteArray = os.toByteArray();

        os.close();
        return byteArray;
    }

    public byte[] sendReportComanda(List<ElementoScontrino> lista, Tavolo tavolo) {
        try {
            byte[] byteaD = getComanda(lista, tavolo, Tipologia.DRINK);
            if (byteaD != null) print(byteaD);

            byte[] byteaF = getComanda(lista, tavolo, Tipologia.FOOD);
            if (byteaF != null) print(byteaF);

            byte[] byteaA = getComanda(lista, tavolo, Tipologia.ALTRO);
            if (byteaA != null) print(byteaA);

            return byteaF;

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private byte[] getComanda(List<ElementoScontrino> lista, Tavolo tavolo, Tipologia tipologia) throws DocumentException, IOException {
        int i = 0;
        for (ElementoScontrino elem : lista) {
            if (elem.getCategoria() != null && elem.getCategoria().getTipologia() != null && elem.getCategoria().getTipologia().equals(tipologia.getSigla()))
                i++;
        }
        if (i == 0) return null;
        String body = "";

        List<ElementoScontrino> listaComanda1 = lista
                .stream()
                .filter(c -> c.getPortata() == 1)
                .filter(c -> c.getCategoria() != null)
                .filter(c -> c.getCategoria().getTipologia().equals(tipologia.getSigla()))
                .collect(Collectors.toList());

        List<ElementoScontrino> listaComanda2 = lista
                .stream()
                .filter(c -> c.getPortata() == 2)
                .filter(c -> c.getCategoria() != null)
                .filter(c -> c.getCategoria().getTipologia().equals(tipologia.getSigla()))
                .collect(Collectors.toList());

        List<ElementoScontrino> listaComanda3 = lista
                .stream()
                .filter(c -> c.getPortata() == 3)
                .filter(c -> c.getCategoria() != null)
                .filter(c -> c.getCategoria().getTipologia().equals(tipologia.getSigla()))
                .collect(Collectors.toList());

        body = body.concat("<div style='text-align:center; font-size:20px'> " + tipologia.name() + "</div>");
        body = body.concat("<div style='text-align:center; font-size:16px'> Tavolo: " + tavolo.getNumero()
                + " - Descrizione: " + tavolo.getDescrizione() + " - Ore: " + new SimpleDateFormat("HH:mm").format(tavolo.getDataInserimento()) + " </div>");
        body = body.concat("<hr><table width='100%'>");
        if (listaComanda1.size() > 0) {
            body = body.concat("<tr>");
            body = body.concat("<td width='100%' style='text-align:center'> <u>PRIMA PORTATA </u></td>");
            body = body.concat("</tr>");
            for (ElementoScontrino elem : listaComanda1) {
                body = body.concat("<tr>");
                body = body.concat("<td>" + elem.getQuantita() + "x " + elem.getDescrizione().toUpperCase() + "</td>");
                body = body.concat("</tr>");

                if (elem.getAggiunte().trim().length() > 0) {
                    body = body.concat("<tr>");
                    body = body.concat("<td style='font-size:14px'>" + elem.getAggiunte() + "</td>");
                    body = body.concat("</tr>");
                }
            }
        }
        if (listaComanda2.size() > 0) {
            body = body.concat("<tr>");
            body = body.concat("<td width='100%' style='text-align:center'><u> SECONDA PORTATA </u></td>");
            body = body.concat("</tr>");
            for (ElementoScontrino elem : listaComanda2) {
                body = body.concat("<tr>");
                body = body.concat("<td>" + elem.getQuantita() + "x " + elem.getDescrizione().toUpperCase() + "</td>");
                body = body.concat("</tr>");

                if (elem.getAggiunte().trim().length() > 0) {
                    body = body.concat("<tr>");
                    body = body.concat("<td style='font-size:14px'>" + elem.getAggiunte() + "</td>");
                    body = body.concat("</tr>");
                }
            }
        }
        if (listaComanda3.size() > 0) {
            body = body.concat("<tr>");
            body = body.concat("<td width='100%' style='text-align:center'><u> TERZA PORTATA </u></td>");
            body = body.concat("</tr>");
            for (ElementoScontrino elem : listaComanda3) {
                body = body.concat("<tr>");
                body = body.concat("<td>" + elem.getQuantita() + "x " + elem.getDescrizione().toUpperCase() + "</td>");
                body = body.concat("</tr>");

                if (elem.getAggiunte().trim().length() > 0) {
                    body = body.concat("<tr>");
                    body = body.concat("<td style='font-size:14px'>" + elem.getAggiunte() + "</td>");
                    body = body.concat("</tr>");
                }
            }
        }
        body = body.concat("</table>");

        String html = "<html>" +
                "<head>" +
                "<style> " +
                "@page {  size: 2.80in 100in; border: 0px; padding: 0px; margin: 0px } " +
                "</style>" +
                "</head>" +
                "<body>" +
                body +
                "</body></html>";
        String xHtml = htmlToXhtml(html);
        return xhtmlToPdf(xHtml);
    }

    public byte[] sendReportCliente(List<ElementoScontrino> lista, int tableNum) {
        try {
            byte[] bytea = getReportCliente(lista, tableNum);
            if (bytea != null) print(bytea);
            return bytea;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private byte[] getReportCliente(List<ElementoScontrino> lista, int tableNum) throws DocumentException, IOException {
        if (lista.size() == 0) return null;
        String body = "<hr><table >";

        BigDecimal countTot = new BigDecimal(0);
        for (ElementoScontrino elemento : lista) {
            body = body.concat("<tr>");
            body = body.concat("<td width='100%' style='font-size:14px'>" + elemento.getQuantita() + "x " + elemento.getDescrizione() + "</td>");
            body = body.concat("<td style='white-space: nowrap; text-align:right;font-size:14px'>" + (elemento.getPrezzo().multiply(new BigDecimal(elemento.getQuantita()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "€</td>");
            body = body.concat("</tr>");
            countTot = countTot.add(elemento.getPrezzo().multiply(new BigDecimal(elemento.getQuantita())));

            if (elemento.getAggiunte() != null) {
                body = body.concat("<tr>");
                body = body.concat("<td style='font-size:12px'>" + elemento.getAggiunte() + "</td><td></td>");
                body = body.concat("</tr>");
            }
        }

        body = body.concat("<tr>");
        body = body.concat("<td style='font-size:18px'> <b><u>TOTALE</u></b></td>");
        body = body.concat("<td style='white-space: nowrap;text-align:right; font-size:18px!important'> <b><u>" + countTot.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "€</u></b></td>");
        body = body.concat("</tr>");

        body = body.concat("</table>");


        String tableInfo = "<div style='text-align: center;'>Tavolo: " + tableNum + "</div>";
        String thanks = "<div style='text-align: center;'>Grazie e Arrivederci</div>";

        String html = "<html>" +
                "<head>" +
                "<style> " +
                "@page {  size: 2.80in 100in; border: 0px; padding: 0px; margin: 0px } " +
                "</style>" +
                "</head>" +
                "<body>" +
                head + tableInfo + body + thanks +
                "</body></html>";
        String xHtml = htmlToXhtml(html);
        return xhtmlToPdf(xHtml);
    }

    public void print(byte[] bytea) throws PrintException {
        InputStream inputStream = new ByteArrayInputStream(bytea);

        DocPrintJob job = null;
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        job = printer.createPrintJob();

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(inputStream, flavor, null);

        job.print(doc, null);
    }
}