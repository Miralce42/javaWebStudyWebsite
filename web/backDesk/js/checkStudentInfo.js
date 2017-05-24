/**
 * Created by 韩壮 on 2017/5/23.
 */
function addCheck() {
    var username = document.getElementById("username").value;
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var major = document.getElementById("major").value;
    var sex = document.getElementById("sex").value;
    var classNum = document.getElementById("classNum").value;
    if (username === "" || name === "" || phone === "" || major === "" || sex === "" || classNum === "") {
        alert("所有项目都不能为空！");
    }
    else if (sex !== "男" && sex !== "女") {
        alert("性别输入错误！");
    }
    else if (phone.length !== 11) {
        alert("手机号长度有问题！")
    }
    else if (!/^[0-9]*$/.test(phone)) {
        alert("手机号码必须全为数字！");
    }
    else if (phone.charAt(0) !== '1') {
        alert("你家手机号第一位不是1吗！？")
    }
    else {
        document.form1.action = "addStudentInfo.action";
        document.form1.submit();
    }
}

function deleteCheck() {
    if(window.confirm('删除之后将无法恢复，你确定要删除吗？')){
        document.form1.action="deleteStudentInfo.action";
        document.form1.submit();
    }
    else{
        alert("已取消删除。");
    }
}

function updateCheck(usernameOld) {
    var username = document.getElementById("username").value;
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var major = document.getElementById("major").value;
    var sex = document.getElementById("sex").value;
    var classNum = document.getElementById("classNum").value;
    if(username != usernameOld){
        alert("学号不能更改！若必须更改，请删除之后重新添加！");
    }
    else if (name === "" || phone === "" || major === "" || sex === "" || classNum === "") {
        alert("所有项目都不能为空！");
    }
    else if (sex !== "男" && sex !== "女") {
        alert("性别输入错误！");
    }
    else if (phone.length !== 11) {
        alert("手机号长度有问题！")
    }
    else if (!/^[0-9]*$/.test(phone)) {
        alert("手机号码必须全为数字！");
    }
    else if (phone.charAt(0) !== '1') {
        alert("你家手机号第一位不是1吗！？")
    }
    else {
        document.form1.action = "updateStudentInfo.action";
        document.form1.submit();
    }
}
