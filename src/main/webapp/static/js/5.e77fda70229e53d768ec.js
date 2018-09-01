webpackJsonp([5],{"6YWy":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("woOf"),l=a.n(i),n=a("BO1k"),s=a.n(n),o=a("ocgh"),r=a("vLgD");function c(t){return Object(r.a)({url:"/node/getList.do",method:"get",params:t})}var u=a("0xDb"),d={data:function(){return{list:null,providers:null,total:0,listQuery:{page:1,limit:10,sort:"-createTime"},temp:{nodeId:void 0,ip:"",port:0,os:"",masterPassword:""},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑节点",create:"添加节点"},statusOptions:[{key:"1",value:"启用"},{key:"0",value:"禁用"}],listLoading:!0}},filters:{statusFilter:function(t){return{1:"开启",0:"关闭"}[t]},formatTime:function(t){return Object(u.a)(t)}},created:function(){this.fetchData()},methods:{fetchData:function(){var t=this;this.listLoading=!0,c(this.listQuery).then(function(e){t.list=e.data.list,t.total=e.data.page.countTotal,t.listLoading=!1}).catch(function(t){console.trace(t)}),Object(o.b)({userType:2,limit:1e4}).then(function(e){t.providers=e.data.list}).catch(function(t){console.trace(t)})},getList:function(){var t=this;this.listLoading=!0,c(this.listQuery).then(function(e){t.list=e.data.list,t.total=e.data.page.countTotal,setTimeout(function(){t.listLoading=!1},200)})},handleFilter:function(){this.listQuery.page=1,this.getList()},handleSizeChange:function(t){this.listQuery.limit=t,this.getList()},handleCurrentChange:function(t){this.listQuery.page=t,this.getList()},resetTemp:function(){this.temp={nodeId:void 0,nodeName:"",iconUrl:"",processName:"",sortIndex:0,state:1}},handleCreate:function(){var t=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},createData:function(){var t=this;this.$refs.dataForm.validate(function(e){var a;e&&(a=t.temp,Object(r.a)({url:"/node/create.do",method:"post",params:a})).then(function(e){t.temp.nodeId=e.data,t.list.unshift(t.temp),t.dialogFormVisible=!1,t.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})})})},handleModifyState:function(t,e){var a,i=this;t.state=e,(a=t,Object(r.a)({url:"/node/updateState.do",method:"post",params:a})).then(function(){var e=!0,a=!1,l=void 0;try{for(var n,o=s()(i.list);!(e=(n=o.next()).done);e=!0){var r=n.value;if(r.nodeId===t.nodeId){var c=i.list.indexOf(r);i.list.splice(c,1,t);break}}}catch(t){a=!0,l=t}finally{try{!e&&o.return&&o.return()}finally{if(a)throw l}}i.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})})},handleUpdate:function(t){var e=this;this.temp=l()({},t),this.temp.password="",this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},updateData:function(){var t=this;this.$refs.dataForm.validate(function(e){if(e){var a=l()({},t.temp);a.createTime=null,a.updateTime=null,(i=a,Object(r.a)({url:"/node/update.do",method:"post",params:i})).then(function(){var e=!0,a=!1,i=void 0;try{for(var l,n=s()(t.list);!(e=(l=n.next()).done);e=!0){var o=l.value;if(o.nodeId===t.temp.nodeId){var r=t.list.indexOf(o);t.list.splice(r,1,t.temp);break}}}catch(t){a=!0,i=t}finally{try{!e&&n.return&&n.return()}finally{if(a)throw i}}t.dialogFormVisible=!1,t.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})})}var i})},handleDelete:function(t){this.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3});var e=this.list.indexOf(t);this.list.splice(e,1)}}},p={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-select",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"状态"},model:{value:t.listQuery.state,callback:function(e){t.$set(t.listQuery,"state",e)},expression:"listQuery.state"}},t._l(t.statusOptions,function(t){return a("el-option",{key:t.key,attrs:{label:t.value,value:t.key}})})),t._v(" "),a("el-select",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"提供者"},model:{value:t.listQuery.ownerId,callback:function(e){t.$set(t.listQuery,"ownerId",e)},expression:"listQuery.ownerId"}},t._l(t.providers,function(t){return a("el-option",{key:t.memberId,attrs:{label:t.account,value:t.memberId}})})),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v("搜索")]),t._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-add"},on:{click:t.handleCreate}},[t._v("添加节点")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:t.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"nodeID",width:"95"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.nodeId))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"IP",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.ip))])]}}])}),t._v(" "),a("el-table-column",{attrs:{"class-name":"status-col",label:"端口",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.port)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"Dapp密码",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.masterPassword)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"系统",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.os)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"所有者",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.owner)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{"class-name":"status-col",label:"状态",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(t._f("statusFilter")(e.row.state))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"创建日期",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(t._f("formatTime")(e.row.createTime)))])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"action",label:"操作",width:"230"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.handleUpdate(e.row)}}},[t._v("编辑")]),t._v(" "),"1"==e.row.state?a("el-button",{attrs:{size:"mini"},on:{click:function(a){t.handleModifyState(e.row,"0")}}},[t._v("禁用")]):t._e(),t._v(" "),"0"==e.row.state?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){t.handleModifyState(e.row,"1")}}},[t._v("启用")]):t._e()],1)]}}])})],1),t._v(" "),a("div",{staticClass:"pagination-container"},[a("el-pagination",{attrs:{background:"","current-page":t.listQuery.page,"page-sizes":[10,20,30,50],"page-size":t.listQuery.limit,layout:"total, sizes, prev, pager, next, jumper",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.temp,"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"IP",prop:"ip"}},[a("el-input",{model:{value:t.temp.ip,callback:function(e){t.$set(t.temp,"ip",e)},expression:"temp.ip"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"端口",prop:"port"}},[a("el-input",{attrs:{type:"number"},model:{value:t.temp.port,callback:function(e){t.$set(t.temp,"port",e)},expression:"temp.port"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"Dapp密码",prop:"password"}},[a("el-input",{model:{value:t.temp.masterPassword,callback:function(e){t.$set(t.temp,"masterPassword",e)},expression:"temp.masterPassword"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"系统",prop:"os"}},[a("el-input",{model:{value:t.temp.os,callback:function(e){t.$set(t.temp,"os",e)},expression:"temp.os"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"所有者",prop:"ownerId"}},[a("el-select",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"所有者"},model:{value:t.temp.ownerId,callback:function(e){t.$set(t.temp,"ownerId",e)},expression:"temp.ownerId"}},t._l(t.providers,function(t){return a("el-option",{key:t.memberId,attrs:{label:t.account,value:t.memberId}})}))],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),"create"==t.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:t.createData}},[t._v("添加")]):a("el-button",{attrs:{type:"primary"},on:{click:t.updateData}},[t._v("更新")])],1)],1)],1)},staticRenderFns:[]},m=a("VU/8")(d,p,!1,null,null,null);e.default=m.exports}});