/**
 * quentin自定义JS公共方法
 */
(function () {

    /**
     * 创建表格属性方法
     * @param bstableId
     * @param url
     * @param columns
     * @constructor
     */
    var MyBootstrapTable = function (bstableId, url, columns) {
        this.btInstance = null;					//jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = Quentin.ctxPath + url;
        this.method = "post";
        this.paginationType = "server";			//默认分页方式是服务器分页,可选项"client"
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.height = 665;						//默认表格高度665
        this.data = {};
        this.queryParams = {}; // 向后台传递的自定义参数
    };


    /**
     * 表格属性方法
     * @type {{init: (function(): MyBootstrapTable), setQueryParams: setQueryParams, setPaginationType: setPaginationType, set: (function(*=, *=): MyBootstrapTable), setData: (function(*): MyBootstrapTable), clear: (function(): MyBootstrapTable), refresh: refresh}}
     */
    MyBootstrapTable.prototype = {
        /**
         * 初始化bootstrapTable方法
         */
        init: function () {
            var tableId = this.bstableId;
            var me = this;
            this.btInstance =
                $('#' + tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    //请求地址
                    url: this.url,
                    //ajax方式,post还是get
                    method: this.method,
                    //ajax请求的附带参数
                    ajaxOptions: {
                        data: this.data
                    },
                    //顶部工具条
                    toolbar: "#" + this.toolbarId,
                    //是否显示行间隔色
                    striped: true,
                    //是否使用缓存,默认为true
                    cache: false,
                    //是否显示分页（*）
                    pagination: true,
                    //是否启用排序
                    sortable: true,
                    //排序方式
                    sortOrder: "desc",
                    //初始化加载第一页，默认第一页
                    pageNumber: 1,
                    //每页的记录行数（*）
                    pageSize: 10,
                    //可供选择的每页的行数（*）
                    pageList: [10, 25, 50, 100],
                    //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParamsType: 'limit',
                    // 向后台传递的自定义参数
                    queryParams: function (param) {
                        return $.extend(me.queryParams, param);
                    },
                    //分页方式：client客户端分页，server服务端分页（*）
                    sidePagination: this.paginationType,
                    //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    search: false,
                    //设置为 true启用 全匹配搜索，否则为模糊搜索
                    strictSearch: true,
                    //是否显示所有的列
                    showColumns: true,
                    //是否显示刷新按钮
                    showRefresh: true,
                    //最少允许的列数
                    minimumCountColumns: 2,
                    //是否启用点击选中行
                    clickToSelect: true,
                    //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    searchOnEnterKey: true,
                    //列数组
                    columns: this.columns,
                    //是否显示分页条
                    pagination: true,
                    height: this.height,
                    icons: {
                        refresh: 'glyphicon-repeat',
                        toggle: 'glyphicon-list-alt',
                        columns: 'glyphicon-list'
                    },
                    iconSize: 'outline'
                });
            return this;
        },
        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {
            this.queryParams = param;
        },
        /**
         * 设置分页方式：server 或者 client
         * @param type
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },

        /**
         * 设置ajax post请求时候附带的参数
         * @param key
         * @param value
         * @returns {MyBootstrapTable}
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         * @param data
         * @returns {MyBootstrapTable}
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         * @returns {MyBootstrapTable}
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * @param parms
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        }
    };

    window.MyBootstrapTable = MyBootstrapTable;
}());