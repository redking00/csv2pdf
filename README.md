# csv2pdf
![Java CI with Maven](https://github.com/redking00/csv2pdf/workflows/Java%20CI%20with%20Maven/badge.svg)
<br>
<br>
🔥A command line tool to generate PDF and text files from csv data and Freemarker templates🔥
<br>
<br>
usage: csv2pdf<br>
&nbsp;&nbsp;-csv_charset <arg>     CSV file charset (optional default 'UTF-8')<br>
&nbsp;&nbsp;-csv_extension <arg>   CSV file extesion (optional default '')<br>
&nbsp;&nbsp;-csv_file <arg>        CSV input file (optional, use instead -csv_query)<br>
&nbsp;&nbsp;-csv_folder <arg>      CSV folder path (optional default '.')<br>
&nbsp;&nbsp;-csv_query <arg>       CSV query command (optional, use instead -csv_file)<br>
&nbsp;&nbsp;-csv_separator <arg>   CSV separator character (optional default ';')<br>
&nbsp;&nbsp;-for_each              Generate one file per data record (optional)<br>
&nbsp;&nbsp;-ftl_encoding <arg>    FTL input file encoding (optional default 'UTF-8')<br>
&nbsp;&nbsp;-ftl_file <arg>        FTL input file<br>
&nbsp;&nbsp;-pdf <arg>             PDF output file (optional, use instead -txt)<br>
&nbsp;&nbsp;-txt <arg>             TXT output file (optional, use instead -pdf)<br>
&nbsp;&nbsp;-txt_charset <arg>     TXT output file charset (optional default 'UTF-8')<br>
<br>
 
Example:<br>
&nbsp;&nbsp;java -jar csv2pdf.jar -csv_file data.csv -ftl_file template.ftl -pdf output.pdf
  
