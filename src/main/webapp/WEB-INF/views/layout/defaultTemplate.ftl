<#macro myLayout>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>National Library Tech Test</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

  <div>
    <#include "header.ftl"/>
  </div>
  <div class="row">
    <div class="col-sm-4" style="width: 100%;">
       <#nested/>
    </div>
  </div>

</div>

</body>
</html>
</#macro>