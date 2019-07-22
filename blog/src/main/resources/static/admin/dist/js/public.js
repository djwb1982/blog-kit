<!-- 正则验证 start-->
/**
 * 判空
 *
 * @param obj
 * @returns {boolean}
 */
function isNull(obj) {
    if (obj == null || obj == undefined || obj.trim() == "") {
        return true;
    }
    return false;
}

/**
 * 参数长度验证
 *
 * @param obj
 * @param length
 * @returns {boolean}
 */
function validLength(obj, length) {
    if (obj.trim().length < length) {
        return true;
    }
    return false;
}

/**
 * url验证
 *
 * @param str
 * @returns {boolean}
 */
function isURL(str_url) {
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}"
        + "|"
        + "([0-9a-zA-Z_!~*'()-]+\.)*"
        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\."
        + "[a-zA-Z]{2,6})"
        + "(:[0-9]{1,4})?"
        + "((/?)|"
        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re = new RegExp(strRegex);
    if (re.test(str_url)) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 用户名称验证 4到16位（字母，数字，下划线，减号）
 *
 * @param userName
 * @returns {boolean}
 */
function validUserName(userName) {
    var pattern = /^[a-zA-Z0-9_-]{4,16}$/;
    if (pattern.test(userName.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 正则匹配2-18位的中英文字符串
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_18(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,18}$/;
    if (pattern.test(str.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 正则匹配2-100位的中英文字符串
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_100(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,100}$/;
    if (pattern.test(str.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 用户密码验证 最少6位，最多20位字母或数字的组合
 *
 * @param password
 * @returns {boolean}
 */
function validPassword(password) {
    var pattern = /^[a-zA-Z0-9]{6,20}$/;
    if (pattern.test(password.trim())) {
        return (true);
    } else {
        return (false);
    }
}

<!-- 正则验证 end-->

/**
 * 获取jqGrid选中的一条记录
 * @returns {*}
 */
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        swal("请选择一条记录", {
            icon: "warning",
        });
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        swal("只能选择一条记录", {
            icon: "warning",
        });
        return;
    }
    return selectedIDs[0];
}

/**
 * 获取jqGrid选中的一条记录(不出现弹框)
 * @returns {*}
 */
function getSelectedRowWithoutAlert() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        return;
    }
    return selectedIDs[0];
}

/**
 * 获取jqGrid选中的多条记录
 * @returns {*}
 */
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        swal("请选择一条记录", {
            icon: "warning",
        });
        return;
    }
    return grid.getGridParam("selarrrow");
}

function uploadImageLoadbyFileExt(fileupload, inputImage, showId, fileQueue, param,ext , callback) {
    $("#" + fileupload).uploadify({
        'swf': ctx+'/admin/plugins/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
        'uploader': webPath + '/admin/upload/file',
        'buttonClass':'btn btn-info',
        'scriptData': {"base": "mavendemo", "param": param},
        'queueID': fileQueue, //文件队列ID
        'fileObjName': 'file', //您的文件在上传服务器脚本阵列的名称
        'auto': true, //选定文件后是否自动上传
        'multi': false, //是否允许同时上传多文件
        'hideButton': false,//上传按钮的隐藏
        'buttonText': '<i class="fa fa-picture-o"></i>&nbsp;上传封面',//默认按钮的名字
        'buttonImg': ctx+'/admin/plugins/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
        'width': 105,
        'simUploadLimit': 3,//多文件上传时，同时上传文件数目限制
        'sizeLimit': 51200000,//控制上传文件的大小
        'queueSizeLimit': 3,//限制在一次队列中的次数（可选定几个文件）
        'fileDesc': '支持格式:'+ext,//出现在上传对话框中的文件类型描述
        'fileExt': ext,//支持的格式，启用本项时需同时声明fileDesc
        'folder': '/upload',//您想将文件保存到的路径
        'cancelImg': ctx+'/admin/plugins/uploadify/uploadify-cancel.png',
        onSelect: function (fileObj) {
            $("#" + fileQueue).html("");
            if (fileObj.size > 51200000) {
                alert('文件太大最大限制51200kb');
                return false;
            }
        },
        onUploadSuccess: function (fileObj,data, response) {
               // $("#" + inputImage).val(data.data);
                var obj = JSON.parse(data);
                console.log(obj.data);
                console.log($("#" + showId));
                $("#" + showId).attr('src', obj.data);
               // $("#" + showId).show();
        },
        onError: function (event, queueID, fileObj, errorObj) {
           console.log(event);
        }
    });
}