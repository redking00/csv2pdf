# csv2pdf
![Java CI with Maven](https://github.com/redking00/csv2pdf/workflows/Java%20CI%20with%20Maven/badge.svg)
<br>
<br>
🔥A command line tool to generate PDF (using Flyingsaucer) or text files from csv data and Freemarker templates🔥
<br>

#### How it works?
<pre>
+--------------+           +--------------+                                                    
|   CSV data   |           | FTL template |                                                   
+--------------+           +--------------+                                                   
        |                          |                                                          
        |                          |                                                           
        v                          v                                                           
+--------------+           +--------------+         +--------------+          +--------------+ 
|   CsvJDBC    |---------->|  Freemarker  |----/--->|    JSoup     |--------->| FlyingSaucer | 
+--------------+           +--------------+         +--------------+          +--------------+ 
                                   |                                                  |        
                                   /                                                  |
                                   |                                                  |        
                                   v                                                  v        
                           +--------------+                                   +--------------+ 
                           |   TXT file   |                                   |   PDF file   | 
                           +--------------+                                   +--------------+ 
</pre>
<br>

### Build: 
&nbsp;&nbsp;mvn clean package

### Usage: 
&nbsp;&nbsp;java -jar csv2pdf.jar &lt;options&gt;

### Options: 
<table>
 <tr><td>-csv_charset &lt;arg&gt;</td><td>CSV file charset (optional, default 'UTF-8')</td></tr>
 <tr><td>-csv_extension &lt;arg&gt;</td><td>CSV file extesion (optional, default '')</td></tr>
 <tr><td>-csv_file &lt;arg&gt;</td><td>CSV input file (optional, use instead -csv_query)</td></tr>
 <tr><td>-csv_folder &lt;arg&gt;</td><td>CSV folder path (optional, default '.')</td></tr>
 <tr><td>-csv_query &lt;arg&gt;</td><td>CSV query command (optional, use instead -csv_file)</td></tr>
 <tr><td>-csv_separator &lt;arg&gt;</td><td>CSV separator character (optional, default ';')</td></tr>
 <tr><td>-for_each</td><td>Generate one file per data record (optional, see notes below)</td></tr>
 <tr><td>-ftl_encoding &lt;arg&gt;</td><td>FTL input file encoding (optional, default 'UTF-8')</td></tr>
 <tr><td>-ftl_file &lt;arg&gt;</td><td>FTL input file</td></tr>
 <tr><td>-pdf &lt;arg&gt;</td><td>PDF output file (optional, use instead -txt)</td></tr>
 <tr><td>-dpi &lt;arg&gt;</td><td>PDF output file dpi (optional, default 96)</td></tr> 
 <tr><td>-txt &lt;arg&gt;</td><td>TXT output file (optional, use instead -pdf)</td></tr>
 <tr><td>-txt_charset &lt;arg&gt;</td><td>TXT output file charset (optional, default 'UTF-8')</td></tr>
 
</table>
<br>

### NOTES:<br>
 - csv2pdf 2.0.0 and above uses freemarker's square bracket tag syntax and square bracket interpolation syntax.
 - Iterate "*rows*" collection inside your template if you are not using the *-for_each* option
 - You can use a templated output file name if using the *-for_each* option
 - See the examples folder!
 
#### Example 1:<br>
&nbsp;&nbsp;java -jar csv2pdf.jar -csv_file data.csv -ftl_file template.ftl -pdf output.pdf
<br>

#### Example 2:<br>
&nbsp;&nbsp;java -jar csv2pdf.jar -csv_file data.csv -ftl_file template2.ftl -for_each -pdf 'output_${id}.pdf'
<br>


### Any idea, suggestion or comment is always appreciated


