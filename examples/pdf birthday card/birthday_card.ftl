<html>
<head>
    <style>
        @page {
            size: A4 landscape;
        }

        @font-face {
            font-family: 'custom';
            src: url('ReenieBeanie-Regular.ttf');
            -fs-pdf-font-embed: embed;
            -fs-pdf-font-encoding: Identity-H;
        }
        
        body {
            background-image:url('background.png');
            background-repeat:no-repeat;
            background-position: center;
            font-family: 'custom';
            text-align:center;
        }

        .greet {
            position:absolute;
            top:17cm;
        }

    </style>
</head>
<body>
    <h1>Happy [= age]º birthday [= name]!</h1>
    <div class="greet">Built with csv2pdf!</div>
</body>
</html>

