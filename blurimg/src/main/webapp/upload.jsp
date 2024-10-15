<!DOCTYPE html>
<html>
<head>
    <title>Upload Image</title>
     <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Upload Image</h1>
    <form action="blur" method="post" enctype="multipart/form-data">
        <input type="file" name="imageFile" accept="image/*" required>
        <input type="submit" value="Upload and Blur">
    </form>
</body>
</html>
