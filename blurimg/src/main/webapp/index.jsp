<!DOCTYPE html>
<html>
<head>
    <title>Image Blur App</title>
     <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="image-uploader">
    <h1 style="font-size: 2.6rem" >Welcome to Image Blur App</h1>
    <p class="multicolor-text">Please upload an image to blur:</p>
    <form action="blur" method="post" enctype="multipart/form-data">
        <input  type="file" name="imageFile" accept="image/*" required>
        <input  type="submit" value="Upload and Blur">
    </form>
    </div>
</body>
</html>
