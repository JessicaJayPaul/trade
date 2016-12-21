<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${base}/plugins/jquery-easyui-1.2.6/themes/default/easyui.css">
    <link rel="stylesheet" href="${base}/plugins/jquery-easyui-1.2.6/themes/icon.css">
    <link rel="stylesheet" href="${base}/plugins/editor/themes/default/default.css" />
    <script charset="utf-8" src="${base}/plugins/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${base}/plugins/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${base}/plugins/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${base}/plugins/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

    <style type="text/css">
        .editDiv td{
            background-color: #E7E7E7;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
    <div class="editDiv">
        <form action="updatePageInfo.action" method="post">
        <table width="100%" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
            <tr>
                <input type="text" name="id" hidden="hidden" value="${pageInfo.id}">
                <input type="text" name="type" hidden="hidden" value="${pageInfo.type}">
                <td>标题</td>
                <td>
                	<input type="text" name="title" id="test" value="${pageInfo.title}">
            	</td>
            </tr>
            <tr>
                <td>关键字</td>
                <td>
                	<input type="text" name="keyword" value="${pageInfo.keyword}">
            	</td>
            </tr>
            <tr>
                <td>描述</td>
                <td>
                	<textarea name="description">${pageInfo.description}</textarea>
            	</td>
            </tr>
            <tr>
                <td>内容</td>
                <td>
                	<textarea name="content">${pageInfo.content}</textarea>
            	</td>
            </tr>
            <tr>
                <td colspan="2">
                	<input type="submit" value="保存" style="width:100px;">
                </td>
            </tr>
        </table>
        </form>
    </div>
    
    <script type="text/javascript">
    	var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[name="content"]', {
            	width : '750px',
                autoHeightMode : true,
                cssData: 'body {font-size:14px;}',
                afterCreate : function() {
                    this.loadPlugin('autoheight');
                }
            });
        });
    </script>
</body>
</html>