/**
 * Created by Vove on 2017/4/12.
 * ajax查询学生成绩
 */


var XMLHttpReq;
//创建XMLHttpRequest对象
function createXMLHttpRequest() {
    if (window.XMLHttpRequest) { //Mozilla 浏览器
        XMLHttpReq = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) { // IE浏览器
        try {
            XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
            alert("IE");
        } catch (e) {
            try {
                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                alert("IE2");
            } catch (e) {
            }
        }
    }
}
//发送请求函数
function sendRequest() {
    var condition=$('#condition').val();
    // if(condition==="")
    //     return;

    createXMLHttpRequest();
    var url = "ajax/filterStudentGrade.jsp?condition="+condition;
    XMLHttpReq.open("GET", url, true);
    XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
    XMLHttpReq.send(null);  // 发送请求
}
// 处理返回信息函数
function processResponse() {
    if (XMLHttpReq.readyState === 4) { // 判断对象状态
        if (XMLHttpReq.status === 200) { // 信息已经成功返回，开始处理信息
            DisplayHot();
        } else { //页面不正常
            //window.alert("您所请求的页面有异常。");
        }
    }
}
function DisplayHot() {
    var studentsGrade=XMLHttpReq.responseText;
    var tbodyContent = document.getElementById("tbody");
    tbodyContent.innerHTML = studentsGrade;

}

