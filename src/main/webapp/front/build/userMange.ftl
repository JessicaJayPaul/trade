<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="${css}/kopi6.min.css">

    <script src="${plugins}/jquery-1.12.4/jquery.1.12.4.min.js"></script>
    <script src="${plugins}/common.js"></script>
    <script type="text/javascript" src="${base}/plugins/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${base}/plugins/jquery.form.min.js"></script>
</head>
<body class="info">
<div class="_info fn-clear">
    <div class="content i-h fn-left">
        <form id="ff">
            <table>
                <tbody>
                <tr>
                    <td class="td_top">お名前</td>
                    <td class="td_right">
                        <input name="name" type="text" title="空ではありません">
                    </td>
                </tr>
                <tr>
                    <td class="td_top">フリガナ</td>
                    <td class="td_right">
                        <input name="nickName" name="nickName" type="text" title="空ではありません">
                    </td>
                </tr>
                <tr>
                    <td class="td_top">郵便番号</td>
                    <td class="td_right">
                        <input name="postCode" type="text" title="空ではありません">
                    </td>
                </tr>
                <tr>
                    <td class="td_top">都道府県</td>
                    <td class="td_right">
                        <select name="county">
                        <#list countiesMap?keys as key>
                            <option value="${key}">
                            ${countiesMap[key]}
                            </option>
                        </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_top">詳しい住所</td>
                    <td class="td_right">
                        <input name="address" type="text" title="空ではありません">
                    </td>
                </tr>
                <tr>
                    <td class="td_top">電話番号</td>
                    <td class="td_right">
                        <input name="phoneNumber" type="text" title="空ではありません">
                    </td>
                </tr>
                <tr>
                    <td class="td_top">メールアドレス</td>
                    <td class="td_right">
                        <input name="email" type="text" title="空ではありません" value="xiongdiao" readonly>
                    </td>
                </tr>
                <tr>
                    <td class="td_top">お客様掲示板</td>
                    <td class="td_right">
                        <textarea name="remark" cols="20" rows="2"></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <p>
            <a href="javascript:updateUser();" class="submit">修正情报</a>
        </p>
    </div>
</div>
</body>
<script>
    $(function () {
        $("input[name='name']").val("${user.name}");
        $("input[name='nickName']").val("${user.nickName}");
        $("input[name='postCode']").val("${user.postCode}");
        $("select[name='county']").val("${user.county}");
        $("input[name='address']").val("${user.address}");
        $("input[name='phoneNumber']").val("${user.phoneNumber}");
        $("input[name='email']").val("${user.email}");
        $("textarea[name='remark']").val("${user.remark}");
    });

    function updateUser() {
        $('#ff').ajaxSubmit({
            url: "/api/updateUser.action",
            beforeSubmit: function () {
                // 返回 false 来阻止提交
            },
            dataType: 'json',
            success: function (result) {
                if (result.code === 200) {
                    alert("已修改");
                } else {
                    alert(result.message);
                }
            },
            error: function (data) {
                alert("网络异常！");
            }
        });
    }
</script>

</html>