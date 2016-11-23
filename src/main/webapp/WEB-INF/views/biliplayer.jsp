<%--
  Author: Queric  Date: 2016/11/22 10:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="/static/js/flv.min.js"></script>
</head>
<body>
    <video id="videoElement"></video>
    <script type="text/javascript">
        if (flvjs.isSupported()) {
            var videoElement = document.getElementById('videoElement');
            var flvPlayer = flvjs.createPlayer({
                type: 'mp4',
                url: 'http://v.stu.nchu.edu.cn/xialuotefannao.mp4'
            });
            flvPlayer.attachMediaElement(videoElement);
            flvPlayer.load();
            flvPlayer.play();
        }
        window.onload=function () {
            document.getElementById("pause").onclick=function () {
                flvPlayer.pause();
            }
            document.getElementById("play").onclick=function () {
                flvPlayer.play();
            }
        }

    </script>
<input type="button" id="pause" value="暂停" />
<input type="button" id="play" value="播放" />
</body>
</html>
