/**
 * Created by Vove on 2017/5/22.
 * 控制新建作业选择填空
 */
var choiceBum = 1;
var completionBum = 1;
var allChoiceValue = [];
var allCompletionValue = [];

function addChoice() {
    allChoiceValue.splice(0, allChoiceValue.length);//清空
    for (var i = 1; i < choiceBum; i++) {
        allChoiceValue.push(getChoiceValue(i,i));//保存原有内容
    }
    allChoiceValue.push(getChoiceValue(choiceBum,choiceBum++));//添加新题
    document.getElementById("choices_field").innerHTML = allChoiceValue.toString();
}
function addCompletion() {
    allCompletionValue.splice(0, allCompletionValue.length);//清空
    for (var i = 1; i < completionBum; i++) {
        allCompletionValue.push(getCompletionValue(i,i));//保存原有内容
    }
    allCompletionValue.push(getCompletionValue(completionBum,completionBum));
    completionBum++;
    document.getElementById("completions_field").innerHTML = allCompletionValue.toString();
    for( i=1;i<completionBum;i++){
        CKEDITOR.replace("question_content_"+i);
    }
}
function deleteChoice(index) {
    allChoiceValue.splice(0, allChoiceValue.length);
    var num = 1;
    for (var i = 1; i < choiceBum ; i++) {//
        if (index !== i) {
            allChoiceValue.push(getChoiceValue(i,num));//保存原有内容
            num++;
        }
    }
    if (choiceBum > 1)
        choiceBum--;

    document.getElementById("choices_field").innerHTML = allChoiceValue.toString();
}
function deleteCompletion(index) {
    allCompletionValue.splice(0, allCompletionValue.length);
    var num = 1;
    for (var i = 1; i < completionBum ; i++) {//
        if (index !== i) {
            allCompletionValue.push(getCompletionValue(i,num));//保存原有内容
            num++;
        }
    }
    if(completionBum>1){
        completionBum--;
    }
    document.getElementById("completions_field").innerHTML = allCompletionValue.toString();
    for( i=1;i<completionBum;i++){
        CKEDITOR.replace("question_content_"+i);
    }
}
function getChoiceValue(index,num) {
    //获取原始值
    var title = document.getElementById('choice-title_' + index);
    if (title === null ) {
        title = '';
    } else {
        title = title.value;
    }
    var s_A = document.getElementById('choice_' + index + '_A');
    if (s_A === null) {
        s_A = '';
    } else {
        s_A = s_A.value;
    }
    var s_B = document.getElementById('choice_' + index + '_B');
    if (s_B === null) {
        s_B = '';
    } else {
        s_B = s_B.value;
    }
    var s_C = document.getElementById('choice_' + index + '_C');
    if (s_C === null) {
        s_C = '';
    } else {
        s_C = s_C.value;
    }
    var s_D = document.getElementById('choice_' + index + '_D');
    if (s_D === null) {
        s_D = '';
    } else {
        s_D = s_D.value;
    }
    var ref_key = document.getElementById('ref_key_' + index);
    if (ref_key === null) {
        ref_key = '';
    } else {
        ref_key = ref_key.value;
    }

    //修改新标记值
    var choiceFieldString = '<div class="choice_field" id="choice_field_' + num + '"><table width="100%"><tr><td class="narrow">' +
        '<div class="title">题目' + num + ':</div></td><td><input id="choice-title_' + num + '" class="choice-title" name="choice-title_' + num + '" type="text" value="' + title + '" placeholder="问题">' +
        '</td><td class="narrow"><a class="floatButton" onclick="deleteChoice('+num+')">删除</a></td></tr><tr><td class="narrow">选项A:</td><td><input class="choice" id="choice_' + num + '_A" type="text" name="choice_' + num + '_A" value="' + s_A + '" placeholder="选项A"></td><td class="narrow">选项B:</td>' +
        '<td><input class="choice" type="text" id="choice_' + num + '_B" name="choice_' + num + '_B "' +
        ' value="' + s_B + '" placeholder="选项B"></td></tr><tr><td class="narrow">选项C:</td><td><input class="choice" id="choice_' + num + '_C" type="text" name="choice_' + num + '_C" value="' + s_C + '" placeholder="选项C"></td>' +
        '<td class="narrow">选项D:</td><td><input class="choice" type="text" id="choice_' + num + '_D" name="choice_' + num + '_D" value="' + s_D + '" placeholder="选项D"></td> </tr> <tr> <td class="narrow">' +
        '参考答案:</td><td><input class="ref-fey" type="text" id="ref_key_' + num + '" name="ref_key_' + num + '" value="' + ref_key + '" placeholder="参考答案"></td></tr> </table></div>';
    return choiceFieldString;
}
function getCompletionValue(index,num) {
    var id='question_content_'+index;
    var textContent;
    if(index>=completionBum){
        textContent=''
    }else {
        textContent=CKEDITOR.instances[id].getData();
    }




    var completionFieldString='<div class="completion_field">'+
        '<div class="title">问题'+num+':</div><div style="float:right"><a class="floatButton" onclick="deleteCompletion('+num+')">删除</a></div><br><br><br>'+
            '<textarea class="ckeditor" name="question_content_'+num+'" '+
            'id="question_content_'+num+'">'+textContent+'</textarea>'+
        '</div>';
    return completionFieldString;
}