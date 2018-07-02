<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>Spring Boot Mail Starter</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css"/>
    <style>

        body {
            padding-top: 150px;
            padding-bottom: 40px;
            font-family: '楷体';
            font-size: 24px;
            color: #FFFFFF;
            background-color: #13BD9D;
        }

        .form-group {
            margin-top: 20px;
        }

    </style>
</head>
<body>

<div class="container">
    <form action="" method="" class="form-horizontal">
        <div style="width: 21%; margin: 0 auto; text-align: center;">
            <h1>邮箱注册</h1>
            <ul class="nav nav-pills">
                <li role="presentation"><a herf="#">登录</a></li>
                <li role="presentation" class="active"><a herf="#">注册</a></li>
                <#--<li role="presentation"><a herf="#" id="res">重置</a></li>-->
            </ul>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-4">
                <input autocomplete="off" type="email" class="form-control" id="email" name="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-4">
                <input autocomplete="off" type="password" class="form-control" id="password" name="password" placeholder="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-4">
                <input autocomplete="off" type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-4">
                <button type="button" class="form-control btn btn-primary" id="subBtn">提交</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/layer/layer.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>