<!DOCTYPE HTML>				  
<html lang="hu">           
<head>
<title>Feldolgozo</title>
<meta charset="utf-8">  
   
</head>

<body>

<font style="font-size:16pt">
 <h2>HTML5 űrlap</h2>
 
 <?php
 
 echo ("<strong>Név:</strong> " . $_POST['nev'] . "<br><br>"); 
 echo ("<strong>PIN:</strong> " . $_POST['kod'] . "<br><br>"); 
 echo ("<strong>Gyümölcs:</strong> " . $_POST['gyumolcs'] . "<br><br>"); 
 echo ("<strong>Kor:</strong> " . $_POST['kor'] . "<br><br>"); 
 echo ("<strong>Lábméret:</strong> " . $_POST['lmeret'] . "<br><br>"); 
 ?>
 
 <a href="urlap.html">vissza az űrlapra...</a>
 
 </font>
 </body>
 
 </html>