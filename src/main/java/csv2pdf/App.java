package csv2pdf;

import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.text.StringSubstitutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class App {
    private static String getUniqueFilename(String name) {
        String result = FileSystems.getDefault().getPath(name, new String[0]).toFile().exists()
                ? System.currentTimeMillis() + "_" + name
                : name;
        return result;
    }

    private static ArrayList<HashMap<String, Object>> getData(String folder, String separator, String extension,
            String charset, String query) throws ClassNotFoundException, SQLException {
        ArrayList<HashMap<String, Object>> rows = new ArrayList<>();
        Class.forName("org.relique.jdbc.csv.CsvDriver");
        Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + folder + "?separator=" + separator
                + "&fileExtension=" + extension + "&charset=" + charset);

        Statement stmt = conn.createStatement(1005, 1007);
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            int columns = results.getMetaData().getColumnCount();
            HashMap<String, Object> row = new HashMap<>();
            for (int n = 1; n <= columns; n++)
                row.put(results.getMetaData().getColumnName(n), results.getObject(n));
            rows.add(row);
        }
        conn.close();
        return rows;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException,
            freemarker.template.TemplateNotFoundException, MalformedTemplateNameException,
            freemarker.core.ParseException, java.io.IOException, freemarker.template.TemplateException {
        System.out.println("·------------------------------------------·");
        System.out.println("|                   ___             ______ |");
        System.out.println("|   ___________   _|__ \\ ____  ____/ / __/ |");
        System.out.println("|  / ___/ ___/ | / /_/ // __ \\/ __  / /_   |");
        System.out.println("| / /__(__  )| |/ / __// /_/ / /_/ / __/   |");
        System.out.println("| \\___/____/ |___/____/ .___/\\__,_/_/      |");
        System.out.println("|                    /_/                   |");
        System.out.println("|                                   v.1.0  |");
        System.out.println("·------------------------------------------·");

        Options options = new Options();

        Option csvFolderOption = new Option("csv_folder", true, "CSV folder path (optional default '.')");
        csvFolderOption.setRequired(false);

        Option csvSeparatorOption = new Option("csv_separator", true, "CSV separator character (optional default ';')");
        csvSeparatorOption.setRequired(false);

        Option csvExtensionOption = new Option("csv_extension", true, "CSV file extesion (optional default '')");
        csvExtensionOption.setRequired(false);

        Option csvCharsetOption = new Option("csv_charset", true, "CSV file charset (optional default 'UTF-8')");
        csvCharsetOption.setRequired(false);

        Option csvInputFileOption = new Option("csv_file", true, "CSV input file");
        csvInputFileOption.setRequired(false);

        Option csvQueryOption = new Option("csv_query", true, "CSV query command (optional, use instead -csv_file)");
        csvQueryOption.setRequired(false);

        Option ftlFileOption = new Option("ftl_file", true, "FTL input file");
        ftlFileOption.setRequired(true);

        Option ftlEncodingOption = new Option("ftl_encoding", true, "FTL input file encoding (optional default 'UTF-8')");
        ftlEncodingOption.setRequired(false);

        Option pdfOption = new Option("pdf", true, "PDF output file (optional, use instead -txt)");
        pdfOption.setRequired(false);

        Option txtOption = new Option("txt", true, "TXT output file (optional, use instead -pdf)");
        txtOption.setRequired(false);

        Option txtCharsetOption = new Option("txt_charset", true, "TXT output file charset (optional default 'UTF-8')");
        txtCharsetOption.setRequired(false);

        Option eachOption = new Option("for_each", false, "Generate one file per data record (optional)");
        eachOption.setRequired(false);

        options.addOption(csvFolderOption);
        options.addOption(csvSeparatorOption);
        options.addOption(csvExtensionOption);
        options.addOption(csvCharsetOption);
        options.addOption(csvInputFileOption);
        options.addOption(csvQueryOption);
        options.addOption(ftlFileOption);
        options.addOption(ftlEncodingOption);
        options.addOption(pdfOption);
        options.addOption(txtOption);
        options.addOption(txtCharsetOption);
        options.addOption(eachOption);

        CommandLineParser parser = new org.apache.commons.cli.DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if ((cmd.hasOption("csv_file")) || (cmd.hasOption("csv_query"))) {
                if ((cmd.hasOption("txt")) || (cmd.hasOption("pdf"))) {
                    String csvFolder = cmd.getOptionValue("csv_folder", ".");
                    String csvSeparator = cmd.getOptionValue("csv_separator", ";");
                    String csvExtension = cmd.getOptionValue("csv_extension", "");
                    String csvCharset = cmd.getOptionValue("csv_charset", "UTF-8");

                    String csvQuery = "select * from " + cmd.getOptionValue("csv_file");
                    ArrayList<HashMap<String, Object>> rows = getData(csvFolder, csvSeparator, csvExtension, csvCharset,
                            csvQuery);

                    String ftlFile = cmd.getOptionValue("ftl_file");
                    String ftlEncoding = cmd.getOptionValue("ftl_encoding", "UTF-8");
                    Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                    cfg.setDirectoryForTemplateLoading(FileSystems.getDefault().getPath(".", new String[0]).toFile());
                    cfg.setDefaultEncoding(ftlEncoding);
                    Template template = cfg.getTemplate(ftlFile);
                    ITextRenderer renderer = cmd.hasOption("pdf") ? new ITextRenderer() : null;

                    if (cmd.hasOption("for_each")) {
                        for (HashMap<String, Object> row : rows) {
                            StringWriter sw = new StringWriter();
                            template.process(row, sw);
                            String fileName = (renderer!=null)?
                                getUniqueFilename(StringSubstitutor.replace(cmd.getOptionValue("pdf"), row)):
                                getUniqueFilename(StringSubstitutor.replace(cmd.getOptionValue("txt"), row));
                            System.out.print("[+] Generating file: " + fileName);
                            OutputStream outputStream = new FileOutputStream(fileName);
                            if (renderer==null) 
                                outputStream.write(sw.toString().getBytes(cmd.getOptionValue("txt_charset", "UTF-8")));
                            else {
                                Document doc = Jsoup.parse(sw.toString());
                                doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).prettyPrint(false);
                                renderer.setDocumentFromString(doc.html());
                                renderer.layout();
                                renderer.createPDF(outputStream);
                            } 
                            outputStream.close();
                            System.out.println("  OK");
                        }
                    } 
                    else {
                        HashMap<String,Object> model = new HashMap<>();
                        model.put("rows", rows);
                        StringWriter sw = new StringWriter();
                        template.process(model, sw);
                        String fileName = (renderer != null)? 
                            getUniqueFilename(cmd.getOptionValue("pdf")):
                            getUniqueFilename(cmd.getOptionValue("txt"));
                        System.out.print("[+] Generating file: " + fileName);
                        OutputStream outputStream = new FileOutputStream(fileName);
                        if (renderer==null)
                            outputStream.write(sw.toString().getBytes(cmd.getOptionValue("txt_charset", "UTF-8")));
                        else {
                            Document doc = Jsoup.parse(sw.toString());
                            doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).prettyPrint(false);
                            renderer.setDocumentFromString(doc.html());
                            renderer.layout();
                            renderer.createPDF(outputStream);
                        } 
                        outputStream.close();
                        System.out.println("  OK");
                    }
                } else throw new org.apache.commons.cli.ParseException("pdf or txt option is required");
                
            } else throw new org.apache.commons.cli.ParseException("csv_file or csv_query option is required");
        } 
        catch (org.apache.commons.cli.ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            System.out.println("Error: " + e.getMessage());
            formatter.printHelp("csv2pdf", options);
        }
    }
}
