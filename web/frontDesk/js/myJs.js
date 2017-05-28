
function loginCheck() {
    var username = document.getElementById("user.username").value;
    var password = document.getElementById("user.password").value;
    if(username === ""){
        alert("请输入用户名！");
    }
    else if(password === ""){
        alert("请输入密码！");
    }
    else{
        document.loginForm.submit();
    }
}

function ChgPwCheck() {
    var username = document.getElementById("user.username").value;
    var oldPassword = document.getElementById("user.password").value;
    var newPasswordOne = document.getElementById("user.userPasswordOne").value;
    var newPasswordTwo = document.getElementById("re-password").value;
    if(username === ""){
        alert("请输入用户名！");
    }
    else if(username.length!==3){
        alert("用户名长度不正确！");
    }
    else if(oldPassword === "" || newPasswordOne ==="" || newPasswordTwo === ""){
        alert("所有密码均不能为空！");
    }
    else if(oldPassword === newPasswordOne){
        alert("原密码和新密码不能相同！")
    }
    else if(newPasswordOne !== newPasswordTwo){
        alert("两次密码输入不相同");
    }
    else{
        document.form1.action = "ChgPw.action";
    }
}

function phoneNumCheck() {
    var phoneNum = document.getElementById("user.phone").value;
    if(phoneNum.length !== 11){
        alert("手机号需11位！")
    }
    else if(!/^[0-9]*$/.test(phoneNum)){
        alert("手机号需全是数字！");
    }
    else if( phoneNum.charAt(0) !== '1'){
        alert("手机号第一位需为1！")
    }
    else{
        document.form1.submit();
    }
}

function topicCheck() {
    var title = document.getElementsByName("topic.title")[0].value;
    var content =  CKEDITOR.instances.content.getData();

    if(title === ""){
        alert("标题不能为空!");
    }
    else if(title.length > 20){
        alert("标题不能多于20字！");
    }
    else if(content === ""){
        alert("内容不能为空！");
    }
    else {
        document.form1.submit();
    }
}

function replaceImg(str) {
    str=str.replace(/<s?img[^>]*>/gi, '【图片】');
    return str;
}

function showTopicDiv(anthorUsername,thisUsername) {//点击显示隐藏div
    if (anthorUsername === thisUsername){
        document.getElementById('topicDeleteBtn').style.display = 'block';//show的display属性设置为block（显示）

    }
}

function showCommentDiv(anthorUsername,thisUsername,i) {//点击显示隐藏div
        var commentDiv = document.getElementsByName('commentDeleteBtn');//show的display属性设置为block（显示）
            if (anthorUsername === thisUsername){
                commentDiv[i].style.display = 'block';
        }
}

function commentCheck() {
    var content =  CKEDITOR.instances.comment.getData();
    if(content === ""){
        alert("内容不能为空！");
    }
    else {
        document.form1.submit();
    }
}

function searchTopic() {
    document.form1.submit();
}

function deleteTopic() {
    if (confirm("删除之后将不再显示，确定要删除吗?")) {
    }
    else {
        document.getElementById("topicDelete").setAttribute("href", window.location.href);
        alert("已取消删除操作！");
    }
}

function deleteComment() {
    if (confirm("删除之后将不再显示，确定要删除吗?")) {
    }
    else {
        document.getElementById("commentDelete").setAttribute("href", window.location.href);
        alert("已取消删除操作！");
    }
}

function UpdateTopic() {
    var content =  CKEDITOR.instances.content.getData();
    if(content === ""){
        alert("内容不能为空！");
    }
    else{
        document.form1.submit();
    }
}