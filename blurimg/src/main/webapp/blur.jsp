<!DOCTYPE html>
<html>
<head>
    <title>Blurred Image</title>
     <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="blurimghold">
    <h1>Blurred Image</h1>
    <img id="blurredImage" src="uploads/<%= request.getAttribute("outputFileName") %>" alt="Blurred Image">
    <div class="btn-divide">
    <p ><a href="index.jsp" class="backbtn">Back to Upload</a></p>
    <button onclick="downloadImage()" class="downblurimg">Download Blurred Image</button>
    </div>
</div>
    <script>
        function downloadImage() {
            var imageSrc = document.getElementById("blurredImage").src;
            var anchor = document.createElement("a");
            anchor.href = imageSrc;
            anchor.download = "blurred_image.jpg";
            anchor.click();
        }
    </script>
</body>
</html>
