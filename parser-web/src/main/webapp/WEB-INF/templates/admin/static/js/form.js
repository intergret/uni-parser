function verifySpeed() {
    var form = window.parent.document.forms["siteConfigForm"];
    var maxSpeed = form.elements["maxSpeed"].value;
    var minSpeed = form.elements["minSpeed"].value;
    if(minSpeed > maxSpeed){
        alert("最小抓取频率 > 最大抓取频率 了...")
        return false;
    }
    return true;
};

function toggleWorkerSearch() {
    document.getElementById("workerSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function toggleSeedSearch() {
    document.getElementById("seedSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function toggleSourceSearch() {
    document.getElementById("sourceSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function toggleSiteSearch() {
    document.getElementById("siteSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function toggleWorkerQueueSearch() {
    document.getElementById("workerQueueSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function toggleDeadLinkPatternSearch() {
    document.getElementById("deadLinkPatternSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};

function activateRuleHelp() {
    $(".pattern").tooltip({
        title: '一个匹配特定url的正则，符合这个正则的url才会按当前规则去解析'
    });
    $('.pattern').tooltip('toggle');

    $(".instance").tooltip({
        title: '一个符合pattern的实际url'
    });
    $('.instance').tooltip('toggle');

    $(".parserType").tooltip({
        title: 'html/json/xml分别选择jsoup/json/xml'
    });
    $('.parserType').tooltip('toggle');

    $(".pageType").tooltip({
        title: '当前解析页是List页还是Detail页'
    });
    $('.pageType').tooltip('toggle');

    $(".docType").tooltip({
        title: '标准数据的类型'
    });
    $('.docType').tooltip('toggle');

    $(".storagePath").tooltip({
        title: '解析结果的存放地址，默认为Hbase'
    });
    $('.storagePath').tooltip('toggle');

    $(".state").tooltip({
        title: 'Enable的规则才会真正生效'
    });
    $('.state').tooltip('toggle');

    $(".publishAddress").tooltip({
        title: '解析完成后的通知方式，默认不通知'
    });
    $('.publishAddress').tooltip('toggle');

    $(".automatic").tooltip({
        title: '是否根据标准数据的Schema自动生成Node和Prop'
    });
    $('.automatic').tooltip('toggle');
};

function activateNodeHelp() {
    $(".nodeLable").tooltip({
        title: 'Node的标签'
    });
    $('.nodeLable').tooltip('toggle');

    $(".nodeType").tooltip({
        title: '是否并列存在多个'
    });
    $('.nodeType').tooltip('toggle');

    $(".parentNode").tooltip({
        title: '所属的上层父节点，顶层Node选择0'
    });
    $('.parentNode').tooltip('toggle');

    $(".inputType").tooltip({
        title: '默认为空则上文的输出即为当前的输入，例如node的输出即为其下各个prop的输入，前置ExtraConfig的输出即为当前ExtraConfig的输入等'
    });
    $('.inputType').tooltip('toggle');

    $(".inputOption").tooltip({
        title: '仅输入类型不为DEFAULT的情况下需要填写'
    });
    $('.inputOption').tooltip('toggle');

    $(".condition").tooltip({
        title: '解释器的条件'
    });
    $('.condition').tooltip('toggle');

    $(".value").tooltip({
        title: '解释器的选项'
    });
    $('.value').tooltip('toggle');

    $(".extractorType").tooltip({
        title: '解释器的类型'
    });
    $('.extractorType').tooltip('toggle');

    $(".automatic").tooltip({
        title: '是否根据标准数据的Schema自动生成Prop'
    });
    $('.automatic').tooltip('toggle');
};

function activatePropHelp() {
    $(".glue").tooltip({
        title: '多个值的解析结果可用glue连接'
    });
    $('.glue').tooltip('toggle');

    $(".propLable").tooltip({
        title: 'Prop的标签名'
    });
    $('.propLable').tooltip('toggle');

    $(".isRequired").tooltip({
        title: 'Prop是否必须'
    });
    $('.isRequired').tooltip('toggle');

    $(".isMultiply").tooltip({
        title: 'Prop的值是否有并列的多个'
    });
    $('.isMultiply').tooltip('toggle');

    $(".scopeType").tooltip({
        placement : 'left',
        title: 'Prop的值的可见范围。默认为local则Prop的值只在当前的node里可见；设为Node则在当前规则的其他的node里也可见；设为Rule则在其他规则里也可见，会将改值传递给下一个规则'
    });
    $('.scopeType').tooltip('toggle');

    $(".resultType").tooltip({
        placement : 'bottom',
        title: 'Prop的值的类型，常见的是text；如果Prop需要作为node的唯一标识，则设为key；如果Prop的值是一个link，则设为link，同时配置link的请求方式等相关配置项'
    });
    $('.resultType').tooltip('toggle');

    $(".httpMethod").tooltip({
        title: '仅在resultType为link时需要设置，表示link的请求方式'
    });
    $('.httpMethod').tooltip('toggle');

    $(".referer").tooltip({
        title: '仅在resultType为link时需要设置，表示link的refer'
    });
    $('.referer').tooltip('toggle');

    $(".parserType").tooltip({
        placement : 'bottom',
        title: '仅在resultType为link时需要设置，指定link的解析器，常见的图片设置为image_magic'
    });
    $('.parserType').tooltip('toggle');
};

function activateExtraConfigHelp() {
    $(".inputType").tooltip({
        title: '默认为空则上文的输出即为当前的输入，例如node的输出即为其下各个prop的输入，前置ExtraConfig的输出即为当前ExtraConfig的输入等'
    });
    $('.inputType').tooltip('toggle');

    $(".inputOption").tooltip({
        title: '仅输入类型不为DEFAULT的情况下需要填写'
    });
    $('.inputOption').tooltip('toggle');

    $(".transformType").tooltip({
        placement : 'left',
        title: '解析过程中值的转换类型，同一个Extractor对不同的转换类型会有不同的处理'
    });
    $('.transformType').tooltip('toggle');

    $(".extractorType").tooltip({
        title: '解释器的类型'
    });
    $('.extractorType').tooltip('toggle');

    $(".condition").tooltip({
        title: '解释器的条件'
    });
    $('.condition').tooltip('toggle');

    $(".value").tooltip({
        title: '解释器的选项'
    });
    $('.value').tooltip('toggle');

    $(".refExtraConfigId").tooltip({
        placement : 'left',
        title: '指定当前ExtraConfig的前置ExtraConfig，前置ExtraConfig的输出为当前ExtraConfig的输入。'
    });
    $('.refExtraConfigId').tooltip('toggle');
};

function activateTestHelp() {
    $(".testUrl").tooltip({
        title: '想要测试的url'
    });
    $('.testUrl').tooltip('toggle');

    $(".testHttpMethod").tooltip({
        title: '测试url的请求方式'
    });
    $('.testHttpMethod').tooltip('toggle');

    $(".testReferer").tooltip({
        title: '测试url的refer'
    });
    $('.testReferer').tooltip('toggle');

    $(".testRefererInfo").tooltip({
        placement : 'right',
        title: '上一页的传递信息在这指定，例如：{"id":"3","title":"วาไรตี้"}'
    });
    $('.testRefererInfo').tooltip('toggle');

    $(".testHeader").tooltip({
        placement : 'right',
        title: '测试url的请求头，例如：{"key":"xxx","source":"xxx"}'
    });
    $('.testHeader').tooltip('toggle');

    $(".testForm").tooltip({
        title: '测试url的Post表单，例如：{"uid":"xxx","category":"xxx"}'
    });
    $('.testForm').tooltip('toggle');

    $(".testContent").tooltip({
        title: '指定测试的内容，否则使用测试url的内容'
    });
    $('.testContent').tooltip('toggle');
};

window.onload = function () {
    toggleLinkSetting();
};

function toggleLinkSetting() {
    var resultTypeElement = document.getElementById("resultType");
    var resultTypeValue = resultTypeElement.options[resultTypeElement.selectedIndex].value;
    if (resultTypeValue != "LINK") {
        document.getElementById("linkSetting").style.display = "none";
    } else {
        document.getElementById("linkSetting").style.display = "";
    }
};

function toggleTestSetting() {
    var httpMethodElement = document.getElementById("testHttpMethod");
    var httpMethod = httpMethodElement.options[httpMethodElement.selectedIndex].value;
    if (httpMethod != "POST") {
        document.getElementById("testFormsSetting").style.display = "none";
    } else {
        document.getElementById("testFormsSetting").style.display = "";
    }
};

function toggleExtractorSetting() {
    var extractorElement = document.getElementById("extractorType");
    var extractorValue = extractorElement.options[extractorElement.selectedIndex].value;
    if (extractorValue == "HTML") {
        document.getElementById("conditionTips").innerHTML = "示例：#theme > div > ul";
        document.getElementById("valueTips").innerHTML = "可选值：$text,$html";
    } else if (extractorValue == "REGEX") {
        document.getElementById("conditionTips").innerHTML = "示例：start=([0-9]+)";
        document.getElementById("valueTips").innerHTML = "示例：$1";
    } else if (extractorValue == "CONST") {
        document.getElementById("conditionTips").innerHTML = "";
        document.getElementById("valueTips").innerHTML = "示例：wandoujia";
    } else if (extractorValue == "JSON") {
        document.getElementById("conditionTips").innerHTML = "示例：$.album.title";
        document.getElementById("valueTips").innerHTML = "示例：$text,$json";
    } else if (extractorValue == "XML") {
        document.getElementById("conditionTips").innerHTML = '示例：/span/cover/url';
        document.getElementById("valueTips").innerHTML = "示例：$text,$xml";
    } else if (extractorValue == "TEMPLATE") {
        document.getElementById("conditionTips").innerHTML = '示例：##&page=1';
        document.getElementById("valueTips").innerHTML = "无需填写";
    } else if (extractorValue == "TIME") {
        document.getElementById("conditionTips").innerHTML = '示例：yyyy-MM-dd HH:mm:ss';
        document.getElementById("valueTips").innerHTML = "无需填写";
    } else if (extractorValue == "MAP") {
        document.getElementById("conditionTips").innerHTML = '示例：{"0":"not finish","1":"finish"}';
        document.getElementById("valueTips").innerHTML = "无需填写";
    } else if (extractorValue == "NEXTPAGE") {
        document.getElementById("conditionTips").innerHTML = '示例：http://moxiu\\?page=([0-9]+)$';
        document.getElementById("valueTips").innerHTML = "示例：0:100:1";
    } else if (extractorValue == "UDF") {
        document.getElementById("conditionTips").innerHTML = '可选值：$expression,$function';
        document.getElementById("valueTips").innerHTML = "JS的表达式或函数";
    } else {
        document.getElementById("conditionTips").innerHTML = "";
        document.getElementById("valueTips").innerHTML = "";
    }
};


function submitRule() {
    var form = window.parent.document.forms["ruleForm"];
    var pattern = form.elements["pattern"].value;
    var instance = form.elements["instance"].value;
    var description = form.elements["description"].value;
    var author = form.elements["author"].value;
    if (!description) {
        alert("忘了填上规则简介了...")
        return false;
    } else if (pattern && !instance) {
        alert("忘了填上规则实例了...")
        return false;
    } else if (!pattern && instance) {
        form.elements["instance"].value = '';
        form.submit();
        return true;
    } else if (!author) {
        alert("忘了填上作者大名了...")
        return false;
    } else {
        form.submit();
        return true;
    }
};

function submitNode() {
    var form = window.parent.document.forms["nodeForm"];
    var label = form.elements["label"].value;
    if (!label) {
        alert("忘了填上Node的label了...")
        return false;
    } else {
        form.submit();
        return true;
    }
};

function submitProp() {
    var form = window.parent.document.forms["propForm"];
    var label = form.elements["label"].value;
    if (!label) {
        alert("忘了填上Prop的label了...")
        return false;
    } else {
        form.submit();
        return true;
    }
};

function submitAddExtraConfig(redirectPage) {
    var form = window.parent.document.forms["extraConfigAddForm"];
    form.elements["redirectPage"].value = redirectPage;
    form.submit();
};

function submitTest() {
    var form = window.parent.document.forms["testForm"];
    var testUrl = form.elements["testUrl"].value;
    if (!testUrl) {
        alert("忘了填上测试url了...")
        return false;
    } else {
        form.submit();
        return true;
    }
};

function submitAuthority() {
    var form = window.parent.document.forms["authorityForm"];
    var uid = form.elements["uid"].value;
    var email = form.elements["email"].value;
    var telephone = form.elements["telephone"].value;
    if (!uid) {
        alert("忘了填上uid了...")
        return false;
    } else if (!email) {
        alert("忘了填上邮箱了...")
        return false;
    } else if (!telephone) {
        alert("忘了填上电话了...")
        return false;
    }else {
        form.submit();
        return true;
    }
};

function toggleRuleSearch() {
    document.getElementById("ruleSearchTable").style.display = "";
    document.getElementById("searchWord").focus();
};
