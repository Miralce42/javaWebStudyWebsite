/**
 * Created by Vove on 2017/5/28.
 * 
 * check发布、重编辑作业完整度
 */

function checkHomework() {
    //标题
    var title=$("input[name='homeworkTitle']");
    if(title.val()===""){
        alert("输入作业标题");
        title.focus();
        return;
    }
    var beginTime=$('#beginTime');
    var endTime=$('#endTime');
    if (beginTime.val()===""){
        alert("请选择开始时间");
        beginTime.focus();
        return;
    }else if(endTime.val()===""){
        alert("请选择结束时间");
        endTime.focus();
        return;
    }else if(beginTime>endTime){//
        alert("请选择正确开始、结束时间");
        return;
    }
    //选择题
    for(var i=1;i<choiceNum;i++){
        var choiceTitleId = 'choice-title_' + i;
        var choiceQuestion = CKEDITOR.instances[choiceTitleId].getData();
        if(choiceQuestion===""){
            alert("请填写完整作业信息,(选择题"+i+")");
            // $("#"+choiceTitleId).focus();
            return;
        }
        //分数

        var score = $('#score_' + i);
        if(score.val()===""){
            alert("请填写完整作业信息");
            score.focus();
            return;
        }else {
            if(!isNum(score.val())){//数字
                alert("请输入数型分数");
                score.focus();
                return;
            }
        }
        //检查各选项
        var s_A = $('#choice_' + i + '_A');
        var s_B = $('#choice_' + i + '_B');
        var s_C = $('#choice_' + i + '_C');
        var s_D = $('#choice_' + i + '_D');
        var msg="请填写完整作业信息,（选择"+i+"）选项";
        if(s_A.val().trim()===""){
            //alert("请输入（选择"+i+"）选项A");
            alert(msg+"A");
            s_A.focus();
            return;
        }else if (s_B.val().trim()===""){
            alert(msg+"B");
            s_B.focus();
            return;
        }else if(s_C.val().trim()==="") {
            alert(msg+"C");
            s_C.focus();
            return;
        }else if(s_D.val().trim()==="") {
            alert(msg+"D");
            s_D.focus();
            return;
        }
        //检查参考答案
        var refId='ref_key_'+i;
        var ref_key=$("input[name="+refId+"][type='radio']:checked").val();
        if(typeof(ref_key)==="undefined"){
            alert("请选择（选择题"+i+"）参考答案");
            return;
        }
    }
    //检查填空
    for(i=1;i<completionNum;i++) {
        var id = 'question_content_' + i;
        var completion = CKEDITOR.instances[id].getData();
        if (completion.trim() === "") {
            alert("请填写完整作业信息,（填空" + i + ")");
            return;
        }
        var com_score=$('#comp_score_' + i);
        ref_key=$('#completion_refKey_' + i);
        if(com_score.val().trim()===""){
            alert("请填写完整,（填空" + i + ")分数");
            com_score.focus();
            return;
        }else {
            if(!isNum(com_score.val())){
                alert("请输入数型分数（填空" + i + ")");
                com_score.focus();
                return;
            }
        }

        if(ref_key.val().trim()===""){
            alert("请填写完整,（填空" + i + ")参考答案");
            ref_key.focus();
            return;
        }
    }
    //操作题
    for(i=1;i<operationNum;i++){
        id = 'operation_content_' + i;
        var operation = CKEDITOR.instances[id].getData();
        if (operation.trim() === "") {
            alert("请填写完整作业信息,（操作" + i + ")");
            return;
        }
        var operation_score=$('#operation_score_' + i);
        if(operation_score.val().trim()===""){
            alert("请填写完整,（操作" + i + ")分数");
            operation_score.focus();
            return;
        }else {
            if(!isNum(operation_score.val())){
                alert("请输入数型分数（操作" + i + ")");
                operation_score.focus();
                return;
            }
        }

    }

    document.form.submit();
    // alert("通过");
}
function isNum(content) {
    var re = /\D/;   //判断字符串是否为数字  判断正整数 /^[1-9]+[0-9]*]*$/
    return !re.test(content);
}
