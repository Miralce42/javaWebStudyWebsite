<%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/19
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Dashboard | Klorofil - Free Bootstrap Dashboard Template</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="../backDesk_assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../backDesk_assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="../backDesk_assets/vendor/chartist/css/chartist-custom.css">
    <link rel="stylesheet" href="../backDesk_assets/vendor/font-awesome/css/font-awesome.min.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../backDesk_assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="../backDesk_assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="../backDesk_assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="../backDesk_assets/img/favicon.png">
    <style type="text/css" rel="stylesheet">
        #fh5co-main{
            padding-top: 86px;
            padding-right: 4px;
        }
        @-webkit-keyframes fadeinLeft {
            from {
                opacity: 0.5;
                /*-webkit-transform: translate3d(-100%, 0, 0);
                transform: translate3d(-100%, 0, 0);*/
                -webkit-transform: translate3d(260px, 0, 0);
                transform: translate3d(260px, 0, 0);
            }
            to {
                opacity: 1;
                -webkit-transform: none;
                transform: none;
            }
        }
        @-webkit-keyframes fadeinRight {
            from {
                opacity: 0.5;
                /*-webkit-transform: translate3d(-100%, 0, 0);
                transform: translate3d(-100%, 0, 0);*/
                -webkit-transform: translate3d(-260px, 0, 0);
                transform: translate3d(-260px, 0, 0);
            }
            to {
                opacity: 1;
                -webkit-transform: none;
                transform: none;
            }
        }
        .left{
            animation:fadeinLeft 0.5s;
            padding-left:4px;
        }
        .right{
            padding-left:264px;
            animation:fadeinRight 0.5s;
        }
    </style>
</head>
<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index.html">后台管理系统</a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>
            <div style="float: left;margin-top: 8px;margin-bottom: 8px;text-align: center; min-width: 66%;max-width: 73%">
                <h2><%=moduleString%></h2>
            </div>
            <div class="navbar-btn navbar-btn-right">
                <a class="btn btn-success update-pro" href="../frontDesk/index.jsp" title="main page" target="_blank"><i class="fa fa-rocket"></i> <span>前往前台主页</span></a>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav">
                <li><a href="homeworkManager.jsp"><i class="lnr lnr-home"></i> <span>作业管理</span></a></li>
                <li><a href="elements.html" class=""><i class="lnr lnr-code"></i> <span>课件管理</span></a></li>
                <li><a href="charts.html" class=""><i class="lnr lnr-chart-bars"></i> <span>资源管理</span></a></li>
                <li><a href="panels.html" class=""><i class="lnr lnr-cog"></i> <span>课程评价</span></a></li>
                <li><a href="selectAllStudents.action" class=""><i class="lnr lnr-alarm"></i> <span>学生信息管理</span></a></li>
                <li>
                    <a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Pages</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="subPages" class="collapse ">
                        <ul class="nav">
                            <li><a href="page-profile.html" class="">Profile</a></li>
                            <li><a href="page-login.html" class="">Login</a></li>
                            <li><a href="page-lockscreen.html" class="">Lockscreen</a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="tables.html" class=""><i class="lnr lnr-dice"></i> <span>实践教学管理</span></a></li>
            </ul>
        </nav>
    </div>
</div>
</div>
<!-- END LEFT SIDEBAR -->
<!-- Javascript -->
<script src="../backDesk_assets/vendor/jquery/jquery.min.js"></script>
<script src="../backDesk_assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../backDesk_assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="../backDesk_assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="../backDesk_assets/vendor/chartist/js/chartist.min.js"></script>
<script src="../backDesk_assets/scripts/klorofil-common.js"></script>

<script type="text/javascript">
    $('.btn-toggle-fullwidth').click(function () {
        var rightDiv=$('#fh5co-main');
        if(rightDiv.hasClass('left')){
            rightDiv.removeClass('left');
            rightDiv.addClass('right');
        }else {
            rightDiv.removeClass('right');
            rightDiv.addClass('left');
        }
    })
</script>
<script>
    $(function() {
        var data, options;

        // headline charts
        data = {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            series: [
                [23, 29, 24, 40, 25, 24, 35],
                [14, 25, 18, 34, 29, 38, 44],
            ]
        };

        options = {
            height: 300,
            showArea: true,
            showLine: false,
            showPoint: false,
            fullWidth: true,
            axisX: {
                showGrid: false
            },
            lineSmooth: false,
        };

        new Chartist.Line('#headline-chart', data, options);


        // visits trend charts
        data = {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            series: [{
                name: 'series-real',
                data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
            }, {
                name: 'series-projection',
                data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
            }]
        };

        options = {
            fullWidth: true,
            lineSmooth: false,
            height: "270px",
            low: 0,
            high: 'auto',
            series: {
                'series-projection': {
                    showArea: true,
                    showPoint: false,
                    showLine: false
                },
            },
            axisX: {
                showGrid: false,

            },
            axisY: {
                showGrid: false,
                onlyInteger: true,
                offset: 0,
            },
            chartPadding: {
                left: 20,
                right: 20
            }
        };

        new Chartist.Line('#visits-trends-chart', data, options);


        // visits chart
        data = {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            series: [
                [6384, 6342, 5437, 2764, 3958, 5068, 7654]
            ]
        };

        options = {
            height: 300,
            axisX: {
                showGrid: false
            },
        };

        new Chartist.Bar('#visits-chart', data, options);


        // real-time pie chart
        var sysLoad = $('#system-load').easyPieChart({
            size: 130,
            barColor: function(percent) {
                return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
            },
            trackColor: 'rgba(245, 245, 245, 0.8)',
            scaleColor: false,
            lineWidth: 5,
            lineCap: "square",
            animate: 800
        });

        var updateInterval = 3000; // in milliseconds

        setInterval(function() {
            var randomVal;
            randomVal = getRandomInt(0, 100);

            sysLoad.data('easyPieChart').update(randomVal);
            sysLoad.find('.percent').text(randomVal);
        }, updateInterval);

        function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

    });
</script>
</body>
</html>
