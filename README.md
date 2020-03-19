# csv2pdf
![Java CI with Maven](https://github.com/redking00/csv2pdf/workflows/Java%20CI%20with%20Maven/badge.svg)
<br>
A command line tool to generate PDF and text files from csv data and Freemarker templates.
<br>
usage: csv2pdf<br>
 -csv_charset <arg>     CSV file charset (optional default 'UTF-8')<br>
 -csv_extension <arg>   CSV file extesion (optional default '')<br>
 -csv_file <arg>        CSV input file<br>
 -csv_folder <arg>      CSV folder path (optional default '.')<br>
 -csv_query <arg>       CSV query command (optional, use instead -csv_file)<br>
 -csv_separator <arg>   CSV separator character (optional default ';')<br>
 -for_each              Generate one file per data record (optional)<br>
 -ftl_encoding <arg>    FTL input file encoding (optional default 'UTF-8')<br>
 -ftl_file <arg>        FTL input file<br>
 -pdf <arg>             PDF output file (optional, use instead -txt)<br>
 -txt <arg>             TXT output file (optional, use instead -pdf)<br>
 -txt_charset <arg>     TXT output file charset (optional default 'UTF-8')<br>
  
Example:<br>
  java -jar csv2pdf.jar -csv_file data.csv -ftl_file template.ftl -pdf output.pdf
  
